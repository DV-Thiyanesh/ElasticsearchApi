package nic.elastic.login.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.time.format.*;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nic.elastic.login.model.userModel;





@Controller
@RequestMapping("/restt/user")
public class UserResources 
 {
   RestHighLevelClient client;
	
	public UserResources() {
		client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http")));
	}
	
	 @GetMapping("/view/{id}")
	 public @ResponseBody Object view(@PathVariable final String id) throws IOException {
		 
		
		 	Map<Object,Object> finalresponse = new HashMap<Object,Object>();
		    GetRequest request = new GetRequest("company", "employee", id).version(1);
		    GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
		    Map<String, Object> result = new HashMap<String,Object>(); 
		    result = getResponse.getSource();
		    List<Object> llist = new ArrayList<Object>();
		    llist.add(result);
		    finalresponse.put("data",llist);
		    return finalresponse;
	
	 }	
	 @RequestMapping("/display")
	 public String view()  {
		 
		    return "display";
	 }
	 
	 @RequestMapping("/displays")
	 public String views()  {
		 
		    return "charts";
	 }
	 
	 String resultJSON = null;
	 @GetMapping("/multiview")
	 public @ResponseBody String multiview() throws IOException
	   {
		   MultiGetRequest request = new MultiGetRequest();
		   request.add(new MultiGetRequest.Item(
		     "company",         
		     "employee",          
		     "1"));  
		   request.add(new MultiGetRequest.Item(
			 "company",         
			 "employee",          
			 "2"));
		 
		   MultiGetResponse response = client.mget(request, RequestOptions.DEFAULT);
		   MultiGetItemResponse item = response.getResponses()[0];
		   GetResponse getResponse = item.getResponse();
			 if (getResponse.isExists()) 
			  {
				resultJSON = getResponse.getSourceAsString();
			  }
		
		
	      return resultJSON;
	    }
	 @GetMapping("/datesearch") 
	 public @ResponseBody SearchResponse datesearch() throws IOException
	  {
		 SearchRequest searchRequest = new SearchRequest();
		 searchRequest.indices("winlogbeat-6.5.3-2019.07.13");
		 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		 searchSourceBuilder.query(QueryBuilders.rangeQuery("date").from("2019-07-13").to("2019-07-14"));
		 searchSourceBuilder.from(0); 
		 searchSourceBuilder.size(3); 
		 searchRequest.source(searchSourceBuilder); 
		 SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		// SearchHits hits = searchResponse.getHits();
		/* System.out.println("Hits:"+hits);
		 
		 List<Object> llist = new ArrayList<Object>();
		 for (SearchHit hit :  searchResponse.getHits()) 
		  {
		     llist.add(hit.getSourceAsMap());
		  }*/
		 return searchResponse;
        }
	 
	 @GetMapping("/datesearch1") 
	 public @ResponseBody List<Object> datesearch1() throws IOException
	  {
		 SearchRequest searchRequest = new SearchRequest();
		 searchRequest.indices("check1","sample");
		 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		 searchSourceBuilder.query(QueryBuilders.rangeQuery("date").from("2019-01-01").to("2019-10-02")); 
		 searchRequest.source(searchSourceBuilder); 
		 SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		 List<Object> llist = new ArrayList<Object>();
		 for (SearchHit hit :  searchResponse.getHits()) 
		  {
		     llist.add(hit.getSourceAsMap());
		  }
		 return llist;
		
	
		
	  }
	 
	 @GetMapping("/datesearch2") 
	 public @ResponseBody List<Object> datesearch2(@RequestParam("fromdate") String fromdate,@RequestParam("todate") String todate ) throws IOException
	  {
		 SearchRequest searchRequest = new SearchRequest();
		 searchRequest.indices("winlogbeat-*");
		 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		 searchSourceBuilder.query(QueryBuilders.rangeQuery("@timestamp").from(fromdate).to(todate)); 
		 searchSourceBuilder.from(0); 
		 searchSourceBuilder.size(1000); 
		 searchRequest.source(searchSourceBuilder); 
		 SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		
		
		  List<Object> llist = new ArrayList<Object>();
		  System.out.println("HITS"+response.getHits().toString());
		
		 for (SearchHit hit : response.getHits()) 
		  {
			 userModel model = new userModel(); 
			 model.setComputer_name(hit.getSourceAsMap().get("computer_name"));
			    model.setType(hit.getSourceAsMap().get("type"));
			    String strdate=(String) hit.getSourceAsMap().get("@timestamp");
			    System.out.println("date:"+strdate);
			    Instant instant = Instant.parse(strdate);
			    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.MEDIUM ).withLocale( Locale.getDefault() ).withZone( ZoneId.systemDefault() );
			   System.out.println(formatter.format(instant));
			    model.setEvent_time(formatter.format(instant));
			    model.setMessage(hit.getSourceAsMap().get("message"));
			
			    
			    
			
		    llist.add(model);

		   }
		 System.out.println(llist.toString());
		 return llist;
		 
		 
	
 
	  }
	 @GetMapping("/datesearch3") 
	 public @ResponseBody List<Object> datesearch3() throws IOException
	  {
		 SearchRequest searchRequest = new SearchRequest();
		 searchRequest.indices("winlogbeat-*");
		 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		 searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.from(0); 
		 searchSourceBuilder.size(10000); 
		 searchRequest.source(searchSourceBuilder); 
		 SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		 SearchHits hits = searchResponse.getHits();
			System.out.println("Hits:"+hits);
			 
			 List<Object> llist = new ArrayList<Object>();
			 for (SearchHit hit :  searchResponse.getHits()) 
			  {
			     llist.add(hit.getSourceAsMap());
			  }
	  return llist;
		// return searchResponse;
	  }
	 
	 /*  @GetMapping("/search") 
	  public @ResponseBody  List<Object> search() throws IOException
	    {
		 
		   List<Object> llist = new ArrayList<Object>();
		   SearchResponse response = client.search(new SearchRequest("check1"), RequestOptions.DEFAULT);
		   for (SearchHit hit : response.getHits()) 
		    {
		      llist.add(hit.getSourceAsMap());
		    }
		   return llist;
		 
		
	    }*/


 }




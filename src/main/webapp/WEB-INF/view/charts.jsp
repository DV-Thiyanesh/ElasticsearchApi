<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.14/c3.min.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/css/spin.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.c3-axis-y text {
	font-size: 15px;
	//
	change
	the
	size
	of
	the
	fonts
}

.c3-axis-x text {
	font-size: 15px;
	//
	change
	the
	size
	of
	the
	fonts
}
</style>
<title>Document</title>
</head>
<body>

		<div class="card mb-3">
		
			<div class="card-header">
			<i class="fa fa-bar-chart" aria-hidden="true"></i>
			No. of Log Logged-Day wise   
			</div>
				<div class="card-body">
					<div id="chart"></div>
					<div class="card-footer small text-muted"></div>
				</div>
			</div>
			<div class="card mb-3">
		
			<div class="card-header">
			<i class="fa fa-pie-chart" aria-hidden="true"></i>
			Percentage of Event-id </div>
				<div class="card-body">
					<div id="chart1"></div>
					<div class="card-footer small text-muted"></div>
				</div>
			</div>
			<div class="card-header">
			<i class="fa fa-pie-chart" aria-hidden="true"></i>
			Percentage of Log_name Logged </div>
			
				<div class="card-body">
					<div id="chart2"></div>
					<div class="card-footer small text-muted"></div>
				</div>
			</div>
			
			
			<div id="chart" style="height: 200px"></div>
			 <div id="chart1" style="height: 200px"></div>
			 <div id="chart2" style="height:200px"></div>
		    <script src="d3.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.6.14/c3.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.9.2/d3.min.js"></script>
		<script type="text/javascript">
		//$('.spinner').attr('hidden', false);
    	d3.json("http://localhost:8080/restt/user/datesearch3").then(function(d){
    	
     		console.log(d)
     	
     		d.forEach(i => {
         		i["@timestamp"] = new Date(i["@timestamp"])
         		i["@timestamp"] =  i["@timestamp"].getDate()+"/"+(i["@timestamp"].getMonth()+1)+"/"+i["@timestamp"].getFullYear()
        	})
    		let arrYear = d.map(i => {return i["@timestamp"]})
   
    		let count = {}
    		arrYear.forEach(i => {count[i] = (count[i] || 0)+1})
    		console.log(count)
  			let valueArr =   Object.values(count)
  			let finyears = Object.keys(count)
			valueArr.unshift("No Of instances")
			console.log(valueArr)
			
			let event_id = d.map(i => {return i.event_id})
let count1 = {}
    event_id.forEach(i => {count1["event_id_no: "+i] = (count1["event_id_no: "+i] || 0)+1})
    console.log(count1)
     let valArrPie = Object.entries(count1)
    console.log(valArrPie)

    
			let log_name = d.map(i => {return i.log_name})
let count2 = {}
    log_name.forEach(i => {count2["log_name: "+i] = (count2["log_name: "+i] || 0)+1})
    console.log(count2)
     let valArrPie1 = Object.entries(count2)
    console.log(valArrPie)
			
    var chart= c3.generate({
				//$('.spinner').attr('hidden', false);
             	bindto: "#chart",
             	data: {
                    columns: [valueArr],
                    type: "bar",labels:true,
                    selection: {
                        enabled: true 
                    }
                 }, /* bar: {
                     width: {
                         ratio: 0.25 // this makes bar width 50% of length between ticks
                     }
                 },*/
                 legend: {
                     show: false
                 },
                  size: {
        			height: 400,
        			width: 1200
    			},
     			padding:{
         			bottom: 20
     			},
            	axis: {
                	x: {
                    	type: 'category',
                    	categories: finyears,label: {
                            text: 'Date',
                            position: 'outer-center' 
                      	},
                      	tick: {
                        	rotate: -40,
                         	multiline: false
               			}
                	},y: {
                    	label: {
                        	text: "No of Count",
                        	position: 'outer-middle',
                      	}
                    }
            	}
        	});
     		
     		d3.select(".c3-axis-x-label").attr("transform","translate(0,40)")
     	
     		 var chart1 = c3.generate({
                 bindto: "#chart1",
         data: {
             // iris data from R
             columns: valArrPie,
             type : 'donut'
         },
         legend: {
              show: true
         },
         size: {
             width: 400,
             height: 400
         }
        
     });
     		         	
    		 var chart2 = c3.generate({
                bindto: "#chart2",
        data: {
            // iris data from R
            columns: valArrPie1,
            type : 'donut'
        },
        legend: {
             show: true
        },
        size: {
            width: 400,
            height: 400
        }
       
    });
    	})
  
		
    
    </script>
</body>
</html>
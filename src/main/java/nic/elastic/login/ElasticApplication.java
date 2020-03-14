package nic.elastic.login;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElasticApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ElasticApplication.class, args);
	}
	
}




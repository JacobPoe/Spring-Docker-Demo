package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@RestController
@Configuration
public class WeatherController {	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@Value("${weather.key}")
	private String key;
	
	@Value("${weather.units}")
	private String units;
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping(path="/weather", produces="application/json")
	public String GetWeatherCurrent(@RequestParam String lat, @RequestParam String lon) throws IOException {
		String url = "http://api.openweathermap.org/data/2.5/weather?" +
		"lat=" + lat +
		"&lon=" + lon + 
		"&appid=" + this.key +
		"&units=" + this.units;

		HttpClient client = HttpClientBuilder.create().build();
		HttpUriRequest httpUriRequest = new HttpGet(url);
		HttpResponse response = client.execute(httpUriRequest);
		
		String json = EntityUtils.toString(response.getEntity());
				
		return json;
	}
	
	@CrossOrigin(origins = "http://localhost:9000")
	@GetMapping("/key") 
	public String GetKey() {
		return this.key;
	}
}
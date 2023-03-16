package com.example.test.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    
    private final OkHttpClient httpClient = new OkHttpClient();
    private final String RAPID_API_URL = "https://weatherapi-com.p.rapidapi.com/forecast.json";
    private final String API_KEY = "275180157emsh42a28c17836e87ep102545jsn60ae89570c9e";
    private final String RAPID_API_URL_HOURLY = "https://forecast9.p.rapidapi.com/rapidapi/forecast/{city}/hourly/";
    
    private static final String RAPID_API_KEY = "54a8e26f0emsh2c9f97728564f51p1354b4jsn9e71594af6d9";
    
   

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{city}")
    public ResponseEntity<String> getWeatherSummary(@PathVariable String city) throws IOException {
    	
    	
        String url = RAPID_API_URL + "?q=" + city + "&days=1";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key",API_KEY)
                .addHeader("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            return ResponseEntity.ok(responseBody);
        }
    }
    
    
    @GetMapping("/hourlyWeather/{city}")
    public ResponseEntity<String> getHourlyWeather(@PathVariable String city) throws IOException{
        String url = RAPID_API_URL_HOURLY + "?city=" + city;
        
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key",API_KEY)
                .addHeader("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            String responseBody = response.body().string();
            return ResponseEntity.ok(responseBody);
        }
        
        
    }
    
    
    
}


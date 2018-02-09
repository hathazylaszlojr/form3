package com.form3.hometest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@ContextConfiguration(classes = HometestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIT {
    static ResponseEntity<String> latestResponse = null;

    protected RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    private ObjectMapper objectMapper;

    void executeGet(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        latestResponse = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                new HttpEntity<>(new HttpHeaders()),
                String.class
        );
    }
    
    void executePost(String url, Object body) throws JsonProcessingException {
        latestResponse = restTemplate.postForEntity(
                url,
                objectMapper.writeValueAsString(body),
                String.class
        );
    }

    void executePut(String url, Object body) throws JsonProcessingException {
        latestResponse = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(objectMapper.writeValueAsString(body)),
                String.class
        );
    }

    void executeDelete(String url) throws JsonProcessingException {
        latestResponse = restTemplate.exchange(
                url, 
                HttpMethod.DELETE, 
                new HttpEntity<>(new HttpHeaders()), 
                String.class);
    }
    
    @Test
    public void emptyTest() {
        // This method is created to avoid errors and warnings when running the tests
    }
    
}
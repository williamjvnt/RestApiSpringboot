package com.example.RestApi.Service.ServiceKC;


import com.example.RestApi.config.KeycloakProperties;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RegisterKeycloak {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    RestTemplate restTemplate;

    @Transactional
    public ResponseEntity<String> registerKeycloak(Map<String,String> credentials, String auth) {
        String url = keycloakProperties.getRegisterKeycloakUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", auth);

        HttpEntity<Map<String,String>> entity = new HttpEntity<>(credentials,headers);

        return restTemplate.postForEntity(url, entity, String.class);
    }

    @Transactional
    public ResponseEntity<String> registerTestingKeycloak(Map<String,String> credentials) {
        String url = keycloakProperties.getRegisterTestingKeycloakUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials,headers);

        return restTemplate.postForEntity(url, entity, String.class);
    }
}

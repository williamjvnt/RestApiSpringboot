package com.example.RestApi.Service.ServiceKC;

import com.example.RestApi.config.KeycloakProperties;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestingKeycloakService {
    @Autowired
    KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public ResponseEntity<String> testingKeycloak(){
        String url = keycloakProperties.getTestingKeycloakUrl();

        return restTemplate.getForEntity(url, String.class);
    }
}

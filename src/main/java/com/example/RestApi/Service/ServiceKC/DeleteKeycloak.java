package com.example.RestApi.Service.ServiceKC;


import com.example.RestApi.config.KeycloakProperties;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DeleteKeycloak {
    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    RestTemplate restTemplate;

    @Transactional
    public ResponseEntity<String> delete (Map<String,String> credentials, String auth){
        String url = keycloakProperties.getDeleteUserUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", auth);
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(credentials,headers);

        return restTemplate.postForEntity(url, entity, String.class);
    }

}

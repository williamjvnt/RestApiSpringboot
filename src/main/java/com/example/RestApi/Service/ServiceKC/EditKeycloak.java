package com.example.RestApi.Service.ServiceKC;

import com.example.RestApi.config.KeycloakProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EditKeycloak {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private KeycloakProperties keycloakProperties;

    public ResponseEntity<String> editPass (String auth, Map<String,String> credentials){
        String url = keycloakProperties.getEditUserUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",auth);

        HttpEntity<Map<String,String>> entity = new HttpEntity<>(credentials,headers);

        return restTemplate.postForEntity(url, entity, String.class);
    }
}

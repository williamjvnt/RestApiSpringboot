package com.example.RestApi.Service.ServiceKC;

import com.example.RestApi.config.KeycloakProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LogoutKeycloak {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KeycloakProperties keycloakProperties;


    public ResponseEntity<String> logoutKeycloak(Map<String,String> refreshToken){
        String url = keycloakProperties.getLogoutRestapiUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,String>> entity = new HttpEntity<>(refreshToken,headers);
        restTemplate.postForEntity(url, entity, String.class);

        return ResponseEntity.noContent().build();

    }
}

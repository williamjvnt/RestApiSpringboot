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
public class LoginKeycloak {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    //2
    @Transactional
    public ResponseEntity<String> loginKeycloak(Map<String,String> credentials){
        String url = keycloakProperties.getLoginKeycloakUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //disini kita menggabungkan headers+body menggunakna HttpEntity
        //HttpEntity = headers+body
        HttpEntity<Map<String,String>> entity = new HttpEntity<>(credentials,headers);

        //setelah sudah digabungkan, dia akan langsung ngehit ke url yang dituju.
        return restTemplate.postForEntity(url, entity, String.class);
    }
}

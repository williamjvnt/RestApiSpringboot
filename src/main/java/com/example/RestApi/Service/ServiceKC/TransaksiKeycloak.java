package com.example.RestApi.Service.ServiceKC;

import com.example.RestApi.Service.TransaksiService;
import com.example.RestApi.config.KeycloakProperties;
import com.example.RestApi.dto.Response.TransaksiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class TransaksiKeycloak {
    @Autowired
    private KeycloakProperties keycloakProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransaksiService transaksiService;

//    @Transactional
//    public TransaksiResponse transaksi(String auth, Map<String,String> credentials){
//        String url = keycloakProperties.getMakeTransaksiUrl();
//
//        try{
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization",auth);
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials,headers);
//            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
//
//            String nominal = credentials.get("nominal");
//            double num  = Double.parseDouble(nominal);
////            if(response.getStatusCode() == HttpStatus.CREATED){}
//            if(num <= 0){
//                return TransaksiResponse.builder()
//                        .Message(HttpStatus.BAD_REQUEST)
//                        .Status("Nominal Tidak Valid")
//                        .build();
//            }
//
//
//        }catch(Exception e){
//            System.err.println("Error creating user: " + e.getMessage());
//            return TransaksiResponse
//                    .builder()
//                    .Message(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .Status("Transaksi Tidak Valid")
//                    .build();
//        }
//
//
//        return ;
//    }
}

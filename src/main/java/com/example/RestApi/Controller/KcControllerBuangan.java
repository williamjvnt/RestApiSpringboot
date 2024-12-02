package com.example.RestApi.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public class KcControllerBuangan {

    //    @PostMapping("/loginKC")
//    public ResponseEntity<String> loginKC (@RequestBody Map<String, String> credentials){
//        String url = "http://localhost:8085/login";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials,headers);
//        //disini bakal ngirim hit ke backend keycloak, dan hasil nya bakal ditampung di response
//        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
//
//        return ResponseEntity.ok(response.getBody());
//    }


    //    @PostMapping("/registerKC")
//    public ResponseEntity<String> registerKC (@RequestBody Map<String, String> credentials,
//                                              @RequestHeader(value = "Authorization",required = false) String authorization) {
//        String url = "http://localhost:8085/register";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization",authorization);
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials,headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, entity,String.class);
//
//        return ResponseEntity.ok(response.getBody());
//    }

//    @PostMapping("/registerTestingKC")
//    public ResponseEntity<String> registerTestingKC (@RequestBody Map<String, String> credentials,
//                                                     @RequestHeader(value = "Authorization",required = false) String authorization) {
//        String url = "http://localhost:8085/registerTesting";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization",authorization);
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(credentials,headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, entity,String.class);
//
//        return ResponseEntity.ok(response.getBody());
//    }


    //    @PostMapping("/user")
//    public ResponseEntity<MakeUserResponse> createUser(HttpServletRequest request,
//                                                       @RequestBody UserRepresentation user){
//        return createUser(request,user);
//    }

//    @GetMapping("/testing")
//    @RolesAllowed("user")
//    public ResponseEntity<?> doTestUser(){
//        return ResponseEntity.ok("Controller doTestUser OK!");
//    }
//
//    @GetMapping("/testingAdmin")
//    @RolesAllowed("admin")
//    public ResponseEntity<?> doTestAdmin(){
//        return ResponseEntity.ok("Controller doTestAdmin OK!");
//    }
}

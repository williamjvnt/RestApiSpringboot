package com.example.RestApi.Controller;

import com.example.RestApi.Service.ServiceKC.*;
import com.example.RestApi.dto.Response.MakeUserResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
//import org.keycloak.representations.idm.UserRepresentation;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@AllArgsConstructor
@RestController
public class KCAuthController {

    private final RestTemplate restTemplate;

    private final TestingKeycloakService testingKeycloakService;

    private final LoginKeycloak loginKeycloak;

    private final RegisterKeycloak registerKeycloak;

    private final LogoutKeycloak logoutKeycloak;

    private final TransaksiKeycloak transaksiKeycloak;

    private final DeleteKeycloak deleteKeycloak;

    private final EditKeycloak editKeycloak;

    @GetMapping("/testing")
    public ResponseEntity<String> testingKC(){
        return testingKeycloakService.testingKeycloak();
    }

//    @RolesAllowed("admin-cli")
    //1
    //tempat masuk pertama saat user melakukan login.
    //disini kita menggunakan @RequestBody untuk mengambil data dari body

    @PostMapping("/loginKC")
    public ResponseEntity<String> loginKC (@RequestBody Map<String, String> credentials){
        //setelah itu kita lanjut ke service
        return loginKeycloak.loginKeycloak(credentials);
    }

    @PostMapping("/registerKC")
    public ResponseEntity<String> registerKC (@RequestBody Map<String, String> credentials,
                                              @RequestHeader(value = "Authorization",required = false) String authorization){
        return registerKeycloak.registerKeycloak(credentials,authorization);
    }

    @PostMapping("/registerTestingKC")
    public ResponseEntity<String> registerTestingKC (@RequestBody Map<String, String> credentials) {
        return registerKeycloak.registerTestingKeycloak(credentials);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutKC(@RequestBody Map<String,String> refreshToken){
        return logoutKeycloak.logoutKeycloak(refreshToken);
    }

//    @PostMapping("/transaksi")
//    public ResponseEntity<String> transaksi(@RequestHeader(value = "Authorization",required = false) String authorization,@RequestBody Map<String, String> credentials){
//        return transaksiKeycloak.transaksi(authorization, credentials);
//    }

    @PostMapping("/edit")
    public ResponseEntity<String> editPassword(@RequestHeader(value = "Authorization",required = false) String authorization,@RequestBody Map<String, String> credentials){
        return editKeycloak.editPass(authorization,credentials);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> delete(@RequestHeader(value = "Authorization",required = false) String authorization,
                                         @RequestBody Map<String,String> credentials){
        return deleteKeycloak.delete(credentials,authorization);
    }


}

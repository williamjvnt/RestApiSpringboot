package com.example.RestApi.Controller;

import com.example.RestApi.Repository.CustomerRepository;
import com.example.RestApi.Service.AuthCustomerService;
import com.example.RestApi.Service.CustomerService;
import com.example.RestApi.Service.JwtService;
import com.example.RestApi.Service.RegisterService;
import com.example.RestApi.dto.Request.AuthRequest;
import com.example.RestApi.dto.Request.RegisterRequest;
import com.example.RestApi.dto.Request.TransaksiRequest;
import com.example.RestApi.dto.Response.*;
import com.example.RestApi.entity.CustomerEntity;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthCustomerService authCustomerService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/customer/register")
    public RegisterResponse<String> register(@Valid @RequestBody RegisterRequest request){
        registerService.register(request);
        return RegisterResponse.<String>builder().message("Registrasi Akun Berhasil").build();
    }
    @PostMapping("/customer/login")
    public LoginResponse<TokenResponse> authLogin(@RequestBody AuthRequest authRequest){

        TokenResponse tokenResponse = authCustomerService.login(authRequest);
        return LoginResponse.<TokenResponse>builder().message(tokenResponse).build();
    }
    @PostMapping("/customer/logout")
    public ResponseEntity<String> logout (@RequestHeader(value = "Authorization",required = false) String authorization){
        authCustomerService.logout(authorization);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/customer/current")
    public LoginResponse<CekAuthResponse> cekAuth (@RequestHeader(value = "Authorization",required = false) String authorization){
        return LoginResponse.<CekAuthResponse>builder().message(authCustomerService.cekAuthJwt(authorization)).build();

    }

    @GetMapping("cihuy")
    public String cihuy(){
        ResponseEntity<String> response = ResponseEntity.badRequest().build();
        int code = response.getStatusCode().value();
        String ingfo = response.getStatusCode().toString();
        return ingfo;
    }


}

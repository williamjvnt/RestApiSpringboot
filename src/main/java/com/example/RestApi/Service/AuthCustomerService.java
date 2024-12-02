package com.example.RestApi.Service;

import com.example.RestApi.Repository.BlackListRepository;
import com.example.RestApi.Repository.CustomerRepository;
import com.example.RestApi.Repository.TransaksiRepository;
import com.example.RestApi.dto.Request.AuthRequest;
import com.example.RestApi.dto.Request.TransaksiRequest;
import com.example.RestApi.dto.Response.CekAuthResponse;
import com.example.RestApi.dto.Response.LoginResponse;
import com.example.RestApi.dto.Response.TokenResponse;
import com.example.RestApi.dto.Response.TransaksiResponse;
import com.example.RestApi.entity.Blacklist;
import com.example.RestApi.entity.CustomerEntity;
import com.example.RestApi.entity.TransaksiEntity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

import java.security.AuthProvider;
import java.util.UUID;

@Service
@Slf4j
public class AuthCustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BlackListRepository blackListRepository;
    @Autowired
    CustomerService customerService;

    @Autowired
    JwtService jwtService;


    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
    @Transactional
    public TokenResponse login (AuthRequest request){

        CustomerEntity cust = customerRepository.findById(request.getNoRekening()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Imagine Input Wrong Username or password dawg"));

        if(cust.getPin() == request.getPin()){
            cust.setToken(jwtService.generateToken(cust.getNama()));
            cust.setTokenExpiredAt(jwtExpiration);
            customerRepository.save(cust);

                return TokenResponse.builder()
                        .token(cust.getToken())
                        .tokenExpiredAt(cust.getTokenExpiredAt())
                        .build();
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"Imagine Input Wrong Username or password dawg");
        }
    }

    @Transactional
    public void logout(String token){
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        CustomerEntity customer = customerRepository.findByNama(username).orElseThrow();
        if(jwtService.isTokenValid(jwt, customer)){
            if(customer.getToken() != null){
                Blacklist hitam = new Blacklist();
                hitam.setListHitam(customer.getToken());
                blackListRepository.save(hitam);
            }
            customer.setToken(null);
            customer.setTokenExpiredAt(null);
            customerRepository.save(customer);

        }
    }

    @Transactional
    public CekAuthResponse cekAuthJwt (String token){

        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        CustomerEntity customer = customerRepository.findByNama(username).orElseThrow();
        if(jwtService.isTokenValid(jwt,customer)){
            return customerService.getCustomer(customer);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Gedaggedig");
        }
    }


    private Long next30Days() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    }
}

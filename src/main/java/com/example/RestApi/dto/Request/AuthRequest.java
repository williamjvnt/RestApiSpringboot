package com.example.RestApi.dto.Request;


import lombok.Data;

@Data
public class AuthRequest {
    private String noRekening;
    private int pin;
}

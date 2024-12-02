package com.example.RestApi.dto.Response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String token;
    private long  tokenExpiredAt;
}


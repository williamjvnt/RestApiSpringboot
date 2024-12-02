package com.example.RestApi.dto.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CekAuthResponse {
    private String nama;

    private double jumlahSaldo;

}

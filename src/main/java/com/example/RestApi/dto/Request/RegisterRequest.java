package com.example.RestApi.dto.Request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
@Data
public class RegisterRequest {

    @NotEmpty(message = "Nama Tidak Boleh Kosong")
    private String nama;

    @NotEmpty(message = "Tanggal Lahir Tidak Boleh Kosong")
    private String tanggalLahir;

    @NotEmpty(message = "Nomor Telfon Tidak Boleh Kosong")
    String noTelf;

    @NotNull(message = "Pin Tidak Boleh Kosong")
    private int pin;


    private double jumlahSaldo;
}

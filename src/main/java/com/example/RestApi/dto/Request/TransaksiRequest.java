package com.example.RestApi.dto.Request;

import lombok.Data;

@Data
public class TransaksiRequest {
    private String jenisTransaksi;
    private String tujuan; //klo tujuan nya kosong berarti pembayaran
    private double nominal;
}

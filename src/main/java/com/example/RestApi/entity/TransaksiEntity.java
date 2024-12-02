package com.example.RestApi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "transaksi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "noRekening", referencedColumnName = "noRekening")
    @JsonBackReference
    @JsonInclude
    private CustomerEntity customer;

    @NonNull
    private String jenisTransaksi;

    private String tujuan;

    @NonNull
    private double nominal;

    @NonNull
    private String status;
}

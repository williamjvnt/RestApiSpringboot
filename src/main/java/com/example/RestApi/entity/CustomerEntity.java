package com.example.RestApi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
public class CustomerEntity {

    @Id
    @Column(name = "noRekening")
    private String noRekening;
//  @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NonNull
    private String nama;

    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate tanggalLahir;

    @NonNull
    private String noTelf;

    @NonNull
    private int pin;

//    @NonNull
    private double jumlahSaldo;

    private String token;
    private Long tokenExpiredAt;
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<TransaksiEntity> transaksiEntityList;


}

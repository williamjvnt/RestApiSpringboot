package com.example.RestApi.Service;


import com.example.RestApi.Repository.CustomerRepository;
import com.example.RestApi.Repository.TransaksiRepository;
import com.example.RestApi.dto.Request.TransaksiRequest;
import com.example.RestApi.dto.Response.TransaksiResponse;
import com.example.RestApi.entity.CustomerEntity;
import com.example.RestApi.entity.TransaksiEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @Autowired
    JwtService jwtService;

    @Autowired
    CustomerRepository customerRepository;
    @Transactional
    public TransaksiResponse transaksi(String token,TransaksiRequest transaksiRequest){
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        CustomerEntity customer = customerRepository.findByNama(username).orElseThrow();
        if(!jwtService.isTokenValid(jwt,customer)){
            return TransaksiResponse.builder()
                    .Message(HttpStatus.UNAUTHORIZED)
                    .Status("Gatot: Gagal Total")
                    .build();
        }
//        log.info("Cihuy: "+transaksiRequest);
        if(transaksiRequest.getNominal() <=0){
            return TransaksiResponse.builder()
                    .Message(HttpStatus.BAD_REQUEST)
                    .Status("Nominal Tidak Valid")
                    .build();
        }
        if(customer.getJumlahSaldo() <=0 && customer.getJumlahSaldo() < transaksiRequest.getNominal()){
            return TransaksiResponse.builder()
                    .Message(HttpStatus.BAD_REQUEST)
                    .Status("Saldo Tidak Cukup")
                    .build();
        }
        String jenisTransaksi =transaksiRequest.getJenisTransaksi();
        TransaksiEntity transaksiEntity = new TransaksiEntity();

        if(jenisTransaksi.equals("Transfer")){
            if(!cekNoRekening(customer.getNoRekening(), transaksiRequest.getTujuan())){
                return TransaksiResponse.builder()
                        .Message(HttpStatus.BAD_REQUEST)
                        .Status("Nomor Rekening Tujuan Tidak Valid")
                        .build();
            }
            HttpStatus.BAD_REQUEST.getReasonPhrase();
            transaksiEntity.setCustomer(customer);
            transaksiEntity.setJenisTransaksi("Transfer");
            transaksiEntity.setTujuan(transaksiRequest.getTujuan());
            transaksiEntity.setNominal(transaksiRequest.getNominal());
            transaksiEntity.setStatus("Berhasil");
            double saldoTemp =  transaksiEntity.getNominal();
            customer.setJumlahSaldo( customer.getJumlahSaldo()-saldoTemp);
            customerRepository.save(customer);
            CustomerEntity penerima = customerRepository.findByNoRekening(transaksiRequest.getTujuan()).orElseThrow();
            penerima.setJumlahSaldo(customer.getJumlahSaldo()+saldoTemp);
            customerRepository.save(penerima);
        }else if(jenisTransaksi.equals("Bayar Qris")){
            transaksiEntity.setCustomer(customer);
            transaksiEntity.setJenisTransaksi("Pembayaran Qris");
            transaksiEntity.setTujuan(transaksiRequest.getTujuan());
            transaksiEntity.setNominal(transaksiRequest.getNominal());
            transaksiEntity.setStatus("Berhasil");
            double saldoTemp =  transaksiEntity.getNominal();
            customer.setJumlahSaldo( customer.getJumlahSaldo()-saldoTemp);
            customerRepository.save(customer);
        }else{
            return TransaksiResponse.builder()
                    .Message(HttpStatus.BAD_REQUEST)
                    .Status("Transaksi Tidak Valid")
                    .build();
        }
        transaksiRepository.save(transaksiEntity);

        return TransaksiResponse.builder()
                .Message(HttpStatus.OK)
                .Status("Cihuy")
                .build();
    }

    @Transactional
    public boolean cekNoRekening(String pengirim, String penerima){
        return customerRepository.findByNoRekening(penerima).isPresent() && !pengirim.equals(penerima);
    }

    @Transactional
    public List<TransaksiEntity> cekMutasi(String token){
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        CustomerEntity customer = customerRepository.findByNama(username).orElseThrow();
        String noRekening = customer.getNoRekening();

        List<TransaksiEntity> mutasi = customer.getTransaksiEntityList();
        List<TransaksiEntity> pemasukan = transaksiRepository.findAllByTujuan(noRekening);
        List<TransaksiEntity> merge = new ArrayList<TransaksiEntity>(mutasi);
        merge.addAll(pemasukan);
        return merge;
    }

}

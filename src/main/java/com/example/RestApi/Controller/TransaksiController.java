package com.example.RestApi.Controller;


import com.example.RestApi.Repository.CustomerRepository;
import com.example.RestApi.Service.JwtService;
import com.example.RestApi.Service.TransaksiService;
import com.example.RestApi.dto.Request.TransaksiRequest;
import com.example.RestApi.dto.Response.TransaksiResponse;
import com.example.RestApi.entity.CustomerEntity;
import com.example.RestApi.entity.TransaksiEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.RestApi.Repository.TransaksiRepository;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private TransaksiService transaksiService;


    @GetMapping
    public List<TransaksiEntity> getAllTransaksi(){
        return transaksiRepository.findAll();
    }
    @GetMapping("/{status}")
    public ResponseEntity<TransaksiEntity> getTransaksiByStatus(@PathVariable String status ){
        TransaksiEntity transaksi= transaksiRepository.findByStatus(status);
        return ResponseEntity.ok(transaksi);
    }

    @PostMapping("/customer")
    public TransaksiResponse makeTransaksi (@RequestHeader(value = "Authorization",required = false) String auth,@RequestBody  TransaksiRequest transaksiRequest){
        return transaksiService.transaksi(auth,transaksiRequest);
    }

    @GetMapping("/customer/cekMutasi")
    public ResponseEntity<List<TransaksiEntity>> cekMutasi  (@RequestHeader(value = "Authorization",required = false) String auth){
        List<TransaksiEntity> cihuy = transaksiService.cekMutasi(auth);

        return ResponseEntity.ok(cihuy);
    }

    @PostMapping
    public TransaksiEntity createTranskasi(@RequestBody TransaksiEntity transaksiEntity){
        return transaksiRepository.save(transaksiEntity);
    }

    @PostMapping("/create-many")
    public ResponseEntity<List<TransaksiEntity>> createMany(@RequestBody List<TransaksiEntity> transaksiEntity){
        List<TransaksiEntity> transaksi = transaksiRepository.saveAll(transaksiEntity);
        return ResponseEntity.ok(transaksi);
    }
}

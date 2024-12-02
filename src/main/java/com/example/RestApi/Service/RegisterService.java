package com.example.RestApi.Service;

import com.example.RestApi.Repository.CustomerRepository;
import com.example.RestApi.dto.Request.RegisterRequest;
import com.example.RestApi.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class RegisterService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void register(RegisterRequest request){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
        LocalDateTime now = LocalDateTime.now();
        String rek = dtf.format(now);
        CustomerEntity customer = new CustomerEntity();
        customer.setNoRekening(rek);
        customer.setNama(request.getNama());
        customer.setRole("nasabah");
        customer.setTanggalLahir(LocalDate.parse(request.getTanggalLahir()));
        customer.setNoTelf(request.getNoTelf());
        customer.setPin(request.getPin());
        if(request.getJumlahSaldo() == 0 || request.getJumlahSaldo() < 1000000.00){
            customer.setJumlahSaldo(1000000.00);
        }else{
            customer.setJumlahSaldo(request.getJumlahSaldo());
        }
        customerRepository.save(customer);
    }
}

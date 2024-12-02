package com.example.RestApi.Service;

import com.example.RestApi.dto.Response.CekAuthResponse;
import com.example.RestApi.dto.Request.RegisterRequest;
import com.example.RestApi.entity.CustomerEntity;
import com.example.RestApi.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerEntity> getAllUsers(){
        return customerRepository.findAll();
    }

    public Optional<CustomerEntity> getCustById(String id){
        return customerRepository.findById(id);
    }

    public CustomerEntity createCust(CustomerEntity cust){
        return customerRepository.save(cust);
    }
    public List<CustomerEntity> createManyCust(List<CustomerEntity> cust) {
        return customerRepository.saveAll(cust);
    }
    public CustomerEntity updateTransaksi(String id, CustomerEntity updatedCustomer){
        Optional<CustomerEntity> exist = customerRepository.findById(id);
        if(exist.isPresent()){
            CustomerEntity customer = exist.get();
            customer.setNama(updatedCustomer.getNama());
            customer.setTanggalLahir(updatedCustomer.getTanggalLahir());
            customer.setNoTelf(updatedCustomer.getNoTelf());

            return customerRepository.save(customer);
        }else{
            throw new RuntimeException("Transaksi not found with ID: " + id);
        }
    }

    public CekAuthResponse getCustomer(CustomerEntity customer){
        return CekAuthResponse.builder()
                .nama(customer.getNama())
                .jumlahSaldo(customer.getJumlahSaldo())
                .build();
    }

    public void deleteCust(String id){
        customerRepository.deleteById(id);
    }

}

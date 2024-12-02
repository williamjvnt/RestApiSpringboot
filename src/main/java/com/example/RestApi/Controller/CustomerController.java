package com.example.RestApi.Controller;

import com.example.RestApi.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.RestApi.Service.CustomerService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerEntity> getAllUsers(){
        return customerService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustById(@PathVariable String id){
        CustomerEntity cust = customerService.getCustById(id).orElseThrow();
        return ok(cust);
    }

    public ResponseEntity<String> createCust(@RequestBody CustomerEntity cust){

        customerService.createCust(cust);
        return ResponseEntity.ok("jadi gini lee");
    }

    @PostMapping("/create-many")

    public ResponseEntity<List<CustomerEntity>> createMany(@RequestBody List<CustomerEntity> cust){
        customerService.createManyCust(cust);
        log.info("cihuy");
        return ok(cust);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCust(@PathVariable String id){
        customerService.deleteCust(id);
        return ResponseEntity.noContent().build();
    }
}

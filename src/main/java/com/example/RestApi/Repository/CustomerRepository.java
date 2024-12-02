package com.example.RestApi.Repository;

import com.example.RestApi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String>
{
    Optional<CustomerEntity> findFirstByToken(String token);

    Optional<CustomerEntity> findByNoRekening(String noRekening);

    Optional<CustomerEntity> findByNama(String nama);
}


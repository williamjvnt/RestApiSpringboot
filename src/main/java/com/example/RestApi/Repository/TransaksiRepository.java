package com.example.RestApi.Repository;


import com.example.RestApi.entity.TransaksiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<TransaksiEntity,Integer> {
    TransaksiEntity findByStatus(String status);
    List<TransaksiEntity> findAllByTujuan(String noRekening);
}

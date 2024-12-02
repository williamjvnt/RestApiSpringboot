package com.example.RestApi.Repository;

import com.example.RestApi.entity.Blacklist;
import com.example.RestApi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<Blacklist,String> {

}

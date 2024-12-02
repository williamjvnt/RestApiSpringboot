    package com.example.RestApi.entity;


    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Entity
    @Table(name ="blacklist")
    @Data
    public class Blacklist {
        @Id
        @Column(name = "list_hitam", unique = true, nullable = false)
        private String listHitam;
    }

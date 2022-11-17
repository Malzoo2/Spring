package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

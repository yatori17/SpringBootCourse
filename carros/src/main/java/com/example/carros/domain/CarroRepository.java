package com.example.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {


    Iterable<Carro> findByTipo(String tipo);
}
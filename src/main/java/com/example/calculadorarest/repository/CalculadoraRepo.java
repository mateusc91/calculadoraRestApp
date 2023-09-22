package com.example.calculadorarest.repository;

import com.example.calculadorarest.entity.Expressao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculadoraRepo extends JpaRepository<Expressao, Long> {
    Optional<Expressao> findByExpressao(String expressao);
}

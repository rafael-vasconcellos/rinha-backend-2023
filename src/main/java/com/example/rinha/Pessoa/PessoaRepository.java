package com.example.rinha.Pessoa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> { 
    Page<Pessoa> findBySearchable(String searchable, Pageable pageable);
}
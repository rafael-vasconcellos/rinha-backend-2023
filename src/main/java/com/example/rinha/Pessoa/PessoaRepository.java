package com.example.rinha.Pessoa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PessoaRepository extends JpaRepository<Pessoa, UUID> { 
    @Query("SELECT p FROM Pessoa p WHERE LOWER(p.searchable) LIKE LOWER(CONCAT('%', :searchable, '%'))")
    Page<Pessoa> findAllBySearchableContaining(String searchable, Pageable pageable);
}
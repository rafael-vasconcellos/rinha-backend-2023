package com.example.rinha.Pessoas;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.rinha.Pessoas.DTO.PessoaDTO;

import jakarta.validation.Valid;

@Service
public class PessoasService { 
    public void create(@Valid PessoaDTO pessoa) {}

    public void get(UUID id) {}
    public void search(String query) {}

    public int count() { return 0; }
}
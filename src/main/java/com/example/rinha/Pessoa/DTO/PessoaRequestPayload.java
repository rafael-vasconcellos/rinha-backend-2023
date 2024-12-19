package com.example.rinha.Pessoa.DTO;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public class PessoaRequestPayload { 
    @NotEmpty
    @Length(max = 100)
    public String nome;

    @NotEmpty
    @Length(max = 32)
    public String apelido;

    @NotEmpty
    public String nascimento;

    @Length(max = 32)
    public Optional<ArrayList<String>> stack;
}

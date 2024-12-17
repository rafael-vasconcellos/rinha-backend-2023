package com.example.rinha.Pessoas.DTO;

import java.util.ArrayList;
import java.util.Optional;

import jakarta.validation.constraints.NotEmpty;

public class PessoaDTO { 
    @NotEmpty
    public String nome;
    @NotEmpty
    public String apelido;
    @NotEmpty
    public String nascimento;
    public Optional<ArrayList<String>> stack;
}

package com.example.rinha.Pessoa.DTO;

import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
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

    public PessoaRequestPayload(String nome, String apelido, String nascimento) { 
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
    }

    public PessoaRequestPayload(String nome, String apelido, String nascimento, ArrayList<String> stack) { 
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
        this.stack = Optional.of(stack);
    }
}

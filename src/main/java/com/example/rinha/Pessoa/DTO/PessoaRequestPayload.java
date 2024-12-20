package com.example.rinha.Pessoa.DTO;

import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    public ArrayList<String> stack;

    public PessoaRequestPayload(String nome, String apelido, String nascimento) { 
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
    }
}

package com.example.rinha.Pessoa.DTO;

import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;


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
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD")
    public String nascimento;

    @Size(max = 32)
    public ArrayList<String> stack;

    public PessoaRequestPayload(String nome, String apelido, String nascimento) { 
        this.nome = nome;
        this.apelido = apelido;
        this.nascimento = nascimento;
    }
}

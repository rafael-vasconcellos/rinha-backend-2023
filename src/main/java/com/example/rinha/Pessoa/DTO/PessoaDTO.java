package com.example.rinha.Pessoa.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class PessoaDTO { 
    private UUID id;

    @NotEmpty
    @Length(max = 100)
    private String nome;

    @NotEmpty
    @Length(max = 32)
    private String apelido;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @Length(max = 32)
    private ArrayList<String> stack;

    public PessoaDTO(PessoaRequestPayload pessoaRequestPayload) { 
        //this.id = UUID.randomUUID();
        this.nome = pessoaRequestPayload.nome;
        this.apelido = pessoaRequestPayload.apelido;
        this.nascimento = LocalDate.parse(pessoaRequestPayload.nascimento);

        if (pessoaRequestPayload.stack.isPresent()) {
            this.stack = pessoaRequestPayload.stack.get();
        }
    }
}

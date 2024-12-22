package com.example.rinha.Pessoa.DTO;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.rinha.Pessoa.Exceptions.StackProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;


@Getter
public class PessoaDTO { 
    @NotEmpty
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

    private String stack;

    public PessoaDTO(PessoaRequestPayload pessoaRequestPayload) { 
        this.id = UUID.randomUUID();
        this.nome = pessoaRequestPayload.nome;
        this.apelido = pessoaRequestPayload.apelido;
        this.nascimento = LocalDate.parse(pessoaRequestPayload.nascimento);

        if (pessoaRequestPayload.stack != null && !pessoaRequestPayload.stack.isEmpty()) { 
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                this.stack = objectMapper.writeValueAsString(pessoaRequestPayload.stack);
            } catch (JsonProcessingException e) {
                throw new StackProcessingException("Error processing stack");
            }
        }
    }
}

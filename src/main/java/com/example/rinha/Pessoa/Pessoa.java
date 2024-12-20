package com.example.rinha.Pessoa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.rinha.Pessoa.DTO.PessoaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name = "pessoas", schema = "public")
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "apelido", nullable = false, unique = true)
    private String apelido;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "stack", columnDefinition = "json")
    private String stack;

    @Column(name = "searchable", insertable = false, updatable = false)
    private String searchable;

    public Pessoa(PessoaDTO pessoaDTO) { 
        this.apelido = pessoaDTO.apelido;
        this.nome = pessoaDTO.nome;
        this.nascimento = pessoaDTO.nascimento;

        if (pessoaDTO.stack != null && !pessoaDTO.stack.isEmpty()) { 
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                this.stack = objectMapper.writeValueAsString(pessoaDTO.stack);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}


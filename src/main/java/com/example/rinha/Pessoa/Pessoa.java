package com.example.rinha.Pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.rinha.Pessoa.DTO.PessoaDTO;


@Entity
@Table(name = "pessoas", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
        this.apelido = pessoaDTO.getApelido();
        this.nome = pessoaDTO.getNome();
        this.nascimento = pessoaDTO.getNascimento();
        this.stack = pessoaDTO.getStack();
        //this.id = pessoaDTO.getId();
    }

}


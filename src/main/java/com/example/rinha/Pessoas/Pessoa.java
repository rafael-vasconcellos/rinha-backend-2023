package com.example.rinha.Pessoas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pessoas", schema = "public") 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "stack", columnDefinition = "json")
    private String stack;

    @Column(name = "searchable", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que a coluna é gerada automaticamente pelo banco de dados
    private String searchable;
}


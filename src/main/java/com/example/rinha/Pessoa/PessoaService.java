package com.example.rinha.Pessoa;

import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rinha.Pessoa.DTO.PessoaDTO;
import com.example.rinha.Pessoa.DTO.PessoaRequestPayload;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Service
public class PessoaService { 
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(@Valid PessoaRequestPayload pessoaRequestPayload) 
        throws DateTimeParseException, ConstraintViolationException { 
            Pessoa pessoa = new Pessoa(new PessoaDTO(pessoaRequestPayload));
            this.pessoaRepository.save(pessoa);
            return pessoa;
    }

    public Optional<Pessoa> get(UUID id) { 
        return this.pessoaRepository.findById(id);
    }
    public Page<Pessoa> search(String query, Pageable pageable) { 
        return this.pessoaRepository.findAllBySearchableContaining(query, pageable);
    }

    public Long count() { return this.pessoaRepository.count(); }
}
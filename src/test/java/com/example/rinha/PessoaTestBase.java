package com.example.rinha;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.example.rinha.Pessoa.PessoaRepository;
import com.example.rinha.Pessoa.DTO.PessoaRequestPayload;



public abstract class PessoaTestBase {
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected PessoaRepository pessoaRepository;
    @AfterEach
    void cleanup() { pessoaRepository.deleteAll(); }


    protected PessoaRequestPayload validPessoaRequestPayload() {
        PessoaRequestPayload payload = new PessoaRequestPayload();
        payload.setNome("João");
        payload.setApelido("Teste");
        payload.setNascimento("2000-01-01");
        return payload;
    }
}

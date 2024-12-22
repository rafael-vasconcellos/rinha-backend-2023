package com.example.rinha.Pessoa;

import java.net.URI;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rinha.Pessoa.DTO.PessoaDTO;
import com.example.rinha.Pessoa.DTO.PessoaRequestPayload;
import jakarta.validation.Valid;


@RestController
public class PessoaController { 
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<?> createPessoa(@RequestBody @Valid PessoaRequestPayload pessoaRequestPayload) { 
        try { 
            PessoaDTO pessoaDTO = new PessoaDTO(pessoaRequestPayload);
            var pessoa = this.pessoaService.create(pessoaDTO);
            URI location = URI.create("/pessoas/" + pessoa.getId().toString());
            return ResponseEntity.created(location).build();

        } catch (DateTimeParseException e) { return ResponseEntity.unprocessableEntity().build(); }
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<?> getPessoa(@PathVariable UUID id) { 
        Optional<?> pessoa = this.pessoaService.get(id);
        if (pessoa.isPresent()) { return ResponseEntity.ok(pessoa.get()); }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pessoas")
    public ResponseEntity<?> searchPessoa(@RequestParam String t) { 
        Pageable pageable = PageRequest.of(0, 50);
        Page<?> pagePessoas = this.pessoaService.search(t, pageable);
        ArrayList<?> pessoas = new ArrayList<>(pagePessoas.getContent());
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<?> count() { 
        Long count = this.pessoaService.count();
        return ResponseEntity.ok(count);
    }
}

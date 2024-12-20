package com.example.rinha;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.rinha.Pessoa.DTO.PessoaRequestPayload;

import static org.assertj.core.api.Assertions.assertThat;

public class PessoaCreationTest extends PessoaTestBase { 
    @Test
	public void shouldCreatePessoa() { 
		ResponseEntity<Void> createdResponse = restTemplate.postForEntity("/pessoas", validPessoaRequestPayload(), Void.class);
		assertThat(createdResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdResponse.getHeaders().getLocation()).isNotNull();
	}

    @Test
	public void shouldNotCreatePessoa() { 
		ResponseEntity<Void> response = makePostRequest(null, "Teste", "2000-01-01");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

		restTemplate.postForEntity("/pessoas", validPessoaRequestPayload(), Void.class);
		ResponseEntity<Void> createdResponse = restTemplate.postForEntity("/pessoas", validPessoaRequestPayload(), Void.class);
		assertThat(createdResponse.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	private ResponseEntity<Void> makePostRequest(String nome, String apelido, String nascimento) { 
		PessoaRequestPayload pessoaRequestPayload = new PessoaRequestPayload(nome, apelido, nascimento);
		return restTemplate.postForEntity("/pessoas", pessoaRequestPayload, Void.class);
	}

}

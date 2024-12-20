package com.example.rinha;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.rinha.Pessoa.Pessoa;
import com.example.rinha.Pessoa.DTO.PessoaRequestPayload;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RinhaApplicationTests extends PessoaTestBase { 
	@Test
	public void shouldGetPessoaById() { 
		ResponseEntity<Optional<?>> createdResponse = restTemplate.exchange(
			"/pessoas",
			HttpMethod.POST,
			new HttpEntity<PessoaRequestPayload>(validPessoaRequestPayload()),
			new ParameterizedTypeReference<Optional<?>>() {}
		);
		URI location = createdResponse.getHeaders().getLocation();

		if (location == null) { 
			LocationHeaderNotSet<?> locationHeaderNotSet = new LocationHeaderNotSet<>("Location header not set", createdResponse.getHeaders().toString(), createdResponse.getStatusCode(), createdResponse.getBody());
			fail(locationHeaderNotSet.toString());
			return; 
		}

        ResponseEntity<Pessoa> getResponse = restTemplate.getForEntity(location.toString(), Pessoa.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
	}

	@SuppressWarnings("null")
	@Test
	public void shouldFindPessoaByTerm() { 
		restTemplate.postForEntity("/pessoas", validPessoaRequestPayload(), Void.class);
        ResponseEntity<ArrayList<Pessoa>> response = restTemplate.exchange(
            "/pessoas?t=Teste",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ArrayList<Pessoa>>() {}
        );


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSizeGreaterThan(0);
        assertThat(response.getBody().get(0).getNome()).contains("João");
	}

	@Test
	public void shouldNotFindPessoaByTerm() { 
		ResponseEntity<ArrayList<Pessoa>> response = restTemplate.exchange(
			"/pessoas?t=Teste2",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<ArrayList<Pessoa>>() {}
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasSize(0);
	}

	@Test
	public void shouldNotFindPessoaById() { 
		ResponseEntity<?> response = restTemplate.getForEntity("/pessoas/00000000-0000-0000-0000-000000000000", Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}

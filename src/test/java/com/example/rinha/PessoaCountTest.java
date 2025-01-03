package com.example.rinha;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaCountTest extends PessoaTestBase { 
    @Test
	public void shouldGetCount() { 
		ResponseEntity<Long> response = restTemplate.getForEntity("/contagem-pessoas", Long.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}
	
}

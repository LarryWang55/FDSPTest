package com.ford.code1.example1.acceptance.greeting;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static com.ford.code1.example1.TestUtil.get;
import static org.assertj.core.api.Assertions.assertThat;

public class GreetingsAcceptanceTest {

	WebClient webClient;

	@Before
	public void setup() {
		webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

	@Test
	public void testGetGreetingsEndpoint() throws Exception {
		ClientResponse clientResponse = get(webClient, "/api/v1/greetings");
		assertThat(clientResponse.statusCode().is2xxSuccessful()).isTrue();
	}

}

package com.ford.code1.example1.acceptance;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

import static com.ford.code1.example1.TestUtil.get;
import static org.assertj.core.api.Assertions.assertThat;

public class WebSecurityAcceptanceTest {

	WebClient webClient;

	@Before
	public void setup() {
		webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

	@Test
	public void testUnknownEndpointIsUnauthorized() throws Exception {
		ClientResponse clientResponse = get(webClient, "/" + UUID.randomUUID().toString());
		assertThat(clientResponse.statusCode().is4xxClientError()).isTrue();
	}

}

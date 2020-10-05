package com.ford.code1.example1.acceptance.hello;

import com.ford.cloudnative.base.test.acceptance.AcceptanceTestUtil;
import com.ford.code1.example1.hello.api.HelloRequest;
import com.ford.code1.example1.hello.api.HelloResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static com.ford.code1.example1.TestUtil.get;
import static com.ford.code1.example1.TestUtil.post;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloAcceptanceTest {

	WebClient webClient;

	@Before
	public void setup() {
		webClient = AcceptanceTestUtil.webClientBuilder().build();
	}

	@Test
	public void testGetHelloEndpoint() throws Exception {
		ClientResponse clientResponse = get(webClient, "/api/v1/hello");
		HelloResponse response = clientResponse.bodyToMono(HelloResponse.class).block();
		assertThat(clientResponse.statusCode().is2xxSuccessful()).isTrue();
		assertThat(response.getResult().getGreeting()).isEqualTo("Hello");
	}

	@Test
	public void testPostHelloEndpoint() throws Exception {
		HelloRequest helloRequest = HelloRequest.builder().name("NAME").build();
		HelloResponse helloResponse = (HelloResponse) post(webClient, "/api/v1/hello", helloRequest, HelloResponse.class);
		assertThat(helloResponse.getResult().getGreeting()).isEqualTo("Hello NAME");
	}

}

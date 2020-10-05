package com.ford.code1.example1.greeting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ford.code1.example1.greeting.api.CreateGreetingRequest;
import com.ford.code1.example1.greeting.api.CreateGreetingResponse;
import com.ford.code1.example1.greeting.api.GreetingApi;
import com.ford.code1.example1.greeting.api.GreetingResponse;
import com.ford.code1.example1.greeting.api.GreetingsResponse;

@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerTest {

	@Mock
	GreetingService greetingService;

	@Mock
	GreetingMapper greetingMapper;

	@InjectMocks
	GreetingController greetingController;

	@Test
	public void testGetGreeting() throws Exception {
		Greeting mockGreeting = mock(Greeting.class);
		when(greetingService.getGreeting(1L)).thenReturn(Optional.of(mockGreeting));
		GreetingApi mockGreetingApi = mock(GreetingApi.class);
		when(greetingMapper.fromGreeting(mockGreeting)).thenReturn(mockGreetingApi);

		ResponseEntity<GreetingResponse> response = greetingController.get(1L);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getError()).isNull();
		assertThat(response.getBody().getResult()).isNotNull();

		assertThat(response.getBody().getResult().getGreeting()).isEqualTo(mockGreetingApi);
	}

	@Test
	public void testGetGreetingsList() throws Exception {
		List<Greeting> mockGreetings = mock(List.class);
		when(greetingService.getAllGreetings()).thenReturn(mockGreetings);
		List<GreetingApi> mockListGreetingApi = mock(List.class);
		when(greetingMapper.fromGreetings(mockGreetings)).thenReturn(mockListGreetingApi);

		GreetingsResponse response = greetingController.list();

		assertThat(response.getError()).isNull();
		assertThat(response.getResult()).isNotNull();

		assertThat(response.getResult().getGreetings()).isEqualTo(mockListGreetingApi);
	}

	@Test
	public void testCreateGreeting() throws Exception {
		CreateGreetingRequest greetingRequest = mock(CreateGreetingRequest.class);
		Greeting mockGreeting = mock(Greeting.class);
		Greeting mockCreatedGreeting = mock(Greeting.class);
		GreetingApi mockCreatedGreetingApi = mock(GreetingApi.class);
		when(greetingMapper.toGreeting(greetingRequest)).thenReturn(mockGreeting);
		when(greetingMapper.fromGreeting(mockCreatedGreeting)).thenReturn(mockCreatedGreetingApi);
		when(greetingService.create(mockGreeting)).thenReturn(mockCreatedGreeting);

		ResponseEntity<CreateGreetingResponse> response = greetingController.create(greetingRequest);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getError()).isNull();
		assertThat(response.getBody().getResult()).isNotNull();

		assertThat(response.getBody().getResult().getGreeting()).isEqualTo(mockCreatedGreetingApi);
	}

}

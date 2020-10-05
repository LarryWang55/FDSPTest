package com.ford.code1.example1.greeting;

import org.springframework.stereotype.Component;

import com.ford.code1.example1.greeting.api.CreateGreetingRequest;
import com.ford.code1.example1.greeting.api.GreetingApi;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GreetingMapper {

	public GreetingApi fromGreeting(Greeting greeting) {
		return GreetingApi.builder()
				.id(greeting.getId())
				.message(greeting.getMessage())
				.authorName(greeting.getAuthorName())
				.created(greeting.getCreated().toZonedDateTime())
				.build();
	}

	public Greeting toGreeting(CreateGreetingRequest greetingRequest) {
		return Greeting.builder()
				.message(greetingRequest.getMessage())
				.authorName(greetingRequest.getAuthorName())
				.build();
	}

	public List<GreetingApi> fromGreetings(List<Greeting> greetings) {
		return greetings.stream().map(this::fromGreeting).collect(Collectors.toList());
	}

}

package com.ford.code1.example1.greeting;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ford.code1.example1.greeting.api.CreateGreetingRequest;
import com.ford.code1.example1.greeting.api.CreateGreetingResponse;
import com.ford.code1.example1.greeting.api.CreateGreetingResponse.CreateGreetingResponseResult;
import com.ford.code1.example1.greeting.api.GreetingResponse;
import com.ford.code1.example1.greeting.api.GreetingResponse.GreetingResponseResult;
import com.ford.code1.example1.greeting.api.GreetingsResponse;
import com.ford.code1.example1.greeting.api.GreetingsResponse.GreetingsResponseResult;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/greetings")
public class GreetingController {
	private GreetingService greetingService;
	private GreetingMapper mapper;

	@Autowired
	public GreetingController(GreetingService greetingService, GreetingMapper mapper) {
		this.greetingService = greetingService;
		this.mapper = mapper;
	}


	@ApiOperation(value = "Get Greeting by ID", notes = "Returns a greeting for the given ID")
	@GetMapping("/{id}")
	public ResponseEntity<GreetingResponse> get(@PathVariable Long id) {
		Optional<Greeting> greeting = this.greetingService.getGreeting(id);
		if (!greeting.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		GreetingResponseResult result = GreetingResponseResult.builder()
				.greeting(this.mapper.fromGreeting(greeting.get()))
				.build();

		return ResponseEntity.ok(GreetingResponse.result(result, GreetingResponse.class));
	}


	@ApiOperation(value = "Get All Greetings", notes = "Returns all greetings")
	@GetMapping
	public GreetingsResponse list() {
		List<Greeting> allGreetings = this.greetingService.getAllGreetings();

		GreetingsResponseResult result = GreetingsResponseResult.builder()
				.greetings(this.mapper.fromGreetings(allGreetings))
				.build();

		return GreetingsResponse.result(result, GreetingsResponse.class);
	}


	@ApiOperation(value = "Create Greeting", notes = "Create a greeting based on the given input")
	@PostMapping
	public ResponseEntity<CreateGreetingResponse> create(@Valid @RequestBody CreateGreetingRequest greetingRequest) {
		Greeting newGreeting = this.mapper.toGreeting(greetingRequest);
		Greeting greetingCreated = this.greetingService.create(newGreeting);

		CreateGreetingResponseResult result = CreateGreetingResponseResult.builder()
				.greeting(this.mapper.fromGreeting(greetingCreated))
				.build();

		CreateGreetingResponse response = CreateGreetingResponse.result(result, CreateGreetingResponse.class);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}

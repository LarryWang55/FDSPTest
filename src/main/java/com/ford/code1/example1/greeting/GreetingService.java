package com.ford.code1.example1.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

	private GreetingRepository greetingRepository;

	@Autowired
	public GreetingService(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}

	public Greeting create(Greeting newGreeting) {
		newGreeting.setCreated(OffsetDateTime.now());
		return this.greetingRepository.save(newGreeting);
	}

	public List<Greeting> getAllGreetings() {
		return this.greetingRepository.findAll();
	}

	public Optional<Greeting> getGreeting(Long id) {
		return this.greetingRepository.findById(id);
	}
}

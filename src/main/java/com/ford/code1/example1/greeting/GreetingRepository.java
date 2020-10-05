package com.ford.code1.example1.greeting;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface GreetingRepository extends Repository<Greeting, Long> {
	List<Greeting> findAll();

	Greeting save(Greeting greeting);

	Optional<Greeting> findById(Long id);
}

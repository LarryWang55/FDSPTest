package com.ford.code1.example1.greeting.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import com.ford.code1.example1.greeting.api.GreetingsResponse.GreetingsResponseResult;

public class GreetingsResponse extends BaseBodyResponse<GreetingsResponseResult> {

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetingsResponseResult {
		List<GreetingApi> greetings;
	}

}

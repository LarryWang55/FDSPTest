package com.ford.code1.example1.greeting.api;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import com.ford.code1.example1.greeting.api.CreateGreetingResponse.CreateGreetingResponseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateGreetingResponse extends BaseBodyResponse<CreateGreetingResponseResult> {

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateGreetingResponseResult {
		GreetingApi greeting;
	}

}

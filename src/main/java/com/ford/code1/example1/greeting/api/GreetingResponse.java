package com.ford.code1.example1.greeting.api;

import com.ford.cloudnative.base.api.BaseBodyResponse;
import com.ford.code1.example1.greeting.api.GreetingResponse.GreetingResponseResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GreetingResponse extends BaseBodyResponse<GreetingResponseResult> {

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GreetingResponseResult {
		GreetingApi greeting;
	}

}

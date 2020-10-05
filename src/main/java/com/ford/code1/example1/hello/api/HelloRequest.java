package com.ford.code1.example1.hello.api;

import com.ford.cloudnative.annotations.WhitelistRegexValidator;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelloRequest {
	@NotNull
	@WhitelistRegexValidator(regex = "[A-Za-z0-9_.,()*?!\\-\\s]")
	String name;
}

package com.ford.code1.example1.greeting.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GreetingApi {
    Long id;
    String message;
    String authorName;
    ZonedDateTime created;
}

package com.example.SunbaseDataassignment.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
}

package com.example.springcase.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RefreshTokenRequest {
    private String refreshToken;
}

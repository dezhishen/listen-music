package com.dezhishen.music.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private String userId;
    private String token;
    private String refreshToken;
    private Long expire;
}

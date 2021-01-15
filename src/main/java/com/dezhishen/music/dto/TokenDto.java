package com.dezhishen.music.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TokenDto implements Serializable {
    private String token;
    private String refreshToken;
}

package com.nidheeshnelson.user_access.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String username;  // Could be email or phone number
    private String password;
}

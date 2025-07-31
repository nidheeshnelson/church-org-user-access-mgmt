package com.nidheeshnelson.user_access.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;  // Can be "MEMBER", "ADMIN", etc.
}

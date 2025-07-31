package com.nidheeshnelson.user_access.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;  // Role the user belongs to, e.g., "MEMBER", "ADMIN"
}

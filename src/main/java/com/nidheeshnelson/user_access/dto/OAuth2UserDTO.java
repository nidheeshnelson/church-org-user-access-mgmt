package com.nidheeshnelson.user_access.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2UserDTO {

    private String id;
    private String name;
    private String email;
    private String profilePictureUrl;
    private String provider;  // Can be Google, Facebook, etc.
}

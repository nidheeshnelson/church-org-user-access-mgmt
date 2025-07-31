package com.nidheeshnelson.user_access.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyDTO {

    private Long id;
    private String familyName;
    private String description;  // Optional, can be a family motto or purpose
    private Long userId;  // The user who is part of the family
}

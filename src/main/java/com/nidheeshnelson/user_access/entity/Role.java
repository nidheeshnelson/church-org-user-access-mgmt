package com.nidheeshnelson.user_access.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // "ADMIN", "MEMBER", etc.

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}

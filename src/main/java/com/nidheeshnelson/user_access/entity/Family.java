package com.nidheeshnelson.user_access.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String familyName;
    private String description;  // Optional, can be a family motto or purpose

    @ManyToMany(mappedBy = "families")
    private List<User> members;
}

package com.md.user.service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_user")
@Data
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME")
    private String userName;
    @Column(name = "EMAIL")
    private String email;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}

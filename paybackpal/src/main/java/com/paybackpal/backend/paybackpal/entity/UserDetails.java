package com.paybackpal.backend.paybackpal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "userDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = true)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "password", nullable = false, unique = true)
    @Size(min = 6)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;


}

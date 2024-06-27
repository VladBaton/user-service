package com.github.vladbaton.userservice.entity;

import com.github.vladbaton.userservice.constraint.Password;
import com.github.vladbaton.userservice.constraint.Username;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue
    @Column(name = "USERID")
    private Long userId;

    @Column(name = "USERNAME", unique = true, nullable = false)
    @NotNull
    @NotBlank
    @Username
    private String username;

    @Column(name = "EMAIL", unique = true, nullable = false)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull
    @NotBlank
    @Password
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

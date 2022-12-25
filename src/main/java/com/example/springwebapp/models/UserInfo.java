package com.example.springwebapp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "roles")
    private Role roles;

    public UserInfo() {
    }

    public UserInfo(String username, String password, Role roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}

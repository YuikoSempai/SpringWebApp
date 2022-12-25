package com.example.springwebapp.security;

import com.example.springwebapp.models.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserInfoDetails implements UserDetails {

    private final UserInfo userInfo;

    public UserInfoDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(userInfo.getRoles());
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}

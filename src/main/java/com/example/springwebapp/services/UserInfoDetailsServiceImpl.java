package com.example.springwebapp.services;

import com.example.springwebapp.models.UserInfo;
import com.example.springwebapp.repository.UserInfoRepository;
import com.example.springwebapp.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoDetailsServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByUsername(username);
        if (userInfoOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserInfoDetails(userInfoOptional.get());
    }
}

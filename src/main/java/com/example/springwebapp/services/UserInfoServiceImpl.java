package com.example.springwebapp.services;

import com.example.springwebapp.models.UserInfo;
import com.example.springwebapp.repository.UserInfoRepository;
import com.example.springwebapp.services.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo loadUserByUsername(String username) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByUsername(username);
        return userInfoOptional.orElse(null);
    }
}

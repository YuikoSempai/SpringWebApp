package com.example.springwebapp.services;

import com.example.springwebapp.models.UserInfo;
import com.example.springwebapp.repository.UserInfoRepository;
import com.example.springwebapp.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationServiceImpl(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void register(UserInfo userInfo) {
        userInfoRepository.save(new UserInfo(userInfo.getUsername(), passwordEncoder.encode(userInfo.getPassword()), userInfo.getRoles()));
    }
}

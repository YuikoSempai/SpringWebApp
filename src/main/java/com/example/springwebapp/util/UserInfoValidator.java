package com.example.springwebapp.util;

import com.example.springwebapp.models.UserInfo;
import com.example.springwebapp.services.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoValidator {

    private final UserInfoServiceImpl userInfoService;

    @Autowired
    public UserInfoValidator(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

    public boolean checkUserIsExists(Object target) {
        UserInfo userInfo = (UserInfo) target;
        UserInfo userInfoFromDB = userInfoService.loadUserByUsername(userInfo.getUsername());
        return userInfoFromDB != null;
    }
}

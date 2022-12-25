package com.example.springwebapp.services.interfaces;

import com.example.springwebapp.models.UserInfo;

public interface UserInfoService {

    UserInfo loadUserByUsername(String username);

}

package com.sgcai.boot.service;

import com.sgcai.boot.to.TokenTO;
import com.sgcai.boot.to.UserTO;

import java.util.List;

public interface UserService {
    List<UserTO> findAll();

    TokenTO findUserByMobile(String mobile, String pwd);

    UserTO findUserByToken(String accessToken);
}

package com.sgcai.boot.core.bootcore.service;

import com.sgcai.boot.core.bootcore.dao.UserMapper;
import com.sgcai.boot.core.bootcore.entity.User;
import com.sgcai.boot.core.bootcore.entity.UserExample;
import com.sgcai.boot.service.TokenService;
import com.sgcai.boot.service.UserService;
import com.sgcai.boot.to.BeanConvertUtils;
import com.sgcai.boot.to.TokenTO;
import com.sgcai.boot.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

@Transactional(rollbackFor = {Throwable.class})
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;


    @Override
    public List<UserTO> findAll() {
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        return BeanConvertUtils.convert(users, UserTO.class);
    }

    @Override
    public TokenTO findUserByMobile(String mobile, String pwd) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andPasswordEqualTo(pwd);
        List<User> users = userMapper.selectByExample(example);
        return !users.isEmpty() ? tokenService.createToken(users.get(0).getId()) : null;
    }

    @Override
    public UserTO findUserByToken(String accessToken) {
        TokenTO tokenTO = tokenService.getToken(accessToken);
        return tokenTO != null ? BeanConvertUtils.convert(userMapper.selectByPrimaryKey(tokenTO.getUserId()), UserTO.class) : null;
    }
}

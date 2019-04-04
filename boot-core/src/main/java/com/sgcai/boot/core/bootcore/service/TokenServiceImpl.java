package com.sgcai.boot.core.bootcore.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sgcai.boot.core.bootcore.utils.Utils;
import com.sgcai.boot.service.TokenService;
import com.sgcai.boot.to.TokenTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(version = "1.0.0")
@Transactional(rollbackFor = {Throwable.class})
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenTO createToken(String userId) {
        TokenTO tokenTO = tokenRepository.getToken(userId);
        if (tokenTO != null && !StringUtils.isBlank(tokenTO.getAccessToken())) {
            tokenRepository.delete(tokenTO.getAccessToken());
        }
        tokenTO = new TokenTO();
        tokenTO.setAccessToken(Utils.generateUUID());
        tokenTO.setExpiredIn(TokenRepository.ACCESS_TOKEN_EXPIRED_SECOND);
        tokenTO.setRefreshToken(Utils.generateUUID());
        tokenTO.setUserId(userId);
        tokenRepository.save(tokenTO);
        return tokenTO;
    }

    @Override
    public TokenTO getToken(String accessToken) {
        return tokenRepository.getTokenByAccessToken(accessToken);
    }

    @Override
    public void deleteToken(String accessToken) {
        tokenRepository.delete(accessToken);
    }

    @Override
    public TokenTO refreshToken(String refreshToken) {
        return tokenRepository.getTokenByRefreshToken(refreshToken);
    }
}

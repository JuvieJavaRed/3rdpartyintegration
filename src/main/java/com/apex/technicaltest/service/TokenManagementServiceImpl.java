package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.AccessToken;
import com.apex.technicaltest.entities.TokenManagement;
import com.apex.technicaltest.exceptions.FailedCallException;
import com.apex.technicaltest.repository.TokenManagementRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManagementServiceImpl implements TokenManagementService{
    private final TokenManagementRepository tokenManagementRepository;

    public TokenManagementServiceImpl(TokenManagementRepository tokenManagementRepository){
        this.tokenManagementRepository = tokenManagementRepository;
    }
    /**
     * method to add valid access token to database on intial login
     *
     * @param accessToken
     * @throws FailedCallException the bank transaction validation exception
     */
    @Override
    public void logToken(AccessToken accessToken) {
        //check if email with said token exists already
        boolean tokenExists = tokenManagementRepository.existsById(accessToken.getUsername());
        if(tokenExists){
            updateToken(accessToken);
        }else{
           tokenManagementRepository.save(new TokenManagement(accessToken.getUsername(), accessToken.getExpires_in(),accessToken.getAccess_token(), accessToken.getRefresh_token(), new Date()));
        }
    }

    @Override
    public void updateToken(AccessToken accessToken) {
        //purely update the token
        TokenManagement tokenManagement = tokenManagementRepository.getById(accessToken.getUsername());
        tokenManagement.setAccessToken(accessToken.getAccess_token());
        tokenManagement.setRefreshToken(accessToken.getRefresh_token());
        tokenManagement.setEmail(accessToken.getUsername());
        tokenManagement.setExpiration(accessToken.getExpires_in());
        tokenManagement.setLastUpdate(new Date());
        tokenManagementRepository.save(tokenManagement);
    }

    @Override
    public boolean isTokenValid(String email){
        boolean isValid = false;
        boolean tokenExists = tokenManagementRepository.existsById(email);
        if(tokenExists){
            TokenManagement tokenManagement = tokenManagementRepository.getById(email);
            long differenceInTime = calculateTimeDifference(tokenManagement.getLastUpdate());
            isValid = hasElapased(differenceInTime, tokenManagement.getExpiration());
        }
        return isValid;
    }

    public Long calculateTimeDifference(Date lastUpdated){
        return new Date().getTime() - lastUpdated.getTime();
    }

    public boolean hasElapased(Long differenceInTime, int expiration){
        return (expiration > differenceInTime) ? true : false; // if expiration is greater then the token has not expired.
    }
}

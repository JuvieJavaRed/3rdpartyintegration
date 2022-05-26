package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.AccessToken;
import com.apex.technicaltest.exceptions.FailedCallException;

/**
 * This is an interface to define services that outline how to maintain a session
 */
public interface TokenManagementService {
    /**
     * method to add valid access token to database on intial login
     * @param accessToken
     * @throws FailedCallException the bank transaction validation exception
     */
    public void logToken(AccessToken accessToken);
    /**
     * method to update valid access token to database on intial login
     * @param accessToken
     * @throws FailedCallException the bank transaction validation exception
     */
    public void updateToken(AccessToken accessToken);
    /**
     * method to check if current token is valid
     * @param email
     * @throws FailedCallException the bank transaction validation exception
     */
    public boolean isTokenValid(String email);

}

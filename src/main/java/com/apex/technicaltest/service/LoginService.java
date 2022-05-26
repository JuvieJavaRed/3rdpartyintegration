package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.AccessToken;
import com.apex.technicaltest.dtos.Client;
import com.apex.technicaltest.exceptions.FailedCallException;

/**
 * This is an interface to define services for auth
 */
public interface LoginService {
    /**
     * method to process input file and create output file
     * @param client
     * @return AccessToken
     * @throws FailedCallException the failed to make a call exception
     */
    public boolean login(Client client);
}

package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.Accounts;
import com.apex.technicaltest.exceptions.FailedCallException;

import java.util.List;

/**
 * This is an interface to define services that are related to how we treat accounts
 */
public interface AccountsService {
    /**
     * method to retrieve all accessible accounts
     *
     * @throws FailedCallException the bank transaction validation exception
     */
    public List<Accounts> retrieveAccessibleAccounts();
}

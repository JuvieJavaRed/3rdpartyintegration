package com.apex.technicaltest.controller;

import com.apex.technicaltest.constants.LogConstants;
import com.apex.technicaltest.exceptions.FailedLoginException;
import com.apex.technicaltest.service.AccountsService;
import com.apex.technicaltest.service.TokenManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*") //wild card for now but security wise the requesting address should be specified
public class AccountsController {

    private final AccountsService accountsService;

    private final TokenManagementService tokenManagementService;

    public AccountsController(AccountsService accountsService, TokenManagementService tokenManagementService){
        this.accountsService = accountsService;
        this.tokenManagementService = tokenManagementService;
    }

    @GetMapping("/v1/api/pullaccessibleaccounts")
    public ResponseEntity<?> retrieveAccessibleAccounts(@RequestHeader(name = "X-COM-EMAIL", required = true) String email){
        log.info("Now retriving all accessible accounts.....");
        if(!tokenManagementService.isTokenValid(email)){
            throw new FailedLoginException(LogConstants.ERROR_LOG_FAILED_LOGIN_EXCEPTION);
        }
        return new ResponseEntity<>(accountsService.retrieveAccessibleAccounts(), HttpStatus.OK);
    }
}

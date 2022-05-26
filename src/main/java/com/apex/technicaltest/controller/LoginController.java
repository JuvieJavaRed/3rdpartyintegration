package com.apex.technicaltest.controller;

import com.apex.technicaltest.dtos.Client;
import com.apex.technicaltest.dtos.Message;
import com.apex.technicaltest.service.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@CrossOrigin(origins = "*") //wild card for now but security wise the requesting address should be specified
public class LoginController {

    private final LoginServiceImpl loginService;

    public LoginController(LoginServiceImpl loginService){
        this.loginService = loginService;
    }

    @PostMapping("/v1/api/login")
    public ResponseEntity<?> getAuthenticated(@RequestBody @Validated Client client){
        log.info("Now procressing request for user :"+client.getUsername());
        loginService.login(client);
        return new ResponseEntity<>(new Message("Successfully Logged In", new Date()), HttpStatus.OK);
    }
}

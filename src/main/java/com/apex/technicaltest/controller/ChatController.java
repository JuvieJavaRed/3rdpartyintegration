package com.apex.technicaltest.controller;

import com.apex.technicaltest.constants.LogConstants;
import com.apex.technicaltest.dtos.Chat;
import com.apex.technicaltest.dtos.Message;
import com.apex.technicaltest.dtos.TagRequest;
import com.apex.technicaltest.exceptions.FailedLoginException;
import com.apex.technicaltest.service.ChatService;
import com.apex.technicaltest.service.TokenManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@CrossOrigin(origins = "*") //wild card for now but security wise the requesting address should be specified
public class ChatController {

    private final ChatService chatService;

    private final TokenManagementService tokenManagementService;

    //constructor based injection.................
    public ChatController(ChatService chatService, TokenManagementService tokenManagementService){
        this.chatService = chatService;
        this.tokenManagementService = tokenManagementService;
    }

    @GetMapping("/v1/api/getlastestchat/{accountId}")
    public ResponseEntity<?> getLatestChat(@RequestHeader(name = "X-COM-EMAIL", required = true) String email, @PathVariable("accountId") int accountId){
        log.info("Received request to retrieve chat for accountId "+accountId);
        if(!tokenManagementService.isTokenValid(email)){
            throw new FailedLoginException(LogConstants.ERROR_LOG_FAILED_LOGIN_EXCEPTION);
        }
        return new ResponseEntity<>(chatService.findLatestChat(accountId), HttpStatus.OK);
    }

    @PutMapping("/v1/api/updatetag/{accountId}")
    public ResponseEntity<?> updateTag(@RequestHeader(name = "X-COM-EMAIL", required = true) String email, @PathVariable("accountId") int accountId, @Validated @RequestBody TagRequest tagRequest){
        log.info("Received request to update account "+accountId+" and tag the chat");
        if(!tokenManagementService.isTokenValid(email)){
            throw new FailedLoginException(LogConstants.ERROR_LOG_FAILED_LOGIN_EXCEPTION);
        }
        Chat chat = chatService.findLatestChat(accountId);
        boolean update = chatService.tagLatestChat(tagRequest,accountId, chat.getId());
        if(update){
            return new ResponseEntity<>(new Message("The chat has been successfully tagged", new Date()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("The chat has not been successfully tagged", new Date()), HttpStatus.EXPECTATION_FAILED);
    }
}

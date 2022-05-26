package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.AccessToken;
import com.apex.technicaltest.dtos.Client;
import com.apex.technicaltest.exceptions.FailedCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{

    @Value("${baseUrl}")
    private String url;

    @Value("${loginUrl}")
    private String login;

    private final WebClient webClient;
    private final TokenManagementService tokenManagementService;

    public LoginServiceImpl(WebClient.Builder webClientBuilder, TokenManagementService tokenManagementService){
        this.webClient = webClientBuilder.baseUrl(url).build();
        this.tokenManagementService = tokenManagementService;
    }
    /**
     * method to process input file and create output file
     *
     * @param client
     * @return AccessToken
     * @throws FailedCallException the failed to make a call exception
     */
    @Override
    public boolean login(Client client) {
        try{
            boolean processed = false;
            AccessToken accessToken = webClient.post()
                    .uri(login)
                    .bodyValue(client)
                    .retrieve()
                    .bodyToMono(AccessToken.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                    .block();

            if(!accessToken.getAccess_token().isEmpty()){
                tokenManagementService.logToken(accessToken); //save valid token for later use.
                processed = true;
            }
            return processed;
        }catch (Exception ex){
            log.error("Failed to authenticate user using the given credentials or failed to call out to service");
            throw new FailedCallException("Failed to authenticate user using the given credentials or failed to call out to service because "+ex.getMessage());
        }
    }

    public String refreshToken(AccessToken accessToken){
        //TODO call out to refresh token endpoint and get back new access token
        //TODO update database with new access token
        //TODO send back refreshed token to method that called to avoid n+1 database queries.
        return "accessToken";
    }
}

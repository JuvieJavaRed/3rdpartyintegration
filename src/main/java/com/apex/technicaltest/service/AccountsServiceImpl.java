package com.apex.technicaltest.service;

import com.apex.technicaltest.constants.LogConstants;
import com.apex.technicaltest.dtos.Accounts;
import com.apex.technicaltest.exceptions.FailedCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {
    @Value("${baseUrl}")
    private String url;

    @Value("${accessibleAccountsUrl}")
    private String access;

    private String MY_SECRET_TOKEN = "";

    private final WebClient webClient;

    public AccountsServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    /**
     * method to process input file and create output file
     *
     * @throws FailedCallException the bank transaction validation exception
     */
    @Override
    public List<Accounts> retrieveAccessibleAccounts() {
        try{
           return webClient.get().uri(access)

                   .header("Authorization", "Bearer "+MY_SECRET_TOKEN)
                    .retrieve()
                    .onStatus(HttpStatus::is5xxServerError,
                           response -> Mono.error(new FailedCallException(LogConstants.ERROR_LOG_FAILED_CALL_EXCEPTION_ACCOUNTS_SERVICE_RETRIEVE_RECORDS)))
                    .bodyToFlux(Accounts.class)
                    .collectList()
                    .block();
        }catch (Exception ex){
            log.error("Error");
            throw new FailedCallException("Exception Message Here");
        }
    }
}

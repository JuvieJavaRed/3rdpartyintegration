package com.apex.technicaltest.service;

import com.apex.technicaltest.constants.LogConstants;
import com.apex.technicaltest.dtos.Chat;
import com.apex.technicaltest.dtos.TagRequest;
import com.apex.technicaltest.exceptions.FailedCallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService{
    @Value("${baseUrl}")
    private String url;

    @Value("${lastestChatForSpecificAccount}")
    private String latestChat;

    @Value("${updateChatExtension}")
    private String chatExtension;

    private String MY_SECRET_TOKEN = "";

    private final WebClient webClient;

    public ChatServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    /**
     * method to process input file and create output file
     *
     * @param accountId type int
     * @throws FailedCallException the failed to make a call exception
     * @return List
     */
    @Override
    public Chat findLatestChat(int accountId) {
        log.info("making a call out to retrieve chats for acccount : "+accountId);
        String order = "desc";
        try{
            List<Chat> chatList = webClient.get().uri(uriBuilder -> uriBuilder
                            .path(latestChat+accountId+"/")
                            .queryParam("order", order)
                            .build()
                    )
                    .header("Authorization", "Bearer "+MY_SECRET_TOKEN)
                    .retrieve()
                    .onStatus(HttpStatus::is5xxServerError,
                            response -> Mono.error(new FailedCallException(LogConstants.ERROR_LOG_FAILED_CALL_EXCEPTION_CHAT_SERVICE_LATEST_CHAT)))
                    .bodyToFlux(Chat.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                    .collectList()
                    .block();

            if(chatList.size() > 0){
                return chatList.get(0);
            }
        }catch (Exception ex){
            log.error("Failed to make the call cause :"+ex.getMessage());
            throw new FailedCallException("Failed to call");
        }
        return null;
    }

    /**
     * method to process input file and create output file
     *  @return boolean
     * @throws FailedCallException the failed to make a call exception
     */
    @Override
    public boolean tagLatestChat(TagRequest tagRequest, int accountId, int chatId) {
        boolean updatedTag = false;
        log.info("Updating account "+accountId+" chat "+chatId+" to update using the request "+tagRequest.toString());
        try{
            Chat chat = webClient.put().uri(latestChat+accountId+chatExtension+chatId)
                    .header("Authorization", "Bearer "+MY_SECRET_TOKEN)
                    .bodyValue(tagRequest)
                    .retrieve()
                    .onStatus(HttpStatus::is5xxServerError,
                            response -> Mono.error(new FailedCallException(LogConstants.ERROR_LOG_FAILED_CALL_EXCEPTION_CHAT_SERVICE_UPDATE_CHAT)))
                    .bodyToMono(Chat.class)
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                    .block();

            if(!chat.getTags().isEmpty()){
                updatedTag = true;
            }
            return updatedTag;
        }catch (Exception ex){
            throw new FailedCallException("Failed to Execute the update because : "+ex.getMessage());
        }

    }
}


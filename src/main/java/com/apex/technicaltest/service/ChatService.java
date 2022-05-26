package com.apex.technicaltest.service;

import com.apex.technicaltest.dtos.Chat;
import com.apex.technicaltest.dtos.TagRequest;
import com.apex.technicaltest.exceptions.FailedCallException;

import java.util.List;

/**
 * This is an interface to define services that are related to how we work with chats
 */
public interface ChatService {
    /**
     * method to process input file and create output file
     * @param accountId type int
     * @return Chat
     * @throws FailedCallException the failed to make a call exception
     */
    public Chat findLatestChat(int accountId);

    /**
     * method to process input file and create output file
     * @return boolean
     * @throws FailedCallException the failed to make a call exception
     */
    public boolean tagLatestChat(TagRequest tagRequest, int accountId, int chatId);
}

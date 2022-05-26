package com.apex.technicaltest.dtos;

import com.apex.technicaltest.constants.LastestEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatEvent {
    private String dateCreated;
    private LastestEvent event;
    private User user;
    private String channel;
}

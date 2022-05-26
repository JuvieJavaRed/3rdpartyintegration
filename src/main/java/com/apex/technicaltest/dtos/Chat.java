package com.apex.technicaltest.dtos;

import com.apex.technicaltest.constants.LastestEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Chat {
    private int id;
    private String dateCreated;
    private String lastUpdated;
    private User startedBy;
    private String name;
    private boolean complete;
    private User lockedBy;
    private int unread;
    private String notes;
    private List<ChatEvent> events;
    private LastestEvent lastestEvent;
    private List<String> tags;
    private String channel;
    private int version;
}

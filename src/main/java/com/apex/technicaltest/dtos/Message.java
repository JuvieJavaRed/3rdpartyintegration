package com.apex.technicaltest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Message {
    private String responseMessage;
    private Date generatedDate;
}

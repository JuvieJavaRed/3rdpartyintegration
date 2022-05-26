package com.apex.technicaltest.dtos;

import com.apex.technicaltest.constants.Notifications;
import com.apex.technicaltest.constants.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Type type;
    private List<Notifications> notifications;
}

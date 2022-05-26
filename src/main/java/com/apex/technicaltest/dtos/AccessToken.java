package com.apex.technicaltest.dtos;

import lombok.Data;
import java.util.List;

@Data
public class AccessToken {
    private String username;
    private String token_type;
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private List<String> roles;
}

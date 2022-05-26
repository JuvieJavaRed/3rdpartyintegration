package com.apex.technicaltest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenManagement {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "expiration")
    private int expiration;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "last_update")
    private Date lastUpdate;
}

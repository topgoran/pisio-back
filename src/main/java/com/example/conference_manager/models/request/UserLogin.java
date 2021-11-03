package com.example.conference_manager.models.request;

import lombok.Data;

@Data
public class UserLogin {
    private String username;
    private String password;
}

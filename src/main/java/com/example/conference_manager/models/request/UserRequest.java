package com.example.conference_manager.models.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String userType;
}

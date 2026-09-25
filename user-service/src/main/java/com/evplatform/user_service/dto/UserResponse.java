package com.evplatform.user_service.dto;

import com.evplatform.user_service.entity.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    private String phone;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
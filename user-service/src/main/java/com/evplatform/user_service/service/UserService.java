package com.evplatform.user_service.service;

import com.evplatform.user_service.dto.CreateUserRequest;
import com.evplatform.user_service.dto.UpdateUserRequest;
import com.evplatform.user_service.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUser(UUID id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(
            UUID id,
            UpdateUserRequest request
    );

    void deleteUser(UUID id);
}

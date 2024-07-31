package com.hieushin.identity_service.controller;

import com.hieushin.identity_service.dto.request.ApiResponse;
import com.hieushin.identity_service.dto.request.UserCreationRequest;
import com.hieushin.identity_service.dto.request.UserUpdateRequest;
import com.hieushin.identity_service.dto.response.UserResponse;
import com.hieushin.identity_service.entity.User;
import com.hieushin.identity_service.mapper.UserMapper;
import com.hieushin.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return userMapper.toUserResponse(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest updatedUser) {
        return userMapper.toUserResponse(userService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User with ID " + userId + " has been deleted";
    }
}
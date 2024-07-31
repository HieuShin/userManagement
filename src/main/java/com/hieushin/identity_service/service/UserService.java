package com.hieushin.identity_service.service;

import com.hieushin.identity_service.dto.request.UserCreationRequest;
import com.hieushin.identity_service.dto.request.UserUpdateRequest;
import com.hieushin.identity_service.entity.User;
import com.hieushin.identity_service.exception.AppException;
import com.hieushin.identity_service.exception.ErrorCode;
import com.hieushin.identity_service.mapper.UserMapper;
import com.hieushin.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

         userMapper.updateUser(user, request);

        userRepository.save(user);
        return user;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

package com.hieushin.identity_service.mapper;

import com.hieushin.identity_service.dto.request.UserCreationRequest;
import com.hieushin.identity_service.dto.request.UserUpdateRequest;
import com.hieushin.identity_service.dto.response.UserResponse;
import com.hieushin.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.MappingControl;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(source = "firstName", target = "firstName")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}


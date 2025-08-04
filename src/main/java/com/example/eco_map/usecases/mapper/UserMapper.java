package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto userToUserDto(User user);

    UserUpdateResponseDto userToUserUpdateResponseDto(User user);
}

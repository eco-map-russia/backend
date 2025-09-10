package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.persistence.model.UserRole;
import com.example.eco_map.usecases.dto.UserDto;
import com.example.eco_map.usecases.dto.UserUpdateResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "roles", source = "userRoles", qualifiedByName = "mapRoles")
    UserDto userToUserDto(User user);

    @Named("mapRoles")
    default List<String> mapRoles(Set<UserRole> userRoles) {
        if (userRoles == null) return Collections.emptyList();
        return userRoles.stream()
                .map(userRole -> userRole.getRole().getName().toString())
                .collect(Collectors.toList());
    }

    UserUpdateResponseDto userToUserUpdateResponseDto(User user);
}

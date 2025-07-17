package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    User toEntity(RegistrationRequestDto user);

    RegistrationResponseDto entityToRegistrationResponseDto(User user);
}

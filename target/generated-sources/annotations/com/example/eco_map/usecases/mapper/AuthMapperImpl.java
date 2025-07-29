package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.RegistrationRequestDto;
import com.example.eco_map.usecases.dto.RegistrationResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T11:29:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class AuthMapperImpl implements AuthMapper {

    @Override
    public User toEntity(RegistrationRequestDto user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setEmail( user.getEmail() );
        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setPassword( user.getPassword() );
        user1.setPhone( user.getPhone() );

        return user1;
    }

    @Override
    public RegistrationResponseDto entityToRegistrationResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();

        registrationResponseDto.setId( user.getId() );
        registrationResponseDto.setEmail( user.getEmail() );
        registrationResponseDto.setFirstName( user.getFirstName() );
        registrationResponseDto.setLastName( user.getLastName() );
        registrationResponseDto.setPhone( user.getPhone() );

        return registrationResponseDto;
    }
}

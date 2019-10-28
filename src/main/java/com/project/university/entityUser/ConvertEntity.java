package com.project.university.entityUser;

import com.project.university.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootConfiguration
public class ConvertEntity {

    @Bean
    protected ModelMapper modelMapper() {
        return new ModelMapper();
    }

    protected UserDto convertToDto(Optional<User> user) {
        return modelMapper().map(user.get(), UserDto.class);
    }

    protected User convertToEntity(UserDto userDto) {
        return modelMapper().map(userDto, User.class);
    }

    protected List<UserDto> convertUserListToUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User item : userList) {
            userDtoList.add(convertToDto(Optional.ofNullable(item)));
        }
        return userDtoList;
    }

    protected List<User> convertUserDtoListToUserList(List<UserDto> userDtoList) {
        List<User> userList = new ArrayList<>();
        for (UserDto item : userDtoList) {
            userList.add(convertToEntity(item));
        }
        return userList;
    }
}

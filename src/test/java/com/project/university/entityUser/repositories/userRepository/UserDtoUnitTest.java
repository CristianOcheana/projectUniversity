//package com.project.university.entityUser.repositories.userRepository;
//
//import com.project.university.dto.UserDto;
//import com.project.university.entityUser.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.modelmapper.ModelMapper;
//
//public class UserDtoUnitTest {
//
//    private ModelMapper modelMapper = new ModelMapper();
//
//    @Test
//    public void whenConvertUserEntityToUserDto() {
//        User user = new User();
//        user.setId(Long.valueOf(1));
//        user.setFirstName("maria");
//        user.setLastName("Tanase");
//        user.setEmail("Maria@Tanase.ro");
//        user.setPassword("1");
//        user.setRole("test");
//
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        Assert.assertEquals(user.getId(), userDto.getId(), 1);
//        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
//        Assert.assertEquals(user.getLastName(), userDto.getLastName());
//        Assert.assertEquals(user.getEmail(), userDto.getEmail());
//        Assert.assertEquals(user.getPassword(), userDto.getPassword());
//        Assert.assertEquals(user.getRole(), userDto.getRole());
//    }
//
//
//
//
//    @Test
//    public void whenConvertUserDtoToUserEntity() {
//        UserDto userDto = new UserDto();
//        userDto.setId(Long.valueOf(1));
//        userDto.setFirstName("Maria");
//        userDto.setLastName("tanase");
//        userDto.setEmail("test@test.ro");
//        userDto.setPassword("1");
//        userDto.setRole("user");
//
//        User user = modelMapper.map(userDto, User.class);
//        Assert.assertEquals(userDto.getId(), user.getId(), 2);
//        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
//        Assert.assertEquals(userDto.getLastName(), user.getLastName());
//        Assert.assertEquals(userDto.getEmail(), user.getEmail());
//        Assert.assertEquals(userDto.getPassword(), user.getPassword());
//        Assert.assertEquals(userDto.getRole(), user.getRole());
//    }
//}

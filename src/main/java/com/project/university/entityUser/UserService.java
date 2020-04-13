package com.project.university.entityUser;

import com.project.university.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ConvertEntity convertEntity;


    public UserDto save(UserDto userDto) {

        User user = convertEntity.convertToEntity(userDto);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("Student");


        return convertEntity.convertToDto(Optional.ofNullable(userRepository.save(user)));

    }


    public List<UserDto> findAll() {
        return (convertEntity.convertUserListToUserDtoList(userRepository.findAll()));
    }


    public Optional<UserDto> findById(Long id) {
        Optional<User> user = Optional.of(userRepository.findById(id).get());
        return Optional.ofNullable(convertEntity.convertToDto(user));
    }


    public void delete(UserDto userDto) {
        User user = convertEntity.convertToEntity(userDto);
        userRepository.delete(user);
    }

    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return Optional.ofNullable(convertEntity.convertToDto(user));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(email);
        BindingResult result = null;
        if (!byEmail.isPresent()) {
            result.rejectValue("email", null, "Email not found");
            throw new UsernameNotFoundException("User not found!");
        }
        User u = byEmail.get();
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), getAuthority(u));
    }

    private List getAuthority(User user) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }


    public org.springframework.security.core.userdetails.User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                authentication.getPrincipal();

        return principal;
    }

    public boolean emailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String input) {

        Path path = Paths.get("D:\\projectUniversity\\src\\main\\resources\\config\\emailValidation");

        List<String> line = null;
        try {
            line = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("no read");
            e.printStackTrace();
        }

        for (String index : line) {
            if (input.equals(index)) {
                return false;
            }
        }
        return true;

    }

}

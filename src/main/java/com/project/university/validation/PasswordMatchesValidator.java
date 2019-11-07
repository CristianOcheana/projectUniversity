package com.project.university.validation;

import com.project.university.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}

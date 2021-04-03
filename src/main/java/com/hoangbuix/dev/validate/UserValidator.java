package com.hoangbuix.dev.validate;


import com.hoangbuix.dev.entity.UserEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserEntity.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;
        ValidationUtils.rejectIfEmpty(errors, "username", "msg.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
        if (user.getId() == null) {
            ValidationUtils.rejectIfEmpty(errors, "firstName", "msg.required");
        }
//        List<UserEntity> users = userService.findByProperty("username", user.getUsername());
//        if (users != null && !users.isEmpty()) {
//            errors.rejectValue("username", "msg.username.exist");
//        }
    }
}

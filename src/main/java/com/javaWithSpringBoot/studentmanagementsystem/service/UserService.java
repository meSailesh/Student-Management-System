package com.javaWithSpringBoot.studentmanagementsystem.service;

import com.javaWithSpringBoot.studentmanagementsystem.entity.User;
import com.javaWithSpringBoot.studentmanagementsystem.exception.PasswordMissMatchException;
import com.javaWithSpringBoot.studentmanagementsystem.exception.UserAlreadyExistException;
import com.javaWithSpringBoot.studentmanagementsystem.model.UserDto;
import com.javaWithSpringBoot.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by sailesh on 1/16/22.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registerUser(UserDto userDto)  throws  Exception{
        if(!userDto.getPassword().equals(userDto.getRePassword())) {
          throw new PasswordMissMatchException();
        }

        try{
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistException();
        }
    }
}

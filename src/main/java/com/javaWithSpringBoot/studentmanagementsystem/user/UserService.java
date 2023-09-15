package com.javaWithSpringBoot.studentmanagementsystem.user;

import com.javaWithSpringBoot.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User loginCheck(String email, String password){
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }

}

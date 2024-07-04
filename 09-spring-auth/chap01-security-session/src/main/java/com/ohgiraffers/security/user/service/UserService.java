package com.ohgiraffers.security.user.service;

import com.ohgiraffers.security.user.UserRole;
import com.ohgiraffers.security.user.dao.UserRepositroy;
import com.ohgiraffers.security.user.model.dto.SignupDTO;
import com.ohgiraffers.security.user.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {


    private final PasswordEncoder encoder;

    private final UserRepositroy userRepositroy;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepositroy userRepositroy) {
        this.encoder = encoder;
        this.userRepositroy = userRepositroy;
    }

    @Transactional
    public Integer regist(SignupDTO signupDTO){

        User user = userRepositroy.findByuserId(signupDTO.getUserId());

        if(!Objects.isNull(user)){
            return null;
        }
        user = new User();
        user.setUserId(signupDTO.getUserId());
        user.setUserName(signupDTO.getUserName());
        user.setUserRole(UserRole.valueOf(signupDTO.getRole()));
        user.setPassword(encoder.encode(signupDTO.getUserPass()));
        // password 를 넘겨줄 떄 encoder 를 통해서 전달해준다.
        //


        User savedUser = userRepositroy.save(user);
        if(Objects.isNull(savedUser)){
            return 0;
        }else {
            return 1;
        }

    }


    public User findbyuserId(String username) {

        User user = userRepositroy.findByuserId(username);
        if(Objects.isNull(user)){
            return null;
        }
        return user;

    }
}

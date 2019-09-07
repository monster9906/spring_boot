package com.soul.service;

import com.soul.domain.Users;
import com.soul.domain.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-26  23:51
 */
@Service
public class UserService {
    @Autowired
    private UsersRepository userRepository;

    public Users save(Users user){
        return userRepository.save(user);
    }
}

package com.ngng.chat.user.service;

import com.ngng.chat.user.entity.User;
import com.ngng.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User read(Long userId){
        return userRepository.findById(userId).orElseThrow();
    }
}

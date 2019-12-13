package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int platformType, String siteUserId) { 
        return userRepository.findUser(platformType, siteUserId);
    }

    public User getUser(long asn) {
        return userRepository.findUser(asn);
    }
}
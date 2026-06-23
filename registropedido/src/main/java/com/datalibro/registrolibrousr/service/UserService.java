package com.datalibro.registrolibrousr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datalibro.registrolibrousr.model.User;
import com.datalibro.registrolibrousr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return repository.findByUsername(username).orElseThrow();
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}

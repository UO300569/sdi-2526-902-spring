package com.uniovi.sdi.sdi2526902spring.services;

import java.util.*;

import com.uniovi.sdi.sdi2526902spring.entities.User;
import com.uniovi.sdi.sdi2526902spring.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void init() {
    }

    public Page<User> getUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public User getUserByDni(String dni) {
        return usersRepository.findByDni(dni);
    }
    
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public void updateOriginalUser(User user) {
        usersRepository.save(user);
    }

    public Page<User> searchUsersByNameAndLastName(Pageable pageable, String searchText) {
        Page<User> users;
        if (searchText != null && !searchText.trim().isEmpty()) {
            users = usersRepository.searchByNameAndLastName(pageable, searchText);
        } else {
            users = usersRepository.findAll(pageable);
        }
        return users;
    }

}
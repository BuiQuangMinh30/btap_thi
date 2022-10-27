package com.example.crudapi.service;

import com.example.crudapi.entity.UserssEntity;
import com.example.crudapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    //@Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserssEntity u) {
        userRepository.save(u);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserssEntity findById(Integer id) {
        Optional<UserssEntity> userE = userRepository.findById(id);
        if(userE.isPresent()) {
            return userE.get();
        }
        return null;
    }

    @Override
    public List<UserssEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserssEntity> findAllByName(String name) {
        return null;
    }
}

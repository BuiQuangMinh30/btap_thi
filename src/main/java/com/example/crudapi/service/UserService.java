package com.example.crudapi.service;

import com.example.crudapi.entity.UserssEntity;

import java.util.List;

public interface UserService {
    public void saveUser(UserssEntity u);
    public void deleteUser(Integer id);
    public UserssEntity findById(Integer id);
    public List<UserssEntity> findAll();
    public List<UserssEntity> findAllByName(String name);
}

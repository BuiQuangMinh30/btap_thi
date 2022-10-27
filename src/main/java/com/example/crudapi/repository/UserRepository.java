package com.example.crudapi.repository;

import com.example.crudapi.entity.UserssEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository
        extends JpaRepository <UserssEntity, Integer> {
    List<UserssEntity> findAllByName(String name);
    List<UserssEntity> findAllByNameAndEmail(String name, String email);
    List<UserssEntity> findAllByNameOrEmail(String name, String email);
    List<UserssEntity> findAllByNameContainingIgnoreCase(String name);

    List<UserssEntity> findAllByNameOrderByEmail(String name);

}

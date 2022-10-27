package com.example.crudapi.repository;

import com.example.crudapi.entity.Product;
import com.example.crudapi.entity.UserssEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer> {
//    List<Product> findAllByName(String name);
//    List<Product> findAllByNameAndEmail(String name, String email);
//    List<Product> findAllByNameOrEmail(String name, String email);
//    List<Product> findAllByNameContainingIgnoreCase(String name);

//    List<UserssEntity> findAllByNameOrderByEmail(String name);

}

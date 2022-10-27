package com.example.crudapi.service;

import com.example.crudapi.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductService {
    public void saveProduct(Product p);
    public Product sellProduct(Integer id);
    public List<Product> findAll();
//    public List<UserssEntity> findAllByName(String name);
}

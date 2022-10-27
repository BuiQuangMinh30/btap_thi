package com.example.crudapi.controller;

import com.example.crudapi.entity.UserssEntity;
import com.example.crudapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity<List<UserssEntity>> findAllUser() {
        List<UserssEntity> lsUser = userService.findAll();
        if(lsUser.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserssEntity>>(lsUser, HttpStatus.OK);
    }

    //user/x
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserssEntity> findUserById(@PathVariable("id") Integer id) {
        UserssEntity u = userService.findById(id);
        return new ResponseEntity<UserssEntity>(u, HttpStatus.OK);
    }

    //userbyname?name=oanh
    @RequestMapping(value = "userbyname", method = RequestMethod.GET)
    public ResponseEntity<List<UserssEntity>> findAllUser(@PathParam("name") String name) {
        List<UserssEntity> lsUser = userService.findAllByName(name);
        if(lsUser.size() == 0) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserssEntity>>(lsUser, HttpStatus.OK);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<UserssEntity> saveNewUser(@RequestBody UserssEntity u) {
        userService.saveUser(u);
        return new ResponseEntity<UserssEntity>(u, HttpStatus.OK);
    }

    //http://localhost:8080/updateUser?id=1
    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public ResponseEntity<UserssEntity> updateUser(
            @PathParam("id") Integer id,
            @RequestBody UserssEntity u) {
        UserssEntity oldUser = userService.findById(id);
        oldUser.setEmail(u.getEmail());
        oldUser.setName(u.getName());
        oldUser.setPhone(u.getPhone());
        userService.saveUser(oldUser);
        return new ResponseEntity<UserssEntity>(oldUser, HttpStatus.OK);
    }

    @RequestMapping(value = "deleteuser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserssEntity> deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}

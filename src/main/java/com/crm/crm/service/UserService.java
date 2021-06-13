package com.crm.crm.service;

import com.crm.crm.entity.User;
import com.crm.crm.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isUserPresent(String email) {
        User user=userRepository.findByEmail(email);
        if(user!=null)
            return true;

        return false;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
//    public List<User> findByName(String name) {
//        return  userRepository.findByNameLike("%"+name+"%");
//    }

//    public boolean addUser(User user){
//        User userFromDb = userRepository.findByUsername(user.getUsername());
//        if (userFromDb!=null){
//            return false;
//        }
//
//
//    }

}

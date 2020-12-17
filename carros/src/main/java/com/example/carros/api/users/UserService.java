package com.example.carros.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> list = new ArrayList<>();

        for(User u: users){
            list.add(UserDTO.create(u));
        }
        return list;

    }
}

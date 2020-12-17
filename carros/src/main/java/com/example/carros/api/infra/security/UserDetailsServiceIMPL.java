package com.example.carros.api.infra.security;

import com.example.carros.api.users.User;
import com.example.carros.api.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value="userDetailsService")
public class UserDetailsServiceIMPL implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(username);

        if(user == null){
            throw new UsernameNotFoundException("User não encontrado");
        }
        return user;

        /*if(username.equals("user")){
            return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
        }else if(username.equals("admin")){
            return User.withUsername(username).password(encoder.encode("ADMIN")).roles("USER","ADMIN").build();
        }

        throw new UsernameNotFoundException("user not found");*/
    }
}

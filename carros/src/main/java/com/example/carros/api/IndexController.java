package com.example.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping() // = @RequestMapping(method = RequestMethod.GET)
    public String get(){
        return "API dos Carros";
    }

    //Detalhes do usuario
    @GetMapping("/userInfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) { //Injetar userdetails
        return user;
    }

}

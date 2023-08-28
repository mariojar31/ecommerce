package com.mariojar.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mariojar.ecommerce.model.User;
import com.mariojar.ecommerce.service.IUsuarioService;

@Controller
@RequestMapping
public class UsuarioController {

    private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/singup")
    public String createUsuario(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(User usuario){
        logger.info("Usuario registrado: {}",usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);

        return "redirect:/";
    }

    
}

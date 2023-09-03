package com.mariojar.ecommerce.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(User usuario, HttpSession session){
        logger.info("Accesos: {}", usuario);
        
        Optional<User> user=usuarioService.findByEmail(usuario.getEmail());
        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")){
            return "redirect:/admin";
            }else{
            return "redirect:/";
        }
        }else{
            logger.info("Usuario no existe");
        }
        
        return "redirect:/";
    }

    
}

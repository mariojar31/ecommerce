package com.mariojar.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariojar.ecommerce.model.User;
import com.mariojar.ecommerce.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;


    @Override
    public Optional<User> findById(Integer id) {

        return usuarioRepository.findById(id);
    }


    @Override
    public User save(User usuario) {
        return usuarioRepository.save(usuario);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }



    
    
}

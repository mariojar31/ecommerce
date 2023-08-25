package com.mariojar.ecommerce.service;

import java.util.Optional;

import com.mariojar.ecommerce.model.User;

public interface IUsuarioService {
    Optional<User> findById(Integer id);
}

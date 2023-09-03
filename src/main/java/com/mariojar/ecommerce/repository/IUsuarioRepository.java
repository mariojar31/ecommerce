package com.mariojar.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariojar.ecommerce.model.User;

@Repository
public interface IUsuarioRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);}

package com.mariojar.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariojar.ecommerce.model.Orden;
import com.mariojar.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService{
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
    
}

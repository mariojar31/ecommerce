package com.mariojar.ecommerce.service;

import java.util.List;

import com.mariojar.ecommerce.model.Orden;

public interface IOrdenService {
    List<Orden> findAll();
    Orden save (Orden orden);
    String generarNumeroOrden();
    
}

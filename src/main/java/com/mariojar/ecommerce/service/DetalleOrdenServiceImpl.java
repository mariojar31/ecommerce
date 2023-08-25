package com.mariojar.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mariojar.ecommerce.model.DetalleOrden;
import com.mariojar.ecommerce.repository.IDetalleOrdenRepository;

public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
    
    
}

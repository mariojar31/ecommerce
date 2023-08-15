package com.mariojar.ecommerce.model;

public class DetalleOrden {
    private Integer id;
    private String name;
    private double cantidad;
    private double precio;
    private double total;
    public DetalleOrden() {
    }

    
    
    public DetalleOrden(Integer id, String name, double cantidad, double precio, double total) {
        this.id = id;
        this.name = name;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleOrden [id=" + id + ", name=" + name + ", cantidad=" + cantidad + ", precio=" + precio
                + ", total=" + total + "]";
    }

    
    
}



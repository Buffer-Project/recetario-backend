package com.buffer.recetariobackend.v2.entity;

public class Ingrediente {
    private String ingrediente;
    private String cantidad;

    public Ingrediente() {
    }

    public Ingrediente(String ingrediente, String cantidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}

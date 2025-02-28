package com.example.microservicetest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}
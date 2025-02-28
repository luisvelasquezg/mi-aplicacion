package com.example.microservicetest.controller;

import com.example.microservicetest.model.Producto;
import com.example.microservicetest.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    //    private final ProductoService service;
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.listarTodos();
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public Optional<Producto> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
//        producto.setId(id);
        return productoService.guardar(producto);
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
    }

}

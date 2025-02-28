package com.example.microservicetest;

import com.example.microservicetest.model.Producto;
import com.example.microservicetest.repository.ProductoRepository;
import com.example.microservicetest.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Laptop", 1200.00, 10);
    }

    @Test
    void testListarTodos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));

        List<Producto> productos = productoService.listarTodos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Laptop", productos.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId_Existe() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Laptop", resultado.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(productoRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoService.obtenerPorId(2L);

        assertFalse(resultado.isPresent());
        verify(productoRepository, times(1)).findById(2L);
    }

    @Test
    void testGuardar() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Laptop", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testEliminar() {
        doNothing().when(productoRepository).deleteById(1L);

        productoService.eliminar(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }
}


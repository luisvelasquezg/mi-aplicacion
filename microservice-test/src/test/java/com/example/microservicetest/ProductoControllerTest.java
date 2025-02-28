package com.example.microservicetest;

import com.example.microservicetest.controller.ProductoController;
import com.example.microservicetest.model.Producto;
import com.example.microservicetest.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private Producto producto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
        producto = new Producto("Laptop", 1200.00, 10);
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDeProductos() throws Exception {
        when(productoService.listarTodos()).thenReturn(Arrays.asList(producto));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(producto.getId()))
                .andExpect(jsonPath("$[0].nombre").value(producto.getNombre()));
    }

    @Test
    void obtenerPorId_DeberiaRetornarProductoSiExiste() throws Exception {
        when(productoService.obtenerPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(producto.getId()))
                .andExpect(jsonPath("$.nombre").value(producto.getNombre()));
    }

    @Test
    void obtenerPorId_DeberiaRetornarNotFoundSiNoExiste() throws Exception {
        when(productoService.obtenerPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    void crearProducto_DeberiaGuardarYRetornarProducto() throws Exception {
        when(productoService.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Laptop\",\"precio\":1200.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(producto.getId()));
    }

    @Test
    void actualizarProducto_DeberiaActualizarYRetornarProducto() throws Exception {
        when(productoService.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Laptop\",\"precio\":1200.00}"))


                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(producto.getId()));
    }

    @Test
    void eliminarProducto_DeberiaEliminarProducto() throws Exception {
        doNothing().when(productoService).eliminar(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());

        verify(productoService, times(1)).eliminar(1L);
    }
}

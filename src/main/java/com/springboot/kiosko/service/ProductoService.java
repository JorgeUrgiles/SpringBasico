package com.springboot.kiosko.service;

import com.springboot.kiosko.entities.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> mostrarTodosProductos();

    Producto buscarPorId(Long id);

    Producto crearUnProducto(Producto producto);

    Producto  editarProducto(Producto producto, Long id);

    void eliminarProducto(Long id);

}

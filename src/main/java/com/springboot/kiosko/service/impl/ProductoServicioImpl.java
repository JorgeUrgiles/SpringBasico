package com.springboot.kiosko.service.impl;

import com.springboot.kiosko.entities.Producto;
import com.springboot.kiosko.repository.ProductoRepository;
import com.springboot.kiosko.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Producto> mostrarTodosProductos() {
        return  productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearUnProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(Producto producto, Long id) {
        Producto productobd= productoRepository.findById(id).orElse(null);

        if(productobd != null){
            productobd.setNombre(producto.getNombre());
            productobd.setPrecio(producto.getPrecio());
            return productoRepository.save(productobd);

        }

        return null;
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);

    }
}

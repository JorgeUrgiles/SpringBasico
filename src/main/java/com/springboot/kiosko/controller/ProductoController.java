package com.springboot.kiosko.controller;

import com.springboot.kiosko.entities.Producto;
import com.springboot.kiosko.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public String getAllProductos(Model model){
        List<Producto>productos = productoService.mostrarTodosProductos();
        model.addAttribute("listaProductos", productos );
        return "/productos";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioProductos(Model model){
        model.addAttribute("producto",new Producto());
        model.addAttribute("accion","/productos/nueva");
        return "/formulario";
    }

    @PostMapping("/nueva")
    public String crearnuevoProducto(@Validated @ModelAttribute Producto producto, BindingResult result){
        if(result.hasErrors()){
            return "/formulario";
        }else{
            productoService.crearUnProducto(producto);
            return "redirect:/productos";
        }
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProductos(Model model,@PathVariable Long id){
        Producto producto= productoService.buscarPorId(id);
        model.addAttribute("producto",producto);
        model.addAttribute("accion","/productos/editar/"+id);
        return "/formulario";
    }

    @PostMapping("/editar/{id}")
    public String   EditarProducto(@Validated @ModelAttribute Producto producto, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()){
            return "/formulario";
        }else{
            productoService.editarProducto(producto,id);
            return "redirect:/productos";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }


}

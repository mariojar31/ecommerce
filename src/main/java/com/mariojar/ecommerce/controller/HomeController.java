package com.mariojar.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mariojar.ecommerce.model.DetalleOrden;
import com.mariojar.ecommerce.model.Orden;
import com.mariojar.ecommerce.model.Producto;
import com.mariojar.ecommerce.model.User;
import com.mariojar.ecommerce.service.IDetalleOrdenService;
import com.mariojar.ecommerce.service.IOrdenService;
import com.mariojar.ecommerce.service.IUsuarioService;
import com.mariojar.ecommerce.service.ProductoService;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    //En esta lista almacenamos los detalles de los productos
    List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model){
        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.get(id);
        producto=optionalProducto.get();

        model.addAttribute("producto", producto);

        log.info("Id de producto enviado como parametro {}",id);
        return "usuario/producto_home";

    } 

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,  @RequestParam Integer cantidad, Model model){

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal =0;

        Optional<Producto> optionalProducto = productoService.get(id);

        List<DetalleOrden> ordenNueva=new ArrayList<DetalleOrden>();


        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("cantidad: {}", cantidad);

        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);
        detalleOrden.setId(id);

        //Verificar si el producto ya se ha añadido al carrito

        Integer idProducto=producto.getId();
        boolean añadido=detalles.stream().anyMatch(p->p.getId()==idProducto);


        if(!añadido){
             detalles.add(detalleOrden);
        }else{

            for(DetalleOrden ordenparacomparar: detalles){
                if(ordenparacomparar.getId()==idProducto){
                    ordenparacomparar.setCantidad(ordenparacomparar.getCantidad()+detalleOrden.getCantidad());
                } 
                ordenNueva.add(ordenparacomparar);
            }
            detalles=ordenNueva;  
        }
       

       

        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);


        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model){
        
        List<DetalleOrden> ordenNueva=new ArrayList<DetalleOrden>();
        
        for(DetalleOrden detalleOrden: detalles){
            if (detalleOrden.getProducto().getId()!=id){
                ordenNueva.add(detalleOrden);
            }
        }

        detalles=ordenNueva;

        double sumaTotal=0;

        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model){

        User usuario = usuarioService.findById(1).get();

        model.addAttribute("cart",detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }
    
    @GetMapping("/saveOrder")
    public String saveOrder(){
        Date fechaCreacion=new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //asignacion a usuario
        User usuario = usuarioService.findById(1).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        //Guardamos los detalles
        for(DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //Limpiar y ordenar lista 
        orden = new Orden();
        detalles.clear();


        return "redirect:/";
    }
}
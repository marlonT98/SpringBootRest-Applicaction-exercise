package com.application.rest.springBootRest.controllers;

import com.application.rest.springBootRest.controllers.dto.ProductDTO;
import com.application.rest.springBootRest.entities.Product;
import com.application.rest.springBootRest.persitence.IProductDAO;
import com.application.rest.springBootRest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    //buscamos por el id
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        //obteniendo el producto
        Optional<Product> productOptional = iProductService.findBiId(id);

        //verificamos si tenemos el producto
        //gracias al optional
        if (productOptional.isPresent()) {
            //no devolvemos el entity
            //devilvemos un objeto a partir de la entidad

            Product product = productOptional.get();

            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .maker(product.getMaker())
                    .build();

            return ResponseEntity.ok(productDTO);

        }

        //devuelve un 404
        return ResponseEntity.notFound().build();


    }

    //mostramos todo
    @GetMapping("/findAll")

    public ResponseEntity<?> findAll() {

        List<ProductDTO> productDTOList = iProductService.findAll()
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .maker(product.getMaker())
                        .build()).toList();

        return ResponseEntity.ok(productDTOList);


    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        if (productDTO.getName().isBlank() || productDTO.getPrice()==null || productDTO.getMaker()==null) {

            return ResponseEntity.badRequest().build();


        }
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .maker(productDTO.getMaker())
                .build();

        iProductService.save(product);

        try {
            return ResponseEntity.created(new URI("/api/product/save")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

        //obtenemos al producto
        Optional<Product> productOptional = iProductService.findBiId(id);

        if (productOptional.isPresent()) {
            //si el producto esta presente
            //seteamos el producto con los valores del producto pasado por parametros

            //obetenemos el producto
            Product product = productOptional.get();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setMaker(product.getMaker());

            iProductService.save(product);
            return ResponseEntity.ok("Registro actualizado");


        }

        //si no esta
        return ResponseEntity.notFound().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (id != null) {

            iProductService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado");


        }

        return ResponseEntity.notFound().build();


    }


}

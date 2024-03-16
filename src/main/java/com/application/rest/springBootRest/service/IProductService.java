package com.application.rest.springBootRest.service;

import com.application.rest.springBootRest.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll( );//un listado de productos
    Optional<Product> findBiId(Long id);//buscar un listado por su id

    List<Product> findBypriceInRange(BigDecimal minPrice , BigDecimal maxPrice);//obtener una lista de productos por el precio de su rango

    void save( Product product);//enviamos un producto a guardar
    void deleteById( Long id ); //eliminamos por el id



}

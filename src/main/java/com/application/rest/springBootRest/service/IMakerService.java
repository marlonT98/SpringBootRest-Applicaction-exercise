package com.application.rest.springBootRest.service;

import com.application.rest.springBootRest.entities.Maker;

import java.util.List;
import java.util.Optional;

public interface IMakerService {
    //aqui definimos la funionalidad de nuestra aplicacion

    List<Maker> findAll();//un listado de creadores
    Optional<Maker> findById(Long id ); //buscamos por el id

    void save (Maker maker);//guardar un fabricante
    void deleteById( Long id);//aliminar por el id a un fabricante



}

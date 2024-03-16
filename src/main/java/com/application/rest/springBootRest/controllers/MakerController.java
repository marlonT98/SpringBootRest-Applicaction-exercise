package com.application.rest.springBootRest.controllers;

import com.application.rest.springBootRest.controllers.dto.MakerDTO;
import com.application.rest.springBootRest.entities.Maker;
import com.application.rest.springBootRest.service.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    @Autowired
    private IMakerService iMakerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Optional<Maker> makerOptional = iMakerService.findById(id);

        if (makerOptional.isPresent()) {
            Maker maker = makerOptional.get();

            MakerDTO makerDTO = MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productlist(maker.getProductlist())
                    .build();

            return ResponseEntity.ok(makerDTO);
        }

        return ResponseEntity.notFound().build();


    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<MakerDTO> makerList = iMakerService.findAll()
                .stream()
                .map(maker -> MakerDTO.builder()
                        .id(maker.getId())
                        .name(maker.getName())
                        .productlist(maker.getProductlist())
                        .build()).toList();
        return ResponseEntity.ok(makerList);


    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) {

        //validando que el nombre no este vacio
        if (makerDTO.getName().isBlank()) {
            //una peticion mal formulada
            return ResponseEntity.badRequest().build();
        }

        //si no esta vacio agregamos el nombre que viene en los parametros
        iMakerService.save(Maker.builder().name(makerDTO.getName()).build());

        // devuelve una respuesta con el estado HTTP 201 (Created)
        try {
            return ResponseEntity.created(new URI("/api/maker/save")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDTO) {

        //validamos si el creador ya existe
        Optional<Maker> makeroptional = iMakerService.findById(id);
        if (makeroptional.isPresent()) {//si ya existe

            Maker maker = makeroptional.get();//recuperamos el entity
            maker.setName(makerDTO.getName());//seteando al entety lo que me esta enviando por parametros
            iMakerService.save(maker);
            return ResponseEntity.ok("Registro actualizado");
        }
        //que pasa si no existe
        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (id != null) {

            iMakerService.deleteById(id);
            return ResponseEntity.ok("Registro elimnado");
        }
        return ResponseEntity.notFound().build();
    }


}

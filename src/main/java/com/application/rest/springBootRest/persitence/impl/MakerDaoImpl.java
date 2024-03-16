package com.application.rest.springBootRest.persitence.impl;

import com.application.rest.springBootRest.entities.Maker;
import com.application.rest.springBootRest.persitence.IMakerDAO;
import com.application.rest.springBootRest.repository.MakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MakerDaoImpl implements IMakerDAO {

    @Autowired
    private MakerRepository makerRepository;


    @Override
    public List<Maker> findAll() {
        return (List<Maker>) makerRepository.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return makerRepository.findById(id);
    }

    @Override
    public void save(Maker maker) {

        makerRepository.save(maker);

    }

    @Override
    public void deleteById(Long id) {

        makerRepository.deleteById(id);

    }
}

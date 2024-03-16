package com.application.rest.springBootRest.service.impl;

import com.application.rest.springBootRest.entities.Product;
import com.application.rest.springBootRest.persitence.IProductDAO;
import com.application.rest.springBootRest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
   private IProductDAO iProductDAO;

    @Override
    public List<Product> findAll() {
        return iProductDAO.findAll();
    }

    @Override
    public Optional<Product> findBiId(Long id) {
        return iProductDAO.findBiId(id);
    }

    @Override
    public List<Product> findBypriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return iProductDAO.findBypriceInRange(minPrice , maxPrice);
    }

    @Override
    public void save(Product product) {
        iProductDAO.save(product);
    }

    @Override
    public void deleteById(Long id) {
        iProductDAO.deleteById(id);
    }
}

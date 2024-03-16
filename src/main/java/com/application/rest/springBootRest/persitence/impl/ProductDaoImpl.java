package com.application.rest.springBootRest.persitence.impl;

import com.application.rest.springBootRest.entities.Product;
import com.application.rest.springBootRest.persitence.IProductDAO;
import com.application.rest.springBootRest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements IProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findBiId(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findBypriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductByPriceInRange( minPrice , maxPrice) ;
    }

    @Override
    public void save(Product product) {

        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);
    }
}

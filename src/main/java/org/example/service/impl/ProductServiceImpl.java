package org.example.service.impl;

import org.example.base.service.impl.BaseEntityServiceImpl;
import org.example.domain.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;

import java.util.List;

public class ProductServiceImpl
        extends BaseEntityServiceImpl<Product,Long, ProductRepository>
        implements ProductService{
    public ProductServiceImpl(ProductRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Product> findByType(int typeId) {
        return baseRepository.findByType(typeId);
    }
}

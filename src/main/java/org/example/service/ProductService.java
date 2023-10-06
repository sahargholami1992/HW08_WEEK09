package org.example.service;

import org.example.base.service.BaseEntityService;
import org.example.domain.Product;

import java.util.List;

public interface ProductService extends BaseEntityService<Product,Long> {
    List<Product> findByType(int typeId);
}

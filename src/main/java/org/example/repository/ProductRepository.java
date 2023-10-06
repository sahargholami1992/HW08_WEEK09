package org.example.repository;

import org.example.base.repository.BaseEntityRepository;
import org.example.domain.Product;

import java.util.List;

public interface ProductRepository extends BaseEntityRepository<Product,Long> {
    List<Product> findByType(int typeId);
}

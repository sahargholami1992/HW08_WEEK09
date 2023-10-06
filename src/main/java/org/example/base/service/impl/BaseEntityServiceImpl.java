package org.example.base.service.impl;

import org.example.base.domain.BaseEntity;
import org.example.base.repository.BaseEntityRepository;
import org.example.base.service.BaseEntityService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class BaseEntityServiceImpl<T extends BaseEntity<ID>,ID extends Serializable,
        R extends BaseEntityRepository<T,ID>>
        implements BaseEntityService<T,ID> {

    protected final R baseRepository;

    public BaseEntityServiceImpl(R baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    @Override
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return baseRepository.update(entity);
    }

    @Override
    public boolean existsById(ID id) {
        return baseRepository.existsById(id);
    }
}

package org.example.base.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseEntityService<T,ID> {

    List<T> findAll();

    T findById(ID id);

    void deleteById(ID id);

    long count();

    T save(T entity);

    T update(T entity);

    boolean existsById(ID id);
}

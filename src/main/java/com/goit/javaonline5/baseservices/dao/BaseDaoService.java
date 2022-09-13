package com.goit.javaonline5.baseservices.dao;

import java.util.List;
import java.util.UUID;

public interface BaseDaoService<T> {

    T save(T entity);

    List<T> saveAll(List<T> entities);

    List<T> findAll();

    T findById(UUID id);

    T updateById(T entity, UUID id);

    void delete(UUID id);
}

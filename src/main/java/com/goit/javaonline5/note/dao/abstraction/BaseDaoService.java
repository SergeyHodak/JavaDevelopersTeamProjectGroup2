package com.goit.javaonline5.note.dao.abstraction;

import java.util.List;

public interface BaseDaoService<T> {

    T save(T entity);

    List<T> saveAll(List<T> entities);

    List<T> findAll();

    T findById(Long id);

    T updateById(T entity, Long id);

    void delete(Long id);
}


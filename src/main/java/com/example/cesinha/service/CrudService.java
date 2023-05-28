package com.example.cesinha.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CrudService<T, ID, R> {

    List<T> fetchAll();

    T fetchById(ID id);

    T insert(R request);

    T update(ID id, R request);

    void delete(ID id);

    ID getEntityId(T entity);
}

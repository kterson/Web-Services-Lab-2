package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {

    void create(E entity);

    Optional<E> find(K key);

    List<E> findAll();

    void delete(E entity);

}

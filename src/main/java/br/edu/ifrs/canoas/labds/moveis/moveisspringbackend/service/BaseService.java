package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();
    Optional<T> find(Long id);
    T save(T entity);
    void delete(Long id);
}

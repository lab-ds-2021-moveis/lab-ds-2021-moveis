package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    Page<T> findPage(Pageable pageable);
    List<T> findAll();
    Optional<T> find(Long id);
    T save(T entity);
    void delete(Long id);
}

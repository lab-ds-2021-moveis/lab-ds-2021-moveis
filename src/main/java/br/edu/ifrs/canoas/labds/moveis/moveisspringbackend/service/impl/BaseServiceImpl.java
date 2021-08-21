package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private JpaRepository<T, Long> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> findPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<T> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

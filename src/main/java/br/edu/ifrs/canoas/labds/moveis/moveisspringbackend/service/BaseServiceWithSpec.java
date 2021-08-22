package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BaseServiceWithSpec<T> extends BaseService<T> {
    List<T> findAllWithSpec(Specification<T> specification);
    Page<T> findPageWithSpec(Pageable pageable, Specification<T> specification);
}

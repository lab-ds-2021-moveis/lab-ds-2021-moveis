package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.BaseServiceWithSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public class BaseServiceWithSpecImpl<T> extends BaseServiceImpl<T> implements BaseServiceWithSpec<T> {

    @Autowired
    private JpaSpecificationExecutor<T> specificationExecutor;

    @Override
    public List<T> findAllWithSpec(Specification<T> specification) {
        return specificationExecutor.findAll(specification);
    }

    @Override
    public Page<T> findPageWithSpec(Pageable pageable, Specification<T> specification) {
        return specificationExecutor.findAll(specification, pageable);
    }
}

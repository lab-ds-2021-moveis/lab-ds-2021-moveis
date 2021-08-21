package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.ProductRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceWithSpecImpl<Product> implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<String> getModels() {
        return repository.getModels();
    }

    @Override
    public List<String> getManufacturers() {
        return repository.getManufacturers();
    }
}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;

import java.util.List;

public interface ProductService extends BaseServiceWithSpec<Product> {
    List<String> getModels();
    List<String> getManufacturers();
}

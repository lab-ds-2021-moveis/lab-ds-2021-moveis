package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {}

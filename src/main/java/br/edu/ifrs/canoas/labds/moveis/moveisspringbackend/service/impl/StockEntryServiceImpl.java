package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.StockEntry;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.StockEntryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StockEntryServiceImpl extends BaseServiceImpl <StockEntry> implements StockEntryService {
}

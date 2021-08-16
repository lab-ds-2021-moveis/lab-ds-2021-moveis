package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart.ChartTuple;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart.PurchaseSummary;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.ProductPurchaseRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.PurchaseRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductPurchaseRepository productPurchaseRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("UTC-3"));

    @Override
    public List<ChartTuple> purchasesSumByDay() {
        List<ChartTuple> values = new ArrayList<>();
        Map<String, Double> purchasesByDay = new HashMap<>();
        List<Purchase> purchases = purchaseRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        // Map purchase value to date
        for (Purchase purchase: purchases) {
            String day = formatter.format(purchase.getCreatedAt());

            if (!purchasesByDay.containsKey(day)) {
                purchasesByDay.put(day, 0D);
            }

            Double oldValue = purchasesByDay.get(day);
            Double newValue = oldValue + purchase.getTotalValue();
            purchasesByDay.put(day, newValue);
        }

        // Convert mapped purchases to output format
        for (String key: purchasesByDay.keySet()) {
            ChartTuple tuple = new ChartTuple();
            tuple.setLabel(key);
            tuple.setValue(purchasesByDay.get(key));
            values.add(tuple);
        }

        return values;
    }

    @Override
    public PurchaseSummary getPurchaseSummary() {
        PurchaseSummary summary = new PurchaseSummary();
        summary.setCount(purchaseRepository.count());
        summary.setValue(purchaseRepository.getSumTotalValue());
        summary.setProducts(productPurchaseRepository.getSumProductAmount());
        return summary;
    }
}

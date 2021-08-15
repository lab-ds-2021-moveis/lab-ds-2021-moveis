package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Purchase;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart.LineChartValues;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.PurchaseRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("UTC-3"));

    @Override
    public LineChartValues purchasesSumByDay() {
        LineChartValues values = new LineChartValues();
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
        values.setLabels(new ArrayList<String>(purchasesByDay.keySet()));
        values.setData(new ArrayList<Double>(purchasesByDay.values()));

        return values;
    }
}

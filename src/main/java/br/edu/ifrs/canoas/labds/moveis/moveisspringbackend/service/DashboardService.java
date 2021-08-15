package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart.LineChartValues;

public interface DashboardService {
    LineChartValues purchasesSumByDay();
}

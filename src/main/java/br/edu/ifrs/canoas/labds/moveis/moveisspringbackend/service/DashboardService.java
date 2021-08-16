package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart.ChartTuple;

import java.util.List;

public interface DashboardService {
    List<ChartTuple> purchasesSumByDay();
}

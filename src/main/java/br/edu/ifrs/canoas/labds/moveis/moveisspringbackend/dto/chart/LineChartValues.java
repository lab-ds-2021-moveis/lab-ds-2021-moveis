package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class LineChartValues implements Serializable {
    private List<String> labels = new ArrayList();
    private List<Double> data = new ArrayList<>();

    public String toString() {
        return "LineChartValues" +
            "(" +
                "labels: " + labels.toString() +
                "; data: " + data.toString() +
            ")";
    }
}

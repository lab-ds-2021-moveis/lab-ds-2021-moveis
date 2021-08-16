package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChartTuple implements Serializable {
    private String label;
    private Double value;

    public String toString() {
        return getClass().toString() +
            "(" +
                "label= " + label + ";" +
                "value= " + value.toString() +
            ")";
    }
}

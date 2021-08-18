package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto.chart;

import lombok.Data;

@Data
public class PurchaseSummary {
    private Long count = 0L;
    private Double value = 0D;
    private Long products = 0L;

    public String toString() {
        return getClass().toString() +
            "(" +
                "count= " + count.toString() + ";" +
                "value= " + value.toString() + ";" +
                "products= " + products.toString() +
            ")";
    }
}

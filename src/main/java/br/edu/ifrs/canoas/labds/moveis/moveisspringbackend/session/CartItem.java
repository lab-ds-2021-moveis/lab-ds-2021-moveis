package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long id;
    private String name;
    private Double value;
    private Long amount;

    public Double getPrice() {
        return value * amount;
    }
}

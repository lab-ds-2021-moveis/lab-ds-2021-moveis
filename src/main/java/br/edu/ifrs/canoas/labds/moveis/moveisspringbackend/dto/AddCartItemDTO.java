package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session.CartItem;
import lombok.Data;

@Data
public class AddCartItemDTO implements EntityDTO<CartItem> {

    public Long id;
    public Long amount = 1L;

    @Override
    public CartItem toEntity() {
        CartItem instance = new CartItem();
        instance.setId(id);
        instance.setAmount(amount);
        return instance;
    }
}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
public class ShoppingCart {

    public static String sessionKey = "cart";

    private Map<Long, CartItem> items = new HashMap<>();

    public Double getTotal() {
        Double value = 0.0;
        for (CartItem item : items.values()) {
            value += item.getPrice();
        }
        return value;
    }

    public List<CartItem> getList() {
        return new ArrayList<>(items.values());
    }

    public void add(CartItem cartItem) {
        Logger.getAnonymousLogger().info("ID: " + cartItem.getId().toString());
        items.put(cartItem.getId(), cartItem);
    }

    public void remove(Long id) {
        items.remove(id);
    }

    public CartItem get(Long id) {
        return items.get(id);
    }

    public void clear() {
        items.clear();
    }

    public boolean has(Long id) {
        return items.containsKey(id);
    }
}

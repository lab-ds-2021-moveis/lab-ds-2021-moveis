package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.ProductEnvironment;
import lombok.Data;

@Data
public class StoreProductDTO implements EntityDTO<Product> {

    public String name;
    public Double value;
    public String description;
    public String manufacturer;
    public String model;
    public String environment;
    public String image;

    @Override
    public Product toEntity() {
        Product instance = new Product();

        instance.setName(name);
        instance.setValue(value);
        instance.setDescription(description);
        instance.setManufacturer(manufacturer);
        instance.setModel(model);
        instance.setImage(image);
        instance.setEnvironment(ProductEnvironment.valueOf(environment));

        return instance;
    }
}

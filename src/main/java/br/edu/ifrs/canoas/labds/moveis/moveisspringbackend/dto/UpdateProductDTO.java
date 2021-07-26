package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.ProductEnvironment;
import lombok.Data;

@Data
public class UpdateProductDTO implements UpdateEntityDTO<Product> {

    public Long id;
    public String name;
    public Double value;
    public String description;
    public String manufacturer;
    public String model;
    public String image;
    public String environment;

    @Override
    public Product toEntity() {
        Product instance = new Product();

        instance.setId(id);
        instance.setName(name);
        instance.setValue(value);
        instance.setDescription(description);
        instance.setManufacturer(manufacturer);
        instance.setModel(model);
        instance.setImage(image);
        instance.setEnvironment(ProductEnvironment.valueOf(environment));

        return instance;
    }

    @Override
    public UpdateProductDTO from(Product product) {
        id = product.getId();
        name = product.getName();
        value = product.getValue();
        description = product.getDescription();
        manufacturer = product.getManufacturer();
        model = product.getModel();
        image = product.getImage();
        environment = product.getEnvironment().toString();
        return this;
    }
}

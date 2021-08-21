package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.specification;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSpecification implements Specification<Product> {

    private String name = "";
    private String model = "";
    private String manufacturer = "";

    // TODO: Permitir filtrar por ambient

    public ProductSpecification() {}

    public ProductSpecification(String name, String model, String manufacturer) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        // Busca por nome tenta encontrar por similaridade, para que não seja necessário saber exatamente o nome
        // Os outros parametros tentam encontrar um produto com exatamente o valor
        // Para não considerar algum parametro só deixar como string vazia

        if (!name.isBlank()) {
            predicates.add(builder.like(builder.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
        }

        if (!model.isBlank()) {
            predicates.add(builder.equal(root.get("model"), model));
        }

        if (!manufacturer.isBlank()) {
            predicates.add(builder.equal(root.get("manufacturer"), manufacturer));
        }

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}

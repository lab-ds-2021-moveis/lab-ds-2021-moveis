package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import lombok.Data;

@Data
public class StoreCustomerDTO implements EntityDTO<Customer> {

    public String name;
    public String cpf;
    public String phone;
    public String email;
    public String password;
    public String passwordConfirm;

    @Override
    public Customer toEntity() {
        Customer entity = new Customer();

        entity.setName(name);
        entity.setCpf(cpf);
        entity.setPhone(phone);
        entity.setEmail(email);
        entity.setPassword(password);

        return entity;
    }
}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.dto;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.enumeration.EmployeeRole;
import lombok.Data;

@Data
public class StoreEmployeeDTO implements EntityDTO<Employee> {

    public String credential;
    public String name;
    public String password;
    public String passwordConfirm;
    public String role;

    @Override
    public Employee toEntity() {
        Employee entity = new Employee();

        entity.setCredential(credential);
        entity.setName(name);
        entity.setPassword(password);
        entity.setRole(EmployeeRole.valueOf(role));

        return entity;
    }
}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;

public interface EmployeeService extends BaseService<Employee> {
    Employee findByCredential(String credential);
}

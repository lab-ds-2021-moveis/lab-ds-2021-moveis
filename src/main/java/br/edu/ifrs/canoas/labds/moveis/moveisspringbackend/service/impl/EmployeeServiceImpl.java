package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.EmployeeRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findByCredential(String credential) {
        return employeeRepository.findByCredential(credential);
    }
}

package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCredential(String credential);
}

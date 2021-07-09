package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.CustomerRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public User loadCustomerByCredential(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Customer.role));

        return new User(customer.getEmail(), customer.getPassword(), grantedAuthorities);
    }

    public User loadEmployeeByCredential(String credential) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByCredential(credential);
        if (employee == null) {
            throw new UsernameNotFoundException(credential);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(employee.getRole().toString()));

        return new User(employee.getCredential(), employee.getPassword(), grantedAuthorities);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s.contains("@")) {
            return loadCustomerByCredential(s);
        } else {
            return loadEmployeeByCredential(s);
        }
    }
}

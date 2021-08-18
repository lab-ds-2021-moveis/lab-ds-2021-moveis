package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.impl;

import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Customer;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.domain.Employee;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.CustomerRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.repository.EmployeeRepository;
import br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Based on: https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    public Object getCurrentUser() {
        String username = getLoggedInUserName();
        Class userClass = getCurrentUserClass();

        if (userClass.equals(Customer.class)) {
            return customerRepository.findByEmail(username);
        }

        if (userClass.equals(Employee.class)) {
            return employeeRepository.findByCredential(username);
        }

        return null;
    }

    @Override
    public Class getCurrentUserClass() {
        String username = getLoggedInUserName();
        if (username != null) {
            return username.contains("@") ? Customer.class : Employee.class;
        }
        return null;
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }


    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }
}

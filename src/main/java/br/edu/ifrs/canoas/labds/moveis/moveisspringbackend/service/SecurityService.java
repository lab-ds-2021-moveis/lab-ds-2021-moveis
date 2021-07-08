package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

public interface SecurityService {
    String findLoggedInIdentifier();
    void autoLogin(String username, String password);
}


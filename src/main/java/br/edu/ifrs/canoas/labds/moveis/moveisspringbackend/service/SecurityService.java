package br.edu.ifrs.canoas.labds.moveis.moveisspringbackend.service;

public interface SecurityService {
    String getLoggedInUserName();
    Object getCurrentUser();
    Class getCurrentUserClass();
    void autoLogin(String username, String password);
}


package pl.codecity.perun.account.service;

import pl.codecity.perun.account.model.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole findOneById(Long id);
    void saveRole(UserRole userRole);
    void deleteById(Long id);
    void delete(UserRole userRole);
    List<UserRole> findAll();
}

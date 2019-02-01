package pl.codecity.perun.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.account.model.UserRole;
import pl.codecity.perun.account.repository.UserRoleRepository;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;
    
    public UserRole findOneById(Long id) {
        return repository.getOne(id);
    }

    public void saveRole(UserRole userRole) {
        repository.save(userRole);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(UserRole userRole) {
        repository.delete(userRole);
    }

    public List<UserRole> findAll() {
        return repository.findAll();
    }
}

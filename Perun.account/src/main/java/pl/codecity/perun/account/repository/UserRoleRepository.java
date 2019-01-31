package pl.codecity.perun.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.perun.account.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query(value = "SELECT p FROM user_profile p WHERE p.id = :id", nativeQuery = true)
    UserRole findOneById(Integer id);

    void saveRole(UserRole userRole);

    void deleteById(Integer id);

    void delete(UserRole userRole);

    List<UserRole> findAll();
}

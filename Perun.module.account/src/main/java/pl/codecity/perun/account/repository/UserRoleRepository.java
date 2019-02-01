package pl.codecity.perun.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.perun.account.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "SELECT up FROM user_profile up WHERE up.id = :id", nativeQuery = true)
    UserRole findOneById(Long id);
    void saveRole(UserRole userRole);
    void deleteById(Long id);
    void delete(UserRole userRole);
    List<UserRole> findAll();
}

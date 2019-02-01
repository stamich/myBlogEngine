package pl.codecity.perun.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.perun.account.model.UserStatus;

import java.util.List;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    @Query(value = "SELECT us FROM user_status us WHERE us.id = :id", nativeQuery = true)
    UserStatus findOneById(Long id);
    void saveStatus(UserStatus userStatus);
    void deleteById(Long id);
    void delete(UserStatus userStatus);
    List<UserStatus> findAll();
}

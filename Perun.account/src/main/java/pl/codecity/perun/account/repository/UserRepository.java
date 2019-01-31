package pl.codecity.perun.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.codecity.perun.account.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM user u WHERE u.id = :id", nativeQuery = true)
    User findOneById(Integer id);

    @Query(value = "SELECT u FROM user u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "SELECT u FROM user u WHERE u.nickname = :nickname", nativeQuery = true)
    User findByNickName(String nickName);

    void deleteById(Integer id);

    void deleteByNickName(String nickName);

    List<User> findAll();
}

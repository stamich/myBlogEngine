package pl.codecity.perun.account.service;

import pl.codecity.perun.account.model.User;

import java.util.List;

public interface UserService {

    User findOneById(Long id);
    User findByEmail(String email);
    User findByNickName(String nickName);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    void deleteByNickName(String nickName);
    List<User> findAll();
    boolean isUserNickNameUnique(Long id, String nickName);
}

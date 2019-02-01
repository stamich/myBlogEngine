package pl.codecity.perun.account.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.account.model.User;
import pl.codecity.perun.account.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findOneById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) {
        User entity = repository.findOneById(user.getId());
        if(entity != null){
            entity.setNickName(user.getNickName());
            entity.setEmail(user.getEmail());
            entity.setPassword(user.getPassword());
            entity.setAvatar(user.getAvatar());
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByNickName(String nickName) {
        repository.deleteByNickName(nickName);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean isUserNickNameUnique(Long id, String nickName) {
        User user = findByNickName(nickName);
        return (user == null || ((id != null) && (user.getId() == id)));
    }
}

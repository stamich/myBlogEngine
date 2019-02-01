package pl.codecity.perun.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.account.model.UserStatus;
import pl.codecity.perun.account.repository.UserStatusRepository;

import java.util.List;

@Service
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    private UserStatusRepository repository;

    public UserStatus findOneById(Long id) {
        return repository.findOneById(id);
    }

    public void saveStatus(UserStatus userStatus) {
        repository.save(userStatus);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(UserStatus userStatus) {
        repository.delete(userStatus);
    }

    public List<UserStatus> findAll() {
        return repository.findAll();
    }
}

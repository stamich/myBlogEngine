package pl.codecity.perun.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.file.model.File;
import pl.codecity.perun.file.repository.FileWarehouseRepository;

import java.util.List;

@Service
@Transactional
public class FileWarehouseServiceImpl implements FileWarehouseService {

    @Autowired
    private FileWarehouseRepository repository;

    @Override
    public File findOneById(String id) {
        return repository.findOneById(id);
    }

    @Override
    public List<File> findAll() {
        return repository.findAll();
    }
}

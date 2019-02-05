package pl.codecity.perun.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.codecity.perun.file.helper.Utils;
import pl.codecity.perun.file.model.BinaryFile;
import pl.codecity.perun.file.model.File;
import pl.codecity.perun.file.repository.FileWarehouseRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public void saveFile(Iterable<File> file){
        repository.saveAll(file);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void processUploadFile(byte[] bytes) throws ClassNotFoundException, IOException {
        BinaryFile binaryFile = Utils.readSerializable(bytes);
        InputStream inputStream = new ByteArrayInputStream(binaryFile.getData());
        try {
            saveFile(binaryFile.getContentType());
        } finally {
            Utils.close(inputStream);
        }
    }
}

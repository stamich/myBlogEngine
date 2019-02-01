package pl.codecity.perun.file.service;

import pl.codecity.perun.file.model.File;

import java.util.List;

public interface FileWarehouseService {

    File findOneById(String id);
    List<File> findAll();
}

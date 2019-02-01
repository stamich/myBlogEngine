package pl.codecity.perun.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.codecity.perun.file.model.File;

public interface FileWarehouseRepository extends JpaRepository<File, String> {

    @Query(value = "SELECT f FROM file f WHERE f.id = :id", nativeQuery = true)
    File findOneById(String id);

}

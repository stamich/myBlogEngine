package pl.codecity.perun.file.model;

import org.hibernate.annotations.GenericGenerator;
import pl.codecity.perun.common.model.AbstractDomainObject;

import javax.persistence.*;

@Entity
@Table(name = "File")
public class File extends AbstractDomainObject<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID", length = 50)
    String id;

    @Column(name = "ORIGINAL_NAME", length = 500)
    String fileName;

    //

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //

    @Override
    public String print() {
        return "Name: " + getFileName();
    }
}

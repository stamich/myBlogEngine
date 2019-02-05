package pl.codecity.perun.file.model;

import org.hibernate.annotations.GenericGenerator;
import pl.codecity.perun.common.model.AbstractDomainObject;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "File")
public class File extends AbstractDomainObject<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID", length = 50)
    private String id;

    @Column(name = "ORIGINAL_NAME", length = 500)
    private String fileName;

    @Lob
    @Column
    private Blob content;

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

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    @Override
    public String print() {
        return "Name: " + getFileName();
    }
}

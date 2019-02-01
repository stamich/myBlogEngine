package pl.codecity.perun.common.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MEDIA")
@SuppressWarnings("serial")
public class Media extends AbstractDomainObject<String> {

    public enum ResizeMode {
        RESIZE,
        CROP
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "ID", length = 50)
    private String id;

    @Column(name = "MIME_TYPE", length = 500, nullable = false)
    private String mimeType;

    @Column(name = "ORIGINAL_NAME", length = 500)
    private String originalName;

    @ManyToMany(mappedBy = "medias")
    private List<Post> posts;

    // Getters and setters

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // Override methods

    @Override
    public String print() {
        return getId() + " " + getOriginalName();
    }
}

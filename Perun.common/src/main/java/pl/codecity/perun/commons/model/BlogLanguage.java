package pl.codecity.perun.commons.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "BLOG_LANGUAGE", uniqueConstraints = @UniqueConstraint(columnNames = {"blog_id", "language"}))
public class BlogLanguage extends AbstractDomainObject<Integer> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LANGUAGE",length = 3, nullable = false)
    private String language;

    @Lob
    @Column(name = "TITLE",length = 300, nullable = false)
    private String title;

    @ManyToOne(optional = false)
    private Blog blog;

    // Abstract methods

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String print() {
        return getBlog().getCode() + "-" + getLanguage();
    }

    // Getters and setters

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Object class methods

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof BlogLanguage)) return false;
        BlogLanguage that = (BlogLanguage) other;
        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getLanguage(), that.getLanguage())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .append(getLanguage())
                .toHashCode();
    }

    @Override
    public String toString() {
        return getBlog().getCode() + "-" + getLanguage();
    }
}

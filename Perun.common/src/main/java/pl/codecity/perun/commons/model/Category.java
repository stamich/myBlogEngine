package pl.codecity.perun.commons.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.*;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "CATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "language"}))
@SuppressWarnings("serial")
public class Category extends AbstractDomainObject<Integer> implements Comparable<Category>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SortableField(forField = "sortId")
    private Integer id;

    @Column(name = "CODE",length = 200, nullable = false)
    private String code;

    @Column(name = "LANGUAGE",length = 3, nullable = false)
    private String language;

    @Column(length = 200, nullable = false)
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LEFT", nullable = false)
    private int left;

    @Column(name = "RIGHT", nullable = false)
    private int right;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children;

    @ManyToMany
    @JoinTable(
            name = "post_category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private SortedSet<Post> posts = new TreeSet<>();

    // Abstract methods

    @Override
    public Integer getId() {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String print() {
        return getName();
    }

    @Override
    public int compareTo(Category category) {
        int lftDiff = getLeft() - category.getLeft();
        if (lftDiff != 0) {
            return lftDiff;
        }
        return (int) (category.getId() - getId());
    }

    // Getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public SortedSet<Post> getPosts() {
        return posts;
    }

    public void setPosts(SortedSet<Post> posts) {
        this.posts = posts;
    }

    // Object class methods

    @Override
    public String toString() {
        return getName();
    }
}

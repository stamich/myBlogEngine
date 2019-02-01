package pl.codecity.perun.common.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "BLOG")
@SuppressWarnings("serial")
public class Blog extends AbstractDomainObject<Integer>{

    public static final Integer DEFAULT_ID = 1;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE", length = 200, nullable = false, unique = true)
    private String code;

    @Column(name = "DEFAULT_LANGUAGE", length = 3, nullable = false)
    private String defaultLanguage;

//    @Embedded
//    @IndexedEmbedded(includeEmbeddedObjectId = true)
//    private GoogleAnalytics googleAnalytics;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    @IndexedEmbedded(includeEmbeddedObjectId = true)
    private Set<BlogLanguage> languages = new HashSet<>();

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
        return getTitle();
    }

    // Getters and setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

//    public GoogleAnalytics getGoogleAnalytics() {
//        return googleAnalytics;
//    }
//
//    public void setGoogleAnalytics(GoogleAnalytics googleAnalytics) {
//        this.googleAnalytics = googleAnalytics;
//    }

    public Set<BlogLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<BlogLanguage> languages) {
        this.languages = languages;
    }

    public BlogLanguage getLanguage(String language) {
        for (BlogLanguage blogLanguage : getLanguages()) {
            if (blogLanguage.getLanguage().equals(language)) {
                return blogLanguage;
            }
        }
        return null;
    }

    public String getTitle() {
        return getTitle(getDefaultLanguage());
    }

    public String getTitle(String language) {
        return getLanguage(language).getTitle();
    }

    public boolean isMultiLanguage() {
        return (getLanguages().size() > 1);
    }
}

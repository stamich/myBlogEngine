package pl.codecity.perun.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.io.Serializable;

@Embeddable
@SuppressWarnings("serial")
public class Seo implements Serializable {

    @Column(name = "SEO_TITLE", length = 500)
    private String title;

    @Lob
    @Column(name = "SEO_DESCRIPTION")
    private String description;

    @Lob
    @Column(name = "SEO_KEYWORDS")
    private String keywords;

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}

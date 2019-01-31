package pl.codecity.perun.commons.model;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Indexed
@DynamicInsert
@DynamicUpdate
@Table(name = "COMMENT")
@SuppressWarnings("serial")
public class Comment extends AbstractDomainObject<Integer> implements Comparable<Comment>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID", nullable = false, updatable = false, unique = true)
    private Integer id;

    @NotEmpty(message = "*Please write something")
    @Column(name = "COMMENT_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "APPROVED", nullable = false)
    private Boolean approved;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", referencedColumnName = "post_id", nullable = false)
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "user_id", nullable = false)
    private User user;

    //Constructors

    public Comment(){
        super();
    }

    public Comment(String commentContent, Post post, User user) {
        this.commentContent = commentContent;
        this.post = post;
        this.user = user;
    }

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
        return this.getClass().getName() + " " + getId();
    }

    // Getters and setters

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Other methods

    public int compareTo(Comment comment) {
        return new CompareToBuilder()
                .append(getDate(), comment.getDate())
                .append(getId(), comment.getId())
                .toComparison();
    }
}

package pl.codecity.perun.account.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.DigestUtils;
import pl.codecity.perun.common.model.AbstractDomainObject;
import pl.codecity.perun.common.model.Article;
import pl.codecity.perun.common.model.Post;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`USER`")
@SuppressWarnings("serial")
public class User extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "NICKNAME", nullable = false, unique = true)
    @Length(min = 3, message = "Username length should have at least 5 characters")
    private String nickName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @NotEmpty(message = "*Please provide an email")
    @Email(message = "*Please provide a valid Email")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty(message = "*Please provide your password")
    @Length(min = 5, message = "Username length should have at least 5 characters")
    private String password;

    @Lob
    @Column(name = "AVATAR")
    private Blob avatar;

    @Column(name = "CONFIRMED", nullable = false)
    private boolean confirmed;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserRole> userRoles = new HashSet<>();

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_STATUS_ID") })
    private Set<UserStatus> userStatuses = new HashSet<>();

//    @NotEmpty
//    @ManyToMany
//    @JoinTable(name = "USER_ARTICLE")
//    private Set<Article> articles = new HashSet<>();
//
//    @NotEmpty
//    @ManyToMany
//    @JoinTable(name = "USER_POST")
//    private Set<Post> posts = new HashSet<>();

    // Abstract methods

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String print() {
        String name = "user: " + getFirstName() + " " + getLastName() + " nick: " + getNickName();
        return (name != null) ? name : "";
    }

    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<UserStatus> getUserStatuses() {
        return userStatuses;
    }

    public void setUserStatuses(Set<UserStatus> userStatuses) {
        this.userStatuses = userStatuses;
    }

//    public Set<Article> getArticles() {
//        return articles;
//    }
//
//    public void setArticles(Set<Article> articles) {
//        this.articles = articles;
//    }
//
//    public Set<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(Set<Post> posts) {
//        this.posts = posts;
//    }

    // Other methods

    public String getGravatarUrl(int size) throws UnsupportedEncodingException {
        String hash = DigestUtils.md5DigestAsHex(getEmail().getBytes("CP1252"));
        return String.format("https://secure.gravatar.com/avatar/%s?size=%d&d=mm", hash, size);
    }
}

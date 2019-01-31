package pl.codecity.perun.account.model;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.DigestUtils;
import pl.codecity.perun.commons.model.AbstractDomainObject;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@SuppressWarnings("serial")
public class User extends AbstractDomainObject<Integer> {

    public enum Role {
        ADMIN,
        EDITOR,
        AUTHOR,
        VIEWER,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @IndexedEmbedded(includeEmbeddedObjectId = true)
    private PersonalName name = new PersonalName();

    @Column(name = "nick_name", nullable = false, unique = true)
    @Length(min = 3, message = "Username length should have at least 5 characters")
    private String nickName;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "*Please provide an email")
    @Email(message = "*Please provide a valid Email")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "*Please provide your password")
    @Length(min = 5, message = "Username length should have at least 5 characters")
    private String password;

    @Lob
    @Column(name = "avatar")
    private Blob avatar;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "actived", nullable = false)
    private boolean active;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserRole> userRoles = new HashSet<>();

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
        return (getName() != null) ? getName().toString() : "";
    }

    // Getters and setters

    public PersonalName getName() {
        return name;
    }

    public void setName(PersonalName name) {
        this.name = name;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    // Other methods

    public String getGravatarUrl(int size) throws UnsupportedEncodingException {
        String hash = DigestUtils.md5DigestAsHex(getEmail().getBytes("CP1252"));
        return String.format("https://secure.gravatar.com/avatar/%s?size=%d&d=mm", hash, size);
    }

    // Object class methods

    @Override
    public String toString() {
        return (getName() != null) ? getName().toString() : "";
    }
}

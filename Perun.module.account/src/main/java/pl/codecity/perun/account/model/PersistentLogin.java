package pl.codecity.perun.account.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import pl.codecity.perun.common.model.AbstractDomainObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
@SuppressWarnings("serial")
public class PersistentLogin extends AbstractDomainObject<String> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 50)
    private String id;

    @Column(name = "nick_name", unique = true, nullable = false)
    private String nickName;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @CreationTimestamp
    @Column(name = "last_used")
    private Date lastUsed;

    // Abstract methods

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String print() {
        return getNickName() + " " + getLastUsed();
    }

    // Getters and setters

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}

package pl.codecity.perun.account.model;

import pl.codecity.perun.common.model.AbstractDomainObject;

import javax.persistence.*;

@Entity
@Table(name = "USER_STATUS")
@SuppressWarnings("serial")
public class UserStatus extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "STATUS", length = 10, unique = true, nullable = false)
    private String status = UserStatusType.ACTIVE.getUserStatus();

    //Getters and Setters

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Override methods

    @Override
    public String print() {
        return null;
    }
}

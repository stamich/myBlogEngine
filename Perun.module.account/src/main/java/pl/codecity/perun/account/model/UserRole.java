package pl.codecity.perun.account.model;

import pl.codecity.perun.common.model.AbstractDomainObject;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE")
@SuppressWarnings("serial")
public class UserRole extends AbstractDomainObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "ROLE", length = 10, unique = true, nullable = false)
    private String role = UserRoleType.ADMIN.getUserRole();

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //Override methods

    @Override
    public String print() {
        return getRole();
    }
}

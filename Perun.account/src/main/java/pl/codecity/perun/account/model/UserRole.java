package pl.codecity.perun.account.model;

import pl.codecity.perun.commons.model.AbstractDomainObject;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole extends AbstractDomainObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(name = "role", length = 15, unique = true, nullable = false)
    private String role = UserRoleType.ADMIN.getUserRole();

    //Constructors

    public UserRole(){
        super();
    }

    public UserRole(String role) {
        this.role = role;
    }

    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String print() {
        return getRole();
    }

    //Override methods

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserRole))
            return false;
        UserRole other = (UserRole) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ", type=" + role + "]";
    }
}

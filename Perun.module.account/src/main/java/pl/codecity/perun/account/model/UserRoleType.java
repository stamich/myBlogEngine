package pl.codecity.perun.account.model;

import java.io.Serializable;

/**
 * This enumerator contains all available user roles
 * @author Michal Stawarski for <b>Codecity.pl</b>
 * @version 1.0
 */
public enum UserRoleType implements Serializable {

    READER("Reader"),
    COMMENTER("Commenter"),
    WRITER("writer"),
    DBA("dba"),
    ADMIN("admin");

    String userRole;

    private UserRoleType(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole(){
        return userRole;
    }
}

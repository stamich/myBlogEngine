package pl.codecity.perun.account.model;

import java.io.Serializable;

public enum UserRoleType implements Serializable {

    VIEWER("viewer"),
    AUTHOR("author"),
    EDITHOR("writer"),
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

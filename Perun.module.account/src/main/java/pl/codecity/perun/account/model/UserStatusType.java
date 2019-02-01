package pl.codecity.perun.account.model;

import java.io.Serializable;

/**
 * This enumerator contains all available user statuses
 * @author Michal Stawarski for <b>Codecity.pl</b>
 * @version 1.0
 */
public enum UserStatusType implements Serializable {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    BLOCKED("Blocked"),
    REMOVED("Removed");

    String userStatus;

    private UserStatusType(String userStatus){
        this.userStatus = userStatus;
    }

    public String getUserStatus(){
        return  userStatus;
    }
}

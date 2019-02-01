package pl.codecity.perun.account.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private static final Long serialVersionUID = 5861310537366287163L;

    public UserAlreadyExistsException(){
        super();
    }

    public UserAlreadyExistsException(final Throwable cause){
        super(cause);
    }

    public UserAlreadyExistsException(final String message){
        super(message);
    }

    public UserAlreadyExistsException(final String message, final Throwable cause){
        super(message, cause);
    }
}

package pl.codecity.perun.account.exception;

public class UserNotFoundException extends RuntimeException{

    private static final Long serialVersionUID = 5861310537366287163L;

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(final String message){
        super(message);
    }

    public UserNotFoundException(final Throwable cause){
        super(cause);
    }

    public UserNotFoundException(final String message, final Throwable cause){
        super(message, cause);
    }
}

package pl.codecity.perun.account.exception;

public class InvalidPasswordException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(final String message) {
        super(message);
    }

    public InvalidPasswordException(final Throwable cause) {
        super(cause);
    }

    public InvalidPasswordException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

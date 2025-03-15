package app.com.sportflow.exception;

public class UserEmailNoExistException extends RuntimeException {
    public UserEmailNoExistException(String message) {
        super(message);
    }
}

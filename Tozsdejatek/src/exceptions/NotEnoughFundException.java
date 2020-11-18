package exceptions;

public class NotEnoughFundException extends Exception {

    public NotEnoughFundException() {
    }

    public NotEnoughFundException(String message) {
        super(message);
    }
}

package jarek.rest.exception;

public class WrongOperation extends RuntimeException{
    public WrongOperation(String message) {
        super(message);
    }
}

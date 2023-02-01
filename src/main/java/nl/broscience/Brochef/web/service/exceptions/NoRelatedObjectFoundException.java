package nl.broscience.Brochef.web.service.exceptions;

public class NoRelatedObjectFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoRelatedObjectFoundException() {

        super();

    }

    public NoRelatedObjectFoundException(String message) {

        super(message);

    }

}

package nl.broscience.Brochef.web.service.exceptions;

public class DeleteRecordException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DeleteRecordException() {

        super();

    }

    public DeleteRecordException(String message) {

        super(message);

    }
}

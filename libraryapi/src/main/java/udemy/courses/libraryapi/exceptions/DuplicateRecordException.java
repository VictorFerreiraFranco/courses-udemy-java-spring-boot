package udemy.courses.libraryapi.exceptions;

public class DuplicateRecordException extends RuntimeException {

    public DuplicateRecordException(String message) {
        super(message);
    }

}

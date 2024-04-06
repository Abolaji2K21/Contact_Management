package africa.semicolon.contactException;

public class CategoryNotFoundException extends BigContactException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

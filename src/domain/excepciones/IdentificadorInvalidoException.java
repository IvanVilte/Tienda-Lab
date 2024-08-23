package domain.excepciones;

public class IdentificadorInvalidoException extends RuntimeException {
    public IdentificadorInvalidoException(String message) {
        super(message);
    }
}

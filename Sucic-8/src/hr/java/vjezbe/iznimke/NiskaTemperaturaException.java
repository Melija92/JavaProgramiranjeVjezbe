package hr.java.vjezbe.iznimke;

/**
 * predstavlja exception za nisku temperaturu
 */
public class NiskaTemperaturaException extends RuntimeException {
    /**
     * prazan konstruktor
     */
    public NiskaTemperaturaException() {
    }

    /**
     * prima poruku
     */
    public NiskaTemperaturaException(String message) {
        super(message);
    }

    /**
     * prima poruku i uzrok
     */
    public NiskaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public NiskaTemperaturaException(Throwable cause) {
        super(cause);
    }
}

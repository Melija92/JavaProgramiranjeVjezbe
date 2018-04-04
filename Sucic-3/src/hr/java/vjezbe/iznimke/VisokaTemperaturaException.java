package hr.java.vjezbe.iznimke;


/**
 * predstavlja exception za visoku temperaturu
 */
public class VisokaTemperaturaException extends Exception {
    /**
     * prazan konstruktor
     */
    public VisokaTemperaturaException() {
    }

    /**
     * prima poruku
     */
    public VisokaTemperaturaException(String message) {
        super(message);
    }

    /**
     * prima poruku i i uzrok
     */
    public VisokaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * prima uzrok
     */
    public VisokaTemperaturaException(Throwable cause) {
        super(cause);
    }
}

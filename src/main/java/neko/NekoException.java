package neko;

public class NekoException extends Exception {
    /**
     * Creates a new NekoException with the specified error message.
     *
     * @param message Description of the error.
     */
    public NekoException(String message) {
        super(message);
    }
}

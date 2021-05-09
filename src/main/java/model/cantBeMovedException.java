package model;

/**
 * Signals that an {@code Entity} can 't be moved.
 */
public class cantBeMovedException extends Exception {
    /**
     * Constructs an {@code cantBeMovedException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */

    public cantBeMovedException(String message) {
        super(message);
    }

}

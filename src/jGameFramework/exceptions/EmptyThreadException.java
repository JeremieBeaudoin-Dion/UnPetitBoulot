package jGameFramework.exceptions;

/**
 * @author Mia Beaudoin-Dion
 */
public class EmptyThreadException extends RuntimeException {

    public EmptyThreadException(){
        super("The threads are empty, stopping game...");
    }

}

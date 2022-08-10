package uz.jl.exceptions;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:22 AM 8/4/22 on Thursday in August
 */
public class ObjectAlreadyExistsException extends RuntimeException{

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}

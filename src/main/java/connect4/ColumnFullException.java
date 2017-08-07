package connect4;

public class ColumnFullException extends RuntimeException{    
    public ColumnFullException(String error){
        super(error);
    }
}
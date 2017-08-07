package connect4;

/**
 * Main class of the Java program. 
 * 
 */
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ConnectFour cf = new ConnectFour();
        int moves = 0;
        while(cf.getResult() == ConnectFour.Result.NONE && moves < 42) {
            int col = random.nextInt(7);
            try {
                cf.dropToken(col);
                System.out.println(cf);
            }
            catch(ColumnFullException e) {}
            moves++;
        }
    }
}


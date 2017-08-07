package connect4;

/**
 * Tests for class ConnectFour.
 * 
 * All tests in the folder "test" are executed 
 * when the "Test" action is invoked.
 * 
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ConnectFourTest {
    private ConnectFour cf;

    @Before
    public final void setUp() {
        cf = new ConnectFour();
        boolean redTurn = true;
    }

	@Test
    public final void testNewGameBoardShouldBeEmpty() {
        for(int col = 0; col < 7; col++) {
            assertEquals(ConnectFour.Location.EMPTY, cf.getTopOfColumn(col));
            assertEquals(0, cf.getHeightOfColumn(col));
        }
    }
    
    @Test
    public final void testGetHeightOfColumn() {
        assertEquals(0, cf.getHeightOfColumn(3));
        cf.dropToken(3);
        assertEquals(1, cf.getHeightOfColumn(3));
        cf.dropToken(3);
        assertEquals(2, cf.getHeightOfColumn(3));
        cf.dropToken(3);
        assertEquals(3, cf.getHeightOfColumn(3));
        cf.dropToken(3);
        assertEquals(4, cf.getHeightOfColumn(3));
    }
    
    @Test
    public final void testGetTopOfColumn() {
        assertEquals(ConnectFour.Location.EMPTY, cf.getTopOfColumn(4));
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(4));
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(4));
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(4));
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(4));
    }
    
    @Test
    public final void testDropRedTokenInThirdColumnThenBlackTokenOnTopOfIt() {
        assertEquals(0, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        assertEquals(1, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(2));
        assertEquals(2, cf.getHeightOfColumn(2));
    }
    
    @Test
    public final void testDroppingIntoAnInvalidColumnIsIgnored() {
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(4));
        assertEquals(1, cf.getHeightOfColumn(4));
        cf.dropToken(9);  // This column is invalid and should be ignored.
        cf.dropToken(4);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(4));
        assertEquals(2, cf.getHeightOfColumn(4));
    }

    @Test
    public final void testDroppingRepeatedlyIntoInvalidColumnsIsIgnored() {
        cf.dropToken(5);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(5));
        assertEquals(1, cf.getHeightOfColumn(5));
        for(int i = 0; i < 9; i++) {
            cf.dropToken(i - 10);
        }
        cf.dropToken(5);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(5));
        assertEquals(2, cf.getHeightOfColumn(5));
    }

    
    @Test(expected=ColumnFullException.class)
    public void testDroppingOnAFullColumnShouldThrowException() {
        assertEquals(0, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        assertEquals(1, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(2));
        assertEquals(2, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        assertEquals(3, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(2));
        assertEquals(4, cf.getHeightOfColumn(2));    
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        assertEquals(5, cf.getHeightOfColumn(2));
        cf.dropToken(2);  
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(2));
        assertEquals(6, cf.getHeightOfColumn(2)); 
        cf.dropToken(2);
    }

    @Test
    public final void testDroppingThreeTokensLeadsToNoWinner() {
        assertEquals(ConnectFour.Result.NONE, cf.getResult());
        cf.dropToken(2);
        assertEquals(ConnectFour.Result.NONE, cf.getResult());
        cf.dropToken(5);
        assertEquals(ConnectFour.Result.NONE, cf.getResult());
        cf.dropToken(0);
        assertEquals(ConnectFour.Result.NONE, cf.getResult());
    }
    
    @Test
    public final void 
testAlternateDroppingBetweenColumnsThreeAndFourLeadsToRedWinning() {
        assertEquals(0, cf.getHeightOfColumn(2));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        cf.dropToken(3);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(3));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        cf.dropToken(3);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(3));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
        cf.dropToken(3);
        assertEquals(ConnectFour.Location.BLACK, cf.getTopOfColumn(3));
        cf.dropToken(2);
        assertEquals(ConnectFour.Location.RED, cf.getTopOfColumn(2));
    }

    @Test
    public final void testPrintingTheBoardAsAString() {
        String expected = "| | | | | | | |\n" +
                          "| | | | | | | |\n" +
                          "| | | | | | | |\n" +
                          "| |R| | |R| | |\n" +
                          "|B|B| | |B|R| |\n" +
                          "|R|B| |B|R|R| |\n" +
                          "---------------";
        int[] drops = {0, 1, 5, 1, 4, 3, 1, 4, 5, 0, 4};
        for(int i = 0; i < drops.length; i++) {
            cf.dropToken(drops[i]);
        }
        assertEquals(expected, cf.toString());
    }
}


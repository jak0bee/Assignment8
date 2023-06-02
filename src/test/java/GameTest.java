// by Jakub Suszwedyk (6310933) and Marcell Dorko (6326607)

import code.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

public class GameTest {
    Game game;
    @Before
    public void setUp() {
        game = new Game();
        game.addPlayer("Marek");
    }


    @Test
    public void normalRollTest() {
        int startingPostition = game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace();
        game.roll(3);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),startingPostition+3);
        startingPostition +=3;
        game.roll(2);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),startingPostition+ 2);

    }

    @Test
    public void negativeRollTest(){
        assertThrows(RuntimeException.class, () -> {
            game.roll(-1);
        });
    }
    @Test
    public void zeroRollTest(){
        int startingPostition = game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace();
        game.roll(0);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),startingPostition);
    }
    @Test
    public void overflowRollTest(){
        game.roll(999);
        assert(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace()<12);

    }
}

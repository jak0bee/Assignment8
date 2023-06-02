// by Jakub Suszwedyk (6310933) and Marcell Dorko (6326607)

import code.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameTest {
    Game game;
    @Before
    public void setUp() {
        game = new Game();
        game.addPlayer("Marek");
    }


    @Test
    public void normalRollTest() {
        game.roll(3);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),3);
        game.roll(2);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),5);
        game.roll(0);
        assertEquals(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace(),5);


    }

    @Test
    public void negativeRollTest(){
        game.roll(-1);
    }

    public void overflowRollTest(){
        game.roll(999);
        assert(game.getPLAYERS().get(game.getCurrentPlayerIndex()).getPlace()<12);

    }
}

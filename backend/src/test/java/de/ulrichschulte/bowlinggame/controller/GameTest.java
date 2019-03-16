package de.ulrichschulte.bowlinggame.controller;

import de.ulrichschulte.bowlinggame.Logic.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setUp () {
        game = new Game();
    }

    public void playMany (int pins, int count) {
        System.out.println(String.format("%d würfe mit %d Pins", count, pins));
        for (int i = 0; i < count; i++) {
            game.bowl(pins);
        }
    }

    @Test
    public void testGameInstance() {
        assertTrue( game != null);
    }

    @Test
    public void whenNoPins_returnZeroPoints() {
        playMany( 0, 21) ;
        assertEquals(0, game.getTotalScore());
    }

    @Test
    public void when1PinEach_return21Points () {
        playMany( 1, 20) ;
        assertEquals(20,game.getTotalScore());
    }

    @Test
    public void whenLastFrameStrike_Allow3Bowls () {
        playMany(1,18);
        assertEquals(18, game.getTotalScore());
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        assertEquals(48, game.getTotalScore());
    }

    @Test
    public void when1Strike_return10() {
        playMany( 10, 1);
        assertEquals( 10, game.getTotalScore());
    }

    @Test
    public void when2Strike_return10() {
        playMany( 10, 2);
        assertEquals( 30, game.getTotalScore());
    }

    @Test
    public void when2Bowls_return2 () {
        playMany(3,2);
        assertEquals(6,game.getTotalScore());
    }

    @Test
    public void whenPerfectGame_return300_and12Rolls () {
        playMany(10,12);
        assertEquals( 300, game.getTotalScore());
    }

    @Test
    public void testSample() {
        game.bowl(10);
        assertEquals(10, game.getTotalScore());
        game.bowl(7);
        game.bowl(3);
        assertEquals(20, game.getFrameScore(0));
        assertEquals(30, game.getTotalScore());
        game.bowl(9);
        game.bowl(0);
        assertEquals(48, game.getTotalScore());
        assertEquals(39, game.getFrameScore(1));
        assertEquals(48, game.getFrameScore(2));
        game.bowl(10);
        assertEquals(58, game.getTotalScore());
        game.bowl(0);
        game.bowl(8);
        assertEquals(66, game.getFrameScore(3));
        assertEquals(74, game.getFrameScore(4));
        assertEquals(74, game.getTotalScore());
        game.bowl(8);
        game.bowl(2);
        assertEquals(84, game.getTotalScore());
        assertEquals(84, game.getFrameScore(5));
        game.bowl(0);
        game.bowl(6);
        assertEquals(90, game.getTotalScore());
        assertEquals(90, game.getFrameScore(6));
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(8);
        game.bowl(1);
        assertEquals(167,game.getTotalScore());
        assertEquals(120, game.getFrameScore(7));
        assertEquals(148, game.getFrameScore(8));
        assertEquals(167, game.getFrameScore(9));


    }


}
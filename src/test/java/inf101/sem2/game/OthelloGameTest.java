package inf101.sem2.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import inf101.grid.Location;
import inf101.sem2.terminal.TerminalGraphics;
import org.junit.jupiter.api.BeforeEach;

import inf101.GetStarted;
import inf101.sem2.player.AbstractPlayer;
import inf101.sem2.player.DumbPlayer;
import inf101.sem2.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OthelloGameTest {

    ArrayList<Player> players;

    @BeforeEach
    void addPlayers() {
        players = new ArrayList<>();
        players.add(new AbstractPlayer('X') {
            @Override
            public Location getMove(Game board) {
                return null;
            }
        });
        players.add(new AbstractPlayer('O') {
            @Override
            public Location getMove(Game board) {
                return null;
            }
        });
    }

    @Test
    void testOPlayerFirst() {

        Othello game = new Othello(new TerminalGraphics(), players.get(0), players.get(1));
        assertTrue(game.getCurrentPlayer().getSymbol() == 'O');
    }

    @Test
    void testDefaultPositionOfBoard() {

        Othello game = new Othello(new TerminalGraphics(), players.get(0), players.get(1));
        assertTrue(game.board.get(new Location(3,3)).getSymbol() == 'X');
        assertTrue(game.board.get(new Location(4,3)).getSymbol() == 'O');
        assertTrue(game.board.get(new Location(3,4)).getSymbol() == 'O');
        assertTrue(game.board.get(new Location(4,4)).getSymbol() == 'X');
    }
    
    @Test
    void testOValidMoveDefault() {

        Othello game = new Othello(new TerminalGraphics(), players.get(0), players.get(1));       
        game.makeMove(new Location(3,2));
        assertTrue(game.board.get(new Location(3,2)).getSymbol() == 'O');
    }
    
    @Test
    void testOCanFlipTileToLoc_3_2() {

        Othello game = new Othello(new TerminalGraphics(), players.get(0), players.get(1));       
        game.makeMove(new Location(3,2));
        assertTrue(game.board.get(new Location(3,3)).getSymbol() == 'O');
    }

}

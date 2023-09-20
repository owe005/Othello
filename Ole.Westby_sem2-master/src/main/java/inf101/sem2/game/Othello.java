package inf101.sem2.game;

import java.util.ArrayList;
import java.util.List;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.ConsolePlayer;
import inf101.sem2.player.MiniMaxPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;

public class Othello extends Game {
	
	
	/**
	 * Othello, a turn based game where the players take turn "moving" pieces on the gameboard in attempts of having the majority of the pieces at the end of the game.
	 * Author: Ole Westby.
	 * 
	 * 
	 * @param graphics - The graphics used to display the game for the human players
	 * @param p1 - First player
	 * @param p2 - Second player
	 */
	private final int[][] adjstFields = {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}};
	public Othello(Graphics graphics, Player p1, Player p2) {
		this(graphics);
		players.add(p1);
		players.add(p2);
		Setup();
	}

	public Othello(Graphics graphics, Iterable<Player> players) {
		
		super(new GameBoard(8, 8), graphics, players);
		Setup();
	}

	public Othello(Graphics graphics) { super(new GameBoard(8,8), graphics); }
	
	public void Setup() {
		board.set(new Location(3, 3), players.getCurrentPlayer());
		board.set(new Location(4, 4), players.getCurrentPlayer());
		players.nextPlayer();
		board.set(new Location(3, 4), players.getCurrentPlayer());
		board.set(new Location(4, 3), players.getCurrentPlayer());
		players.nextPlayer();

		players.setCurrentPlayer(players.nextPlayer());
	}

	
	//Denne må fikses litt på.
	@Override
	public boolean isWinner(Player p) {
		return board.isFull();
		
		

	}
	@Override
	public boolean gameOver() {
		List<Location> moves = getPossibleMoves();
		for(Player p : players) {
			if(isWinner(p)) {
				return true;
			}
			if(moves.isEmpty()) {
				players.nextPlayer();
				if(moves.isEmpty()) {
					return true;
				}
			}
		}

		return board.isFull();
	}

	
	@Override
	public boolean canPlace (Location loc) {
		boolean isMove = false;
		if(!board.isOnGrid(loc))
			return false;
		for(int[]field : adjstFields) {
			Location tmp = new Location(loc.row + field[0], loc.col +  field[1]);
			if(board.isOnGrid(tmp) && board.get(tmp) != null)
				isMove =  true;
		}
		if(!super.canPlace(loc))
			isMove =  false;
		return isMove;
	}
	
	
	@Override
	public void makeMove(Location loc) {
		List<Location> moves = getPossibleMoves();
			if(moves.contains(loc)) {
				board.set(loc, getCurrentPlayer());
				for(int[] direction : adjstFields) {
					int switches = getNrPieces(loc, direction[0], direction[1]);
					if(switches > 0) {
						switchPieces(loc, direction[0], direction[1]);
					}
				}
				players.nextPlayer();
			}
	}
	private void switchPieces(Location loc, int xDir, int yDir){
		Player curplay = players.getCurrentPlayer();
		Player nextplay = players.nextPlayer();
		for(int i = 1 ;; i ++) {
			if(board.get(new Location(loc.row +  i * xDir, loc.col +  i* yDir)) == curplay) {
				break;
			}
			else if(board.get(new Location(loc.row +  i * xDir, loc.col +  i* yDir)) == nextplay) {
				board.put(new Location(loc.row +  i * xDir, loc.col +  i* yDir), curplay);
			}
		}
		players.nextPlayer();
		
	}
	@Override
	public Game copy() {
		Othello game = new Othello(graphics);
		copyTo(game);
		return game;
	}

	@Override
	public String getName() {
		return "Othello";
	}
	
	@Override
	public List<Location> getPossibleMoves() {
		ArrayList<Location> goodMoves = new ArrayList<Location>();
		Location loc;
		for(int x = 0 ; x < board.numColumns(); x++)
		{
			for(int y = 0 ; y < board.numRows() ; y++)
			{
				loc = new Location(y,x);
				if(canPlace(loc) && getNrSwitches(loc) > 0)
					goodMoves.add(loc);
			}
	
		}
		return goodMoves;
	}
	
	private int getNrSwitches(Location loc) {
		int switches = 0;
		for(int[] directioin : adjstFields) {
			switches += getNrPieces(loc,directioin[0],directioin[1]);
		}
		return switches;
	}
	
	private int getNrPieces(Location loc, int xDir, int yDir) {
		int switches = 0;
		int opponentc = 0;
		Player curplay = players.getCurrentPlayer();
		Player nextplay = players.nextPlayer();
		for(int tmp = 1 ;(loc.row + tmp * xDir >= 0) &&(loc.row + tmp * xDir < board.numRows()) && (loc.col + tmp * yDir >= 0) && (loc.col + tmp * yDir < board.numColumns());tmp ++ ) {
			Player tempp = board.get(new Location(loc.row + tmp * xDir,loc.col + tmp * yDir));
			if(tempp == curplay) {
				switches += opponentc;
				opponentc = 0;
				break;
			}
			else if(tempp == nextplay)
			{
				opponentc++;				
			}
			else {
				switches = 0;
				break;
			}
		}
		players.setCurrentPlayer(curplay);
		return switches;
		
	}
}

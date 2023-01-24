import java.util.*;

/**
 * Java Minesweeper Game
 * After related tutorials:
 * zetcode.com
 * betterprogramming.pub
 * tutorialflow.com
 * The coding train on YouTube
 *
 * Author: Tangos974
 */

public class MineSweeper {	

	private static final int BOARD_WIDTH = 1;
	private static final int BOARD_HEIGHT = 1;
	
	private static final int NBR_MINES = 0;
	
	private static final int VERT_MARGIN = 2;
	private static final int HOR_MARGIN = 2;
		

	public static void main(String[] args){

		
		
		MineSweeper M = new MineSweeper();
		M.startGame(NBR_MINES, BOARD_WIDTH, BOARD_HEIGHT); //Call to initializing method
	}

	public void startGame(int mineNumber, int boardWidth, int boardHeight){

		//GameBoard board = new GameBoard(boardWidth, boardHeight, mineNumber);
		GameBoard board = new GameBoard();

		boolean inGame = true;
		while(inGame){
			board.drawMinesweepBoard(HOR_MARGIN, VERT_MARGIN);
			inGame = board.playerAction();

		}
		if (board.gameWin()){
			System.out.println("Game Over");
		}
		else{
			System.out.println("You Win!");
		}
		board.victoryScreen(HOR_MARGIN, VERT_MARGIN);
		
		

	}

}

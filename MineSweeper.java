import java.util.*;

/**
 * Java Minesweeper Game
 * After related tutorials:
 * zetcode.com
 * betterprogramming.pub
 * tutorialflow.com
 * The coding train on YouTube
 *
 * Author: Tanguy Frémont
 */

public class MineSweeper {	

	private static final int BOARD_WIDTH = 7;
	private static final int BOARD_HEIGHT = 12;
	private static final int NBR_MINES = 10;
	
	public static void main(String[] args){

		
		
		MineSweeper M = new MineSweeper();
		M.startGame(NBR_MINES, BOARD_WIDTH, BOARD_HEIGHT); //Call to initializing method
	}

	public void startGame(int mineNumber, int boardWidth, int boardHeight){

		//GameBoard board = new GameBoard(boardWidth, boardHeight, mineNumber);
		GameBoard board = new GameBoard();

		boolean inGame = true;
		while(inGame){
			board.drawMinesweepBoard(3, 2);
			inGame = board.playerAction();

			if(board.gameWin()){
				board.victoryScreen(3, 2);
				break;
			}
		}

	}

}

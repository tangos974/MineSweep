import java.util.*;

public class GameBoard{

	int[][] gameBoard;
	int[][] playerBoard;

	int boardWidth;
	int boardHeight;

	public GameBoard(){
		this(10, 10, 10);
	}


	Scanner sc = new Scanner(System.in);


	public GameBoard(int width, int height, int minesNbr){
		
		this.boardWidth = width;
		this.boardHeight = height;

		this.gameBoard = new int[boardWidth][boardHeight];
		this.playerBoard = new int[boardWidth][boardHeight];

		int minesAdded = 0;
		int i,j;
		Random rd = new Random();

		while(minesAdded!=minesNbr){
			i = rd.nextInt(boardWidth);
			j = rd.nextInt(boardHeight);

			if(gameBoard[i][j] != -1){
				gameBoard[i][j] = -1;
				minesAdded++;
			}
		}

		for(i=0; i<boardWidth; i++){
			for(j=0; j<boardHeight;j++){
				if(gameBoard[i][j]!=-1) gameBoard[i][j]=countNghbrMines(i,j);
			}
		}
	}

	public int getHeight(){
		return gameBoard[0].length;
	}

	public int getWidth(){
		return gameBoard.length;
	}


	void drawMinesweepBoard(int horMargin, int vertMargin){

	/**
	 * Draws a 2D gameBoard on the shell using System.out.print
	 * The margins of the gameBoard must be positive integers
	 *
	 */

		printEqualLine(2*horMargin+4*boardWidth+2);
		System.out.print("\n");
		for(int i=0; i<vertMargin; i++) System.out.print("\n");

		printEmptyCharLine(1+horMargin);
		for(int i=0; i<boardWidth; i++){
			if(i<10){
				System.out.print(" ");
			}
			System.out.print(" " + i + " ");
		}
		printEmptyCharLine(horMargin);

		for(int i=0; i<vertMargin; i++) System.out.print("\n");

		for(int j=0; j<boardHeight; j++){
			System.out.print(j);
			printEmptyCharLine(horMargin-(j/10));
			System.out.print("| ");
			for(int i = 0; i<boardWidth; i++){
				if(playerBoard[i][j]==0){
					System.out.print("?");
				}
				else if(playerBoard[i][j]==50){
					System.out.print(" ");
				}
				else{
					System.out.print(playerBoard[i][j]);
				}
				System.out.print(" | ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}



	static void printEqualLine(int size){

	/** Prints size times the equal sign on a line
	*
	*/

		for(int i=0; i<size; i++){
			System.out.print("=");
		}
	}



	static void printEmptyCharLine(int size){

	/** Prints size times the empty space character on a line
	*   To the system.out.print output
	*/
		for(int i=0; i<size; i++){
			System.out.print(" ");
		}
	}


	int countNghbrMines(int i, int j){

	/**
	*
	* Returns number of neighbouring cells to the cell (i,j) that contain a mine
	*
	*
	*
	*
	*/
			int count = 0;

			for(int x=-1; x<2; x++){
				for(int y=-1; y<2; y++){
					if((i+x<0)||(j+y<0)){
						continue;
					}
					else if(i+x>=boardWidth||j+y>=boardHeight){
						continue;
					}
					else if(this.gameBoard[i+x][j+y]==-1){
						count++;
					}
				}
			}
			return count;
		}

	public boolean playerAction(){
		int i;
		do{
			System.out.print(" Enter valid column number: ");
			while(!sc.hasNextInt()){
				System.out.print(" Must be an integer: ");
 				sc.next();
			}
			i = sc.nextInt();
		} while (i<0 || i >= boardWidth);
		
		int j;
		do{
			System.out.print(" Enter valid row number: ");
			while(!sc.hasNextInt()) {
				System.out.print(" Must be an integer: ");
				 sc.next();
			}
			j = sc.nextInt();
		} while (j<0 || j >= boardHeight);

		if(this.gameBoard[i][j]==-1){
			System.out.println("\nOh no! A mine!");
			return false;
		}

		else if (gameBoard[i][j]==0){
			updateBoard(i,j);
		}
		else{
			updateNeighbors(i,j);
		}

		return this.gameWin();
	}

	public void updateBoard(int i, int j){
		playerBoard[i][j]=50;
		for(int x=-1; x<=1; x++){
			for(int y=-1; y<=1; y++){
				if((i+x>=0)&&(j+y>=0)&&(i+x<boardWidth)&&(j+y<boardHeight)){
					playerBoard[x+i][y+j]=gameBoard[x+i][y+j];
					if(playerBoard[x+i][y+j]==0) playerBoard[x+i][y+j] = 50;
				}
			}
		}

	}

	public void updateNeighbors(int i, int j){
	Random rd = new Random();
	int x = rd.nextInt()%4;

	playerBoard[i][j]=gameBoard[i][j];
	}

	public void victoryScreen(int horMargin, int vertMargin){
	
		printEqualLine(2*(2+horMargin)+4*boardWidth);
		for(int i=0; i<vertMargin; i++) System.out.print("\n");

		printEmptyCharLine(1+horMargin);
		for(int i=0; i<boardWidth; i++){
			if(i<10){
				System.out.print(" ");
			}
			System.out.print(" " + i + " ");
		}
		printEmptyCharLine(horMargin);

		for(int i=0; i<vertMargin; i++) System.out.print("\n");

		for(int j=0; j<boardHeight; j++){
			System.out.print(j);
			printEmptyCharLine(horMargin-(j/10));
			System.out.print("| ");
			for(int i = 0; i<boardWidth; i++){
				if(this.gameBoard[i][j]==0){
					System.out.print(" ");
				}
				else if(this.gameBoard[i][j]==-1){
					System.out.print("X");
				}
				else{
					System.out.print(gameBoard[i][j]);
				}
				System.out.print(" | ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		
	}


	public boolean gameWin(){
		for (int i=0; i<boardWidth; i++){
			for (int j = 0; j<boardHeight; j++){
				if(playerBoard[i][j]==0 && gameBoard[i][j]!=-1){
					return true;
				}
			}
		}
	return false;
	}

}	

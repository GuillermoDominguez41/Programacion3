package lightsOut;

import java.util.Random;

public class Main {

	private Integer [][] randomBoard;
	private Integer sizeBoard;

	public Main(Integer size) {
		sizeBoard = size;
		randomBoard = createRandomBoard();
	}
	
	protected Integer[][] createRandomBoard() {
		Integer [][] board = new Integer[sizeBoard][sizeBoard];
		Random rand = new Random();
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[row].length; col++) {
				board[row][col] = rand.nextInt(2);
			}
		}
		 // ONLY FOR TESTING FUNCTIONS !!!
			showBoard(board);
		 // END OF TEST
		return board;
	}
	
	public Integer [][] getRandomBoard() {
		return randomBoard;
	}
	
	 // ONLY FOR TESTING FUNCTIONS !!!
		protected void showBoard(Integer [][] board) {	
			StringBuilder st = new StringBuilder();
			for (Integer row = 0; row < board[0].length; row++) {
				for (Integer col = 0; col < board[0].length; col++) {
					st.append(board[row][col] + " ");
				}
				st.append("\n");
			}
			System.out.print( st.toString()+"\n");	
		}
	 // END OF TEST
	
}

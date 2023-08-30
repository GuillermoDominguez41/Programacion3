package lightsOut;

import java.util.Random;

public class Main {

	private Integer [][] board;
	private Integer sizeBoard;

	public Main(Integer size) {
		sizeBoard = size;
		board = createRandomBoard();
	}
	
	protected Integer[][] createRandomBoard() {
		board = new Integer[sizeBoard][sizeBoard];
		Random rand = new Random();
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[row].length; col++) {
				board[row][col] = rand.nextInt(2);
			}
		}
		 // ONLY FOR TESTING FUNCTIONS !!!
			showBoard();
		 // END OF TEST
		return board;
	}
	
	public Integer [][] getRandomBoard() {
		return board;
	}
	
	public void updateBoard(String position) {
		Integer row, col;
		
		if(position != null) {
			row =  Integer.parseInt( position.charAt(0) + "");
			col = Integer.parseInt( position.charAt(2) + "");
			
			toggleValueRow(row);
			toggleValueColumn(col);
		}

	}
	
	
	public void toggleValueRow(Integer row) {
		for (Integer col = 0; col < board[0].length; col++) {
			Integer currentItem = board[row][col];
			if(currentItem==0) {
				currentItem = 1;
			}else {
				currentItem = 0;
			}
		}
	}
	
	public void toggleValueColumn(Integer col) {
		for (Integer row = 0; row < board[0].length; row++) {
			Integer currentItem = board[row][col];
			if(currentItem==0) {
				currentItem = 1;
			} else {
				currentItem = 0;
			}
		}
	}
	
	
	 // ONLY FOR TESTING FUNCTIONS !!!
		protected void showBoard() {	
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

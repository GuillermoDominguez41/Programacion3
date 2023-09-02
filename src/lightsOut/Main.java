package lightsOut;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Main {

	private boolean[][] board;
	private Integer sizeBoard;
	private Integer turn;
	private Integer sizeMin;
	private Integer sizeMax;
	private Integer percentCompleted;

	public Main(Integer size) {
		sizeBoard = size;
		turn = 0;
		sizeMin = 4;
		sizeMax = 6;
		percentCompleted = 0;
		
	// ONLY FOR TESTING!!!
		 board = createRandomBoard(size);
//		board = createPreconfiguredBoard(size);
		showBoard();
	// END OF TEST		
		
		updatePercentCompleted();
	}
	
	protected boolean[][] createRandomBoard(Integer sizeBoard) {
		boolean[][] newBoard = new boolean[sizeBoard][sizeBoard];
		Random rand = new Random();
		for (Integer row = 0; row < newBoard.length; row++) {
			for (Integer col = 0; col < newBoard[row].length; col++) {
				newBoard[row][col] = rand.nextBoolean();
			}
		}
		return newBoard;
	}

	public boolean[][] getRandomBoard() {
		return board;
	}

	public Integer getSize() {
		return sizeBoard;
	}

	public void updateBoard(String position) {
		Integer row, col;
		if (position != null) {
			row = Integer.parseInt(position.substring(4, 6).replace(" ", ""));
			col = Integer.parseInt(position.substring(10, 12).replace(" ", ""));
			toggleValueRow(row);
			toggleValueColumn(row, col);
		}
	}

	public void toggleValueRow(Integer row) {
		for (Integer col = 0; col < board[0].length; col++) {
			boolean currentItem = board[row][col];
			board[row][col] = currentItem == false ? true : false;
		}
	}

	public void toggleValueColumn(Integer row, Integer col) {
		for (Integer fil = 0; fil < board.length; fil++) {
			boolean currentItem = board[fil][col];
			if (fil != row) {
				board[fil][col] = currentItem == false ? true : false;
			}
		}
	}

	public Integer getTurn() {
		return turn;
	}

	public void increaseTurn() {
		turn++;
	}

	public Integer getBoardSizeMin() {
		return sizeMin;
	}

	public Integer getBoardSizeMax() {
		return sizeMax;
	}
	
	public void updatePercentCompleted() {
		Integer acumLight = 0;
		Integer acumLightsOff = 0;
		
		for(boolean[] row : board) {
			for(boolean elem : row) {
				acumLight++;
				if(elem == false)
					acumLightsOff++;
			}
		}
		float decCompleted = (float) acumLightsOff/acumLight;
		percentCompleted = (int) (decCompleted*100);
	}
	
	public Integer getPercentCompleted() {
		return percentCompleted;
	}
	
	public boolean gameComplete() {
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[0].length; col++) {
				if(board[row][col]==false)
					return false;
			}
		}
		return true;
	}

// ONLY FOR TESTING FUNCTIONS !!!
	protected void showBoard() {
		StringBuilder st = new StringBuilder();
		for(boolean[] row : board) {
			for(boolean elem : row) {
				st.append( elem + " ");
			}
			st.append("\n");
		}	
		System.out.print(st.toString() + "\n");
	}

	protected boolean[][] createPreconfiguredBoard(Integer sizeBoard) {
		boolean[][] newBoard = new boolean[sizeBoard][sizeBoard];
		for (Integer row = 0; row < newBoard.length; row++) {
			if (row != 0) {
				for (Integer col = 0; col < newBoard[0].length; col++) {
					if (col != 0)
						newBoard[row][col] = true;
				}
			}
		}
		return newBoard;	
	}
// END OF TEST

}

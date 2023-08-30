package lightsOut;

import java.util.Random;

public class Main {

	private Integer[][] board;
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
		// ONLY FOR TESTING!!!
		showBoard();
		// END OF TEST
		return board;
	}

	public Integer[][] getRandomBoard() {
		return board;
	}

	public Integer getSize() {
		return sizeBoard;
	}
	
	public void updateBoard(String position) {
		Integer row, col;
		if (position != null) {
			row = Integer.parseInt( position.substring(4, 6).replace(" ", "") );
			col = Integer.parseInt( position.substring(10, 12).replace(" ", "") );
			toggleValueRow(row);
			toggleValueColumn(row, col);
		}
	}

	public void toggleValueRow(Integer row) {
		for (Integer col = 0; col < board[0].length; col++) {
			Integer currentItem = board[row][col];
			board[row][col] = currentItem == 0 ? 1 : 0;
		}
	}

	public void toggleValueColumn(Integer row, Integer col) {
		for (Integer fil = 0; fil < board[0].length; fil++) {
			Integer currentItem = board[fil][col];
			if (fil != row) {
				board[fil][col] = currentItem == 0 ? 1 : 0;
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
		System.out.print(st.toString() + "\n");
	}
	// END OF TEST

}

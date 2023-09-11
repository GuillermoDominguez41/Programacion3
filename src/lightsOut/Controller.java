package lightsOut;

public class Controller {
	Main MN;

	public Controller() {
		MN = new Main();
	}
	
	public void createBoard(Integer sizeBoard) {
		MN.createBoard(sizeBoard);
	}

	public boolean[][] getBoard() {
		return MN.getRandomBoard();
	}

	public Integer getSizeBoard() {
		return MN.getSize();
	}

	public void updateBoard(String position) {
		MN.updateBoard(position);
	}

	public void showBoard() {
		MN.showBoard();
	}

	public Integer getTurn() {
		return MN.getTurn();
	}

	public void increaseTurn() {
		MN.increaseTurn();
	}

	public boolean gameComplete() {
		return MN.gameComplete();
	}
	
	public Integer getBoardSizeMin() {
		return MN.getBoardSizeMin();
	}

	public Integer getBoardSizeMax() {
		return MN.getBoardSizeMax();
	}
	
	public void updatePercentCompleted() {
		MN.updatePercentCompleted();
	}
	
	public Integer getPercentCompleted() {
		return MN.getPercentCompleted();
	}

	public String[] getSavedScores() {
		return MN.getSavedScores();
	}
	
	public boolean checkNewScore(String player) {
		return MN.checkNewScore(player);
	}
	
	public Integer getRowSolution() {
		return MN.getRowSolution();
	}
	
	public Integer getColSolution() {
		return MN.getColSolution();
	}
	
	public void updateStackSolution(String position) {
		MN.updateStackSolution(position);
	}
	
}

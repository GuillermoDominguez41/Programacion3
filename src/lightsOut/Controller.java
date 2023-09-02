package lightsOut;

public class Controller {
	Main MN;

	public Controller(Integer size) {
		MN = new Main(size);
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
}

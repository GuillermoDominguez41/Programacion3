package lightsOut;

public class Controller {
	Main MN;

	public Controller(Integer size) {
		MN = new Main(size);
	}

	public Integer[][] getBoard() {
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
	
}

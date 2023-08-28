package lightsOut;

public class Controller {
	Main MN;

	public Controller(Integer size) {
		MN = new Main(size);
	}
	
	public Integer[][] getRandomBoard() {
		return MN.getRandomBoard();
	}
	
}

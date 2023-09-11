package lightsOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Stack;

public class Main {

	private boolean[][] board;
	private Stack<String> solution;
	private Integer size;
	private Integer turn;
	private Integer sizeMin;
	private Integer sizeMax;
	private Integer percentCompleted;
	private String pathScoreFile;
	private String[] savedScores;
	
	public Main() {
		turn = 0;
		sizeMin = 4;
		sizeMax = 6;
		percentCompleted = 0;
		pathScoreFile = "src/lightsOut/SavedScore.txt";
		savedScores = readScoreFile(pathScoreFile);
	}
		
	public void createBoard(Integer sizeBoard) {
		if (sizeBoard == 4) {
			boolean[][] matrix4x4 = { 
					{ true, false, false, true }, 
					{ true, true, false, false },
					{ false, false, false, false }, 
					{ false, true, false, true }
			};
			this.board = matrix4x4;
			Stack<String> stack4x4 = new Stack<String>();
			stack4x4.push("0-0");
			stack4x4.push("1-1");
			stack4x4.push("1-0");
			stack4x4.push("3-3");
			stack4x4.push("3-1");
			stack4x4.push("0-3");
			this.solution = stack4x4;
		}
		if(sizeBoard == 5) {
			boolean[][] matrix5x5 = { 
					{ false, false, false, true, false }, 
					{ true, false, false, true, true }, 
					{ true, true, false, false, true }, 
					{ false, true, true, false, true }, 
					{ true, true, false, true, false }
			};
			this.board = matrix5x5;
			Stack<String> stack5x5 = new Stack<String>();
				stack5x5.push("0-0");
				stack5x5.push("1-4");
				stack5x5.push("2-2");
				stack5x5.push("2-0");
				stack5x5.push("4-1");
			this.solution = stack5x5;
		}
		
		if(sizeBoard == 6) {
			boolean[][] matrix6x6 = { 
					{ false, true, false, false, false, false }, 
					{ true, false, true, false, true, true }, 
					{ false, true, false, false, false, false }, 
					{ true, true, true, true, true, true },
					{ true, false, true, false, true, true }, 
					{ false, true, false, false, false, false }
			};
			this.board = matrix6x6;
			Stack<String> stack6x6 = new Stack<String>();
			stack6x6.push("1-3");
			stack6x6.push("3-1");
			stack6x6.push("4-3");
			this.solution = stack6x6;
		}
		updatePercentCompleted();
		this.size = sizeBoard;
	}
	
	public void updateStackSolution(String pressedLight) {
		if (pressedLight != null) {
			Integer rowLight = Integer.parseInt(pressedLight.substring(4, 6).replace(" ", ""));
			Integer colLight = Integer.parseInt(pressedLight.substring(10, 12).replace(" ", ""));		
			if(rowLight == getRowSolution() && colLight == getColSolution() ) {
				solution.pop();
			} else {
				solution.push(rowLight + "-" + colLight);
			}
		}		
	}
	
	public void increaseTurn() {
		turn++;
	}
	
	public Integer getRowSolution() {
		Integer row = Integer.parseInt( solution.peek().substring(0, 1) );
		return row;
	}
	
	public Integer getColSolution() {
		Integer col = Integer.parseInt( solution.peek().substring(2, 3) );
		return col;
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
	
	public boolean gameComplete() {
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[0].length; col++) {
				if(board[row][col]==true)
					return false;
			}
		}
		return true;
	}
	
	private String[] readScoreFile (String pathScoreFile) {
		String[] scores = new String[10];
		BufferedReader reader;
		FileReader fileRead;
		File file;
		
		try {
			file = new File(pathScoreFile);
			fileRead = new FileReader(file);
			if(fileRead.ready()) {
				reader = new BufferedReader(fileRead);
				String textLine;
				Integer countLine = 0;
				while( (textLine = reader.readLine()) != null) {
					scores[countLine] = textLine;
					countLine+=1;
				}
				fileRead.close();
				return scores;
			} else {
				System.out.println("The file is not ready to be read.");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
		return scores;
	}
	
	public boolean checkNewScore(String player) {
		Boolean moveRegister = false;
		String lastRegister = "";
		String aux = "";
		Integer itemTurn;
		
		for(int i=0; i < savedScores.length; i++) {
			if(savedScores[i] != null) {
				if(moveRegister) {
					aux = savedScores[i].toString();
					savedScores[i] = lastRegister;
					lastRegister = aux;
				} else {
					itemTurn = Integer.parseInt( savedScores[i].substring(7,10) );
					if(turn < itemTurn) {
						String formatedPlayer = player.substring(0,3);
						String formatedSize = "#0" + size;
						String formatedTurn = (turn > 100) ? "#"+turn : (turn > 10 && turn < 100) ? "#0"+turn : "#00"+turn;
						lastRegister = savedScores[i].toString(); // Almaceno el registro que voy a mover a la siguiente posicion
						savedScores[i] = formatedPlayer+formatedSize+formatedTurn;
						moveRegister = true;
					}
				}
			}
		}	
		writeScoreFile();
		return moveRegister;
	}
		
	protected void writeScoreFile () {
		FileWriter file = null;
		
		try {
			file = new FileWriter(pathScoreFile);
			
			for (String score : savedScores) {
				file.write(score + "\n");
			}

			file.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
// GETTERS AND SETTERS 
	public boolean[][] getRandomBoard() {
		return board;
	}

	public Integer getSize() {
		return size;
	}
	
	public Integer getTurn() {
		return turn;
	}
	
	public Integer getBoardSizeMin() {
		return sizeMin;
	}

	public Integer getBoardSizeMax() {
		return sizeMax;
	}
		
	public String[] getSavedScores() {
		return savedScores;
	}
	
	public Integer getPercentCompleted() {
		return percentCompleted;
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

	public void createPreconfiguredBoard(Integer sizeBoard) {
		size = sizeBoard;
		boolean[][] matrixPreset = { 
				{ true, true, true, true }, 
				{ true, false, false, false },
				{ true, false, false, false }, 
				{ true, false, false, false }
		};
		this.board = matrixPreset;
		Stack<String> stackPreset = new Stack<String>();
			stackPreset.push("0-0");
		this.solution = stackPreset;
		updatePercentCompleted();
	}
	
	public void createLightsOffBoard(Integer sizeBoard) {
		size = sizeBoard;
		board = new boolean[sizeBoard][sizeBoard];
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[0].length; col++) {
					board[row][col] = false;
			}
		}
		updatePercentCompleted();
	}
	
	public void createRandomBoard(Integer sizeBoard) {
		size = sizeBoard;
		board = new boolean[sizeBoard][sizeBoard];
		Random rand = new Random();
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[row].length; col++) {
				board[row][col] = rand.nextBoolean();
			}
		}
		updatePercentCompleted();
	}
	
	protected void showScores(){
		for(String line : savedScores)
			System.out.println(line);
	}
// END OF TEST
	
}

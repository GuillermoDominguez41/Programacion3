package lightsOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class Main {

	private boolean[][] board;
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
//		GENERAR TABLERO PRESETEADO CON SU HOJA DE RUTA O PASO A PASO PARA RESOLVER
	}


	public boolean[][] getRandomBoard() {
		return board;
	}

	public Integer getSize() {
		return size;
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
	
	private String[] readScoreFile (String pathScoreFile) {
		String[] puntajes = new String[10];
		BufferedReader lector;
		FileReader archivo;
		File f;
		
		try {
			f = new File(pathScoreFile);
			archivo = new FileReader(f);
			if(archivo.ready()) {
				lector = new BufferedReader(archivo);
				String cadena;
				Integer linea = 0;
				while( (cadena = lector.readLine()) != null) {
					puntajes[linea] = cadena;
					linea+=1;
				}
				archivo.close();
				return puntajes;
			} else {
				System.out.println("El archivo no esta listo para ser leido...");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage() );
		}
		return puntajes;
	}
	
	public String[] getSavedScores() {
		return savedScores;
	}
	
	public boolean checkNewScore(String player) {
		Boolean desplazarRegistros = false;
		String ultimoRegistro = "";
		String auxRegistro = "";
		Integer itemTurn;
		
		for(int i=0; i < savedScores.length; i++) {
			if(savedScores[i] != null) {
				if(desplazarRegistros) {
					auxRegistro = savedScores[i];
					savedScores[i] = ultimoRegistro;
					ultimoRegistro = auxRegistro;
				} else {
					itemTurn = Integer.parseInt( savedScores[i].substring(7,10) );
					if(turn < itemTurn) {
						String formatedPlayer = player.substring(0,3);
						String formatedSize = "#0" + size;
						String formatedTurn = (turn > 100) ? "#"+turn : (turn > 10 && turn < 100) ? "#0"+turn : "#00"+turn;
						ultimoRegistro = savedScores[i];
						savedScores[i] = formatedPlayer+formatedSize+formatedTurn;
						desplazarRegistros = true;
					}
				}
			}
		}	
		writeScoreFile();
		return desplazarRegistros;
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
		board = new boolean[sizeBoard][sizeBoard];
		for (Integer row = 0; row < board.length; row++) {
			if (row != 0) {
				for (Integer col = 0; col < board[0].length; col++) {
					if (col != 0)
						board[row][col] = true;
				}
			}
		}
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
	
	protected void showScores()
	{
		for(String line : savedScores) {
			System.out.println(line);
		}
	}
// END OF TEST

}

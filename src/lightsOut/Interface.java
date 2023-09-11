package lightsOut;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Interface {

	private JFrame frame;
	private Controller CTR;
	private JPanel panelLight;
	private JPanel panelGameStatus;
	private JTextField lblTurn;
	private JTextField txtPlayer;
	private JProgressBar progressBar;
	private Color lightOff;
	private Color lightOn;
	private Color lightSolution;
	private JComboBox<Integer> cbxSize;
	private JButton btnStartGame;

	/** Launch the application **/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application **/
	public Interface() {
		initialize();
	}

	/** Initialize the contents of the frame **/
	private void initialize() {
		CTR = new Controller();
		lightOff = new Color(187, 196, 187);
		lightOn = new Color(137, 234, 120);
		lightSolution = new Color(238, 252, 0);

		createAppWindow();
		createPanelInfo();
	}

	public void createAppWindow() {
		frame = new JFrame();
		frame.setTitle("Lights Out");
		frame.setBounds(100, 200, 927, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	private void createPanelInfo() {
		JPanel panelGameInfo = new JPanel();
			panelGameInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			panelGameInfo.setBounds(10, 10, 251, 350);
			panelGameInfo.setLayout(null);
		frame.getContentPane().add(panelGameInfo);
		
		JLabel lblTitleSize = new JLabel("Tamaño del tablero");
			lblTitleSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitleSize.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleSize.setBounds(10, 10, 231, 13);
		panelGameInfo.add(lblTitleSize);

		cbxSize = new JComboBox<Integer>();
			cbxSize.setBounds(10, 28, 90, 21);
			for(int i=CTR.getBoardSizeMin(); i<=CTR.getBoardSizeMax(); i++ )
				cbxSize.addItem(i);
		panelGameInfo.add(cbxSize);
		
		JLabel lblTitlePlayer = new JLabel("Iniciales Jugador");
			lblTitlePlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitlePlayer.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitlePlayer.setBounds(10, 60, 231, 13);
		panelGameInfo.add(lblTitlePlayer);

		txtPlayer = new JTextField("AAA");
			txtPlayer.setBounds(10, 78, 90, 20);
		panelGameInfo.add(txtPlayer);

		JLabel lblTitleInstructions = new JLabel("Instrucciones");
			lblTitleInstructions.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitleInstructions.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleInstructions.setBounds(10, 110, 231, 13);
		panelGameInfo.add(lblTitleInstructions);
		
		JTextPane txtInstructions = new JTextPane();
			txtInstructions.setFont(new Font("Arial", Font.PLAIN, 12));
			txtInstructions.setEditable(false);
			txtInstructions.setText("Lights Out es un juego de puzzle que consiste en una cuadrícula de luces que están encendidas (verde claro) o apagadas (verde oscuro). Al pulsar una luz se activan todas las luces de su fila y columna. El objetivo del juego es apagar todas las luces.");
			txtInstructions.setBounds(10, 133, 231, 112);
		panelGameInfo.add(txtInstructions);
		
		btnStartGame = new JButton("Iniciar Juego");
			btnStartGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Integer size = Integer.parseInt(cbxSize.getSelectedItem().toString());
					btnStartGame.setText("Nuevo Juego");
					CTR.createBoard(size);
					createPanelLights(size);
					addLightsToPanel(size, CTR.getBoard());
					createPanelStatus();
					frame.repaint();
				}
			});
			btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnStartGame.setBounds(10, 266, 231, 40);
		panelGameInfo.add(btnStartGame);
	}
	
	private void createPanelStatus() {
		panelGameStatus = new JPanel();
			panelGameStatus.setLayout(null);
			panelGameStatus.setBounds(654, 10, 251, 350);
			panelGameStatus.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frame.getContentPane().add(panelGameStatus);
		
		JLabel lblTitleTurn = new JLabel("Turno actual");
			lblTitleTurn.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitleTurn.setBounds(10, 10, 231, 13);
			lblTitleTurn.setHorizontalAlignment(SwingConstants.CENTER);
		panelGameStatus.add(lblTitleTurn);

		lblTurn = new JTextField(CTR.getTurn().toString());
			lblTurn.setBackground(new Color(255, 255, 255));
			lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTurn.setBounds(10, 24, 231, 20);
			lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		panelGameStatus.add(lblTurn);
		

		JLabel lblTitleProgressBar = new JLabel("Avance tablero");
			lblTitleProgressBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitleProgressBar.setBounds(10, 50, 231, 13);
			lblTitleProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		panelGameStatus.add(lblTitleProgressBar);
		
		progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setToolTipText("");
			progressBar.setForeground(new Color(128, 255, 128));
			progressBar.setBounds(10, 64, 231, 20);
			progressBar.setValue(CTR.getPercentCompleted());
		panelGameStatus.add(progressBar);

		JLabel lblTitleRecords = new JLabel("Records");
			lblTitleRecords.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTitleRecords.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleRecords.setBounds(10, 145, 231, 13);
		panelGameStatus.add(lblTitleRecords);
		
		JScrollPane tableRecords = createScorePanel(CTR.getSavedScores());
		panelGameStatus.add(tableRecords);
		
		JLabel lblSolution = new JLabel("Siguiente paso");
			lblSolution.setHorizontalAlignment(SwingConstants.CENTER);
			lblSolution.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSolution.setBounds(10, 94, 231, 13);
		panelGameStatus.add(lblSolution);
		
		JButton btnShowSolution = new JButton("Mostrar solucion");
			btnShowSolution.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showSoluctionToPanel();
				}
			});
			btnShowSolution.setBounds(50, 110, 150, 21);
		panelGameStatus.add(btnShowSolution);
		
	}
	
	protected JScrollPane createScorePanel(String[] savedScores) {
		String[] columns = {"Posicion", "Jugador", "Tamaño", "Turno"};
						
		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);
		tableModel.setColumnIdentifiers(columns);
		for(int i=0; i < savedScores.length; i++) {
			String position = Integer.toString(i+1);
			String player = savedScores[i].substring(0,3);
			String size = savedScores[i].substring(4,6);
			String turn = savedScores[i].substring(7,10);
			tableModel.addRow(new Object[] {position,player,size, turn});
		}
		table.setModel(tableModel);
		
		DefaultTableCellRenderer tableCR = new DefaultTableCellRenderer();
		tableCR.setHorizontalAlignment(SwingConstants.CENTER);
		for(int c=0; c < columns.length; c++) {
			table.getColumnModel().getColumn(c).setCellRenderer(tableCR);
		}
		
		JScrollPane tablePanel = new JScrollPane(table); 
		tablePanel.setBounds(10, 160, 231, 183);
		
		return tablePanel;
	}
	
	public void createPanelLights(Integer sizeBoard) {
		panelLight = new JPanel();
		panelLight.setBounds(283, 10, 350, 350);
		panelLight.setLayout(new GridLayout(sizeBoard, sizeBoard, 2, 2));
		panelLight.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panelLight);
	}

	private void addLightsToPanel(Integer sizeBoard, boolean[][] booleanBoard) {
		Integer lightIndex = 0;
		for (Integer row = 0; row < booleanBoard[0].length; row++) {
			for (Integer col = 0; col < booleanBoard[0].length; col++) {
				boolean valCurrent = booleanBoard[row][col];
				String posCurrent = "Row:" + row + " Col:" + col + " Index:" + lightIndex;
				JTextField light = new JTextField();
				light.setName(posCurrent);
				light.setHorizontalAlignment(SwingConstants.CENTER);
				light.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String position = e.getComponent().getName();
						updateLightsToPanel(sizeBoard, CTR.getBoard(), position);
						CTR.updateStackSolution(position);
						updateTurn();
						
						if (CTR.gameComplete()) {
							endGame();
						}
					}
				});
				Color background = valCurrent == false ? lightOff : lightOn;
				light.setBackground(background);
				panelLight.add(light);
				lightIndex++;
			}
		}
	}

	private void updateLightsToPanel(Integer sizeBoard, boolean[][] booleanBoard, String position) {
		CTR.updateBoard(position);
		Integer lightIndex = 0;
		for (boolean[] row : booleanBoard) {
			for (boolean elem : row) {
				JTextField componentCurrent = (JTextField) panelLight.getComponent(lightIndex);
				Color background = elem == false ? lightOff : lightOn;
				componentCurrent.setBackground(background);
				lightIndex++;
			}
		}
		frame.repaint();
	}
	
	private void showSoluctionToPanel() {
		boolean[][] board = CTR.getBoard();
		Integer lightIndex = 0;
		for (Integer row = 0; row < board.length; row++) {
			for (Integer col = 0; col < board[0].length; col++) {				
				if(row == CTR.getRowSolution() && col == CTR.getColSolution()) {
					JTextField componentCurrent = (JTextField) panelLight.getComponent(lightIndex);
					componentCurrent.setBackground(lightSolution);
				}
				lightIndex++;
			}
		}
		frame.repaint();
	}

	private void updateTurn() {
		CTR.increaseTurn();
		lblTurn.setText(CTR.getTurn().toString());
		frame.repaint();
	}

	private void endGame() {		
		JPanel panelEndGame = new JPanel();
		panelEndGame.setLayout(null);
		
		JLabel lblGameComplete = new JLabel("¡¡Felicitaciones, juego completo!!");
		lblGameComplete.setForeground(new Color(0, 189, 0));
		lblGameComplete.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGameComplete.setBounds(10, 10, 370, 45);
		lblGameComplete.setHorizontalAlignment(SwingConstants.CENTER);
		panelEndGame.add(lblGameComplete);
		
		if( CTR.checkNewScore(txtPlayer.getText()) ){
			JLabel lblnewRecord = new JLabel("¡¡Superaste una marca registrada!!");
			lblnewRecord.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblnewRecord.setForeground(new Color(0, 189, 0));
			lblnewRecord.setBounds(10, 30, 370, 30);
			lblnewRecord.setHorizontalAlignment(SwingConstants.CENTER);
			panelEndGame.add(lblnewRecord);
			
			lblGameComplete.setBounds(10, 10, 380, 30);
		}

		JScrollPane tableRecords = createScorePanel(CTR.getSavedScores());
		tableRecords.setBounds(10, 60, 370, 183);
		panelEndGame.add(tableRecords);
		
		frame.setBounds(100, 200, 400, 300);
		frame.getContentPane().setLayout(new GridLayout());
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panelEndGame);
		frame.repaint();
	}
}

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
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class Interface {

	private JFrame frame;
	private Controller CTR;
	private JPanel panelLight;
	private JTextField lblTurn;
	private JTextField txtPlayer;
	private JProgressBar progressBar;
	private Color lightOff;
	private Color lightOn;

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
		Integer sizeBoard = 4;
		CTR = new Controller(sizeBoard);
		lightOff = new Color(187, 196, 187);
		lightOn = new Color(137, 234, 120);

		createAppWindow();
		createPanelInfo();
		createPanelLights(sizeBoard);
		addLightsToPanel(sizeBoard, CTR.getBoard());
		createPanelStatus();
//		createTurnsBoard();
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

		JComboBox<Integer> cbxSize = new JComboBox<Integer>();
		cbxSize.setBounds(10, 28, 90, 21);
		for(int i=CTR.getBoardSizeMin(); i<=CTR.getBoardSizeMax(); i++ )
			cbxSize.addItem(i);
		panelGameInfo.add(cbxSize);
		
		JLabel lblTitlePlayer = new JLabel("Iniciales Jugador");
		lblTitlePlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitlePlayer.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitlePlayer.setBounds(10, 60, 231, 13);
		panelGameInfo.add(lblTitlePlayer);

		txtPlayer = new JTextField();
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
	}
	
	private void createPanelStatus() {
		JPanel panelGameStatus = new JPanel();
		panelGameStatus.setLayout(null);
		panelGameStatus.setBounds(654, 10, 251, 350);
		panelGameStatus.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		frame.getContentPane().add(panelGameStatus);
		
		JLabel lblTitleTurn = new JLabel("Turno actual");
		lblTitleTurn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitleTurn.setBounds(10, 10, 231, 13);
		panelGameStatus.add(lblTitleTurn);
		lblTitleTurn.setHorizontalAlignment(SwingConstants.CENTER);

		lblTurn = new JTextField(CTR.getTurn().toString());
		lblTurn.setBackground(new Color(255, 255, 255));
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTurn.setBounds(10, 24, 231, 20);
		panelGameStatus.add(lblTurn);
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblTitleProgressBar = new JLabel("Avance tablero");
		lblTitleProgressBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitleProgressBar.setBounds(10, 56, 231, 13);
		lblTitleProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		panelGameStatus.add(lblTitleProgressBar);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("");
		progressBar.setForeground(new Color(128, 255, 128));
		progressBar.setBounds(10, 73, 231, 20);
		progressBar.setValue(CTR.getPercentCompleted());
		panelGameStatus.add(progressBar);

		JLabel lblTitleRecords = new JLabel("Records");
		lblTitleRecords.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitleRecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleRecords.setBounds(10, 105, 231, 13);
		panelGameStatus.add(lblTitleRecords);

		JTable tblRecords = new JTable();
		tblRecords.setBounds(10, 123, 231, 217);
		panelGameStatus.add(tblRecords);
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
				JTextField light = new JTextField(String.valueOf(valCurrent));
				light.setName(posCurrent);
				light.setHorizontalAlignment(SwingConstants.CENTER);
				light.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						updateLightsToPanel(sizeBoard, CTR.getBoard(), e.getComponent().getName());
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
				componentCurrent.setText(String.valueOf(elem));
				Color background = elem == false ? lightOff : lightOn;
				componentCurrent.setBackground(background);
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
		JPanel panelGanaste = new JPanel();
		panelGanaste.setLayout(new GridLayout());
		JLabel lblGanaste = new JLabel("Ganaste");
		lblGanaste.setHorizontalAlignment(SwingConstants.CENTER);
		panelGanaste.add(lblGanaste);
		
		frame.setBounds(100, 200, 600, 500);
		frame.getContentPane().setLayout(new GridLayout());
		frame.getContentPane().removeAll();
		frame.getContentPane().add(panelGanaste);
		frame.repaint();
	}
}

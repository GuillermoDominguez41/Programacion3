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

public class Interface {

	private JFrame frame;
	private Controller CTR;
	private JPanel panelLight;
	private JLabel lblTurn;

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

		createAppWindow();
		createPanelLights(sizeBoard);
		addLightsToPanel(sizeBoard, CTR.getBoard());
		createTurnsBoard();
	}

	public void createAppWindow() {
		frame = new JFrame();
		frame.setTitle("Lights Out");
		frame.setBounds(100, 200, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	public void createPanelLights(Integer sizeBoard) {
		panelLight = new JPanel();
		panelLight.setBounds(25, 64, 253, 248);
		panelLight.setLayout(new GridLayout(sizeBoard, sizeBoard, 2, 2));
		frame.getContentPane().add(panelLight);
	}

	private void createTurnsBoard() {
		JPanel panelTurns = new JPanel();
		panelTurns.setBounds(360, 34, 214, 40);
		frame.getContentPane().add(panelTurns);

		JLabel lblTitleTurn = new JLabel("Turnos");
		lblTitleTurn.setHorizontalAlignment(SwingConstants.CENTER);
		panelTurns.add(lblTitleTurn);

		lblTurn = new JLabel(CTR.getTurn().toString());
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
		panelTurns.add(lblTurn);
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
				Color background = valCurrent == false ? Color.BLACK : Color.GREEN;
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
				Color background = elem == false ? Color.BLACK : Color.GREEN;
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

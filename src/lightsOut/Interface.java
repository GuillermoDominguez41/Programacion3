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

public class Interface {

	private JFrame frame;
	private Controller CTR;

	private JPanel panelLuces;

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

		frame = new JFrame();
		frame.setTitle("Lights Out");
		frame.setBounds(100, 200, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		generarPanelLuces(sizeBoard);
		actualizarGrilla(sizeBoard);

	}

	private void actualizarGrilla(Integer sizeBoard) {

		if (panelLuces != null) {
			panelLuces.removeAll();
		}

		CTR.showBoard();
		aniadirLucesPanel(sizeBoard, CTR.getBoard());
		frame.repaint();
	}

	public void generarPanelLuces(Integer sizeBoard) {
		panelLuces = new JPanel();
		panelLuces.setBounds(25, 64, 253, 248);
		panelLuces.setLayout(new GridLayout(sizeBoard, sizeBoard, 2, 2));

		frame.getContentPane().add(panelLuces);
		
		

	}

	private void aniadirLucesPanel(Integer sizeBoard, Integer[][] booleanBoard) {
		System.out.println("entra");
		
		for (Integer row = 0; row < booleanBoard[0].length; row++) {
	
			for (Integer col = 0; col < booleanBoard[0].length; col++) {

				Integer valCurrent = booleanBoard[row][col];
				String posCurrent = (row) + "-" + (col);
				System.out.println(posCurrent);
				JTextField lbl_Luz = new JTextField(valCurrent.toString());
				lbl_Luz.setName(posCurrent);
				lbl_Luz.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_Luz.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(e.getComponent().getName());
						CTR.updateBoard(e.getComponent().getName());
						actualizarGrilla(sizeBoard);
					}
				});

				Color background = valCurrent == 0 ? Color.BLACK : Color.GREEN;
				lbl_Luz.setBackground(background);

				panelLuces.add(lbl_Luz);
			}
		}
	}
}

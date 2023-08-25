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
		
		frame = new JFrame();
		frame.setTitle("Lights Out");
		frame.setBounds(100, 200, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//	Panel principal de luces
		
		Integer size = 4;
		
		JPanel panelLuces = new JPanel();
		panelLuces.setBounds(25, 64, 253, 248);
		panelLuces.setLayout(new GridLayout(size, size, 2, 2));
		frame.getContentPane().add(panelLuces);
		
		for(Integer i = 0; i < (size*size); i++) {
			// Reemplazar i por la posicion de la matriz, ejemplo 1_3 (fila/columna)
			JTextField lbl_Luz = new JTextField( i.toString() );
			lbl_Luz.setName( i.toString() );
			lbl_Luz.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Luz.setBackground(Color.RED);
			lbl_Luz.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println( e.getComponent().getName() );
				}
			});
			panelLuces.add(lbl_Luz);
		}
		
	}
}

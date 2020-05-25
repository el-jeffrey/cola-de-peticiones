import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ColaDePeticionesAplicacion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColaDePeticionesAplicacion frame = new ColaDePeticionesAplicacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ColaDePeticionesAplicacion() {
		setTitle("Cola de Peticiones HTTP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		contentPane.add(splitPane, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Ingrese los request a la Cola de Peticiones HTTP");
		splitPane.setLeftComponent(lblNewLabel);
		
		JButton btnNewButton = new JButton("Nuevo");
		splitPane.setRightComponent(btnNewButton);

	}

}

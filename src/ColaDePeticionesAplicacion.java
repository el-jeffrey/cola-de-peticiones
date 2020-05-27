import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ColaDePeticionesAplicacion extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private static JDialog d;
	static JFrame f;
	String tituloCapturaPeticion = "Nueva Petición";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColaDePeticionesAplicacion frame = new ColaDePeticionesAplicacion();
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 960, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Ingrese los request a la Cola de Peticiones HTTP");

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setActionCommand("get-request");
		btnNuevo.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
								.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(btnNuevo))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(51, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { 1, "GET", "Crear Usuario" } },
				new String[] { "#", "Método", "Título", "URL", "Body", "Autenticación", "Headers", "" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, true, true, true, false, true };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

	}

	public void actionPerformed(ActionEvent ae) {
		String choice = ae.getActionCommand();
		if (choice.equals("Quit")) {
			System.exit(0);
		} else if (choice.equals("get-request")) {
//			CapturarPeticion dlg = new CapturarPeticion(this, tituloCapturaPeticion);
			GetPeticion  dlg = new GetPeticion();
			String results = dlg.run();
			/*if (results[0] != null) {
				JOptionPane.showMessageDialog(this, results[0] + ", color: " + results[1]);
			}*/
		}
	}

}

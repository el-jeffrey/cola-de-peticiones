
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Modelo.ClsRequestDTO;
import Utilidades.Utilidades;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ColaDePeticionesAplicacion extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6775312666216508120L;

	private JPanel contentPane;
	private JTable table;
	static JFrame f;
	String tituloCapturaPeticion = "Nueva petición";

	Integer cantidad = 0;

	String cadenaExportacion = "";

	List<ClsRequestDTO> lista = new ArrayList<ClsRequestDTO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColaDePeticionesAplicacion frame = new ColaDePeticionesAplicacion();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Mis documentos\\Software\\RojoMuro\\cola-de-peticiones\\src\\assets\\logo32.png"));
		setTitle("Cola de peticiones HTTP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1088, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Ingrese los REQUESTS a la cola de peticiones HTTP");

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setActionCommand("get-request");
		btnNuevo.addActionListener(this);

		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.setActionCommand("procesar");
		btnProcesar.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnExportarAPdf = new JButton("Exportar a PDF");
		btnExportarAPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cadenaExportacion != null && !cadenaExportacion.trim().isEmpty()) {
						Utilidades.onGenerarDocumentoPDF(cadenaExportacion);

						JOptionPane.showMessageDialog(null, "Documento generado.", "Mensaje",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "Documento vacío.", "Mensaje", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JButton btnExportarATxt = new JButton("Exportar a TXT");
		btnExportarATxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cadenaExportacion != null && !cadenaExportacion.trim().isEmpty()) {
						Utilidades.onGenerarDocumentoTXT(cadenaExportacion);

						JOptionPane.showMessageDialog(null, "Documento generado.", "Mensaje",
								JOptionPane.INFORMATION_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "Documento vacío.", "Mensaje", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 693, Short.MAX_VALUE)
							.addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExportarATxt)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExportarAPdf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProcesar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnNuevo))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProcesar)
						.addComponent(btnExportarAPdf)
						.addComponent(btnExportarATxt))
					.addContainerGap())
		);

		table = new JTable();
		setTabla();

		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(280);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		table.getColumnModel().getColumn(8).setWidth(0);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent ae) {
		System.out.println("Ejecutando [actionPerformed]...");

		String choice = ae.getActionCommand();
		if (choice.equals("Quit")) {
			System.exit(0);
		} else if (choice.equals("get-request")) {
			Global.Auxiliar.setCurrent_obj(new ClsRequestDTO());
			CapturarPeticion dlg = new CapturarPeticion(this, tituloCapturaPeticion);
			String results = dlg.run();
			if (results != null) {
				if (Global.Auxiliar.getModo() == "N") {
					cantidad++;
					Global.Auxiliar.getCurrent_obj().setPosicion(cantidad);
					lista.add(Global.Auxiliar.getCurrent_obj());

					Object[] objs = { Global.Auxiliar.getCurrent_obj().getPosicion(),
							Global.Auxiliar.getCurrent_obj().getMetodo(), Global.Auxiliar.getCurrent_obj().getTitulo(),
							Global.Auxiliar.getCurrent_obj().getUrl(), Global.Auxiliar.getCurrent_obj().getBody(),
							Global.Auxiliar.getCurrent_obj().getHeaders(), new JButton("Ver"), new JButton("Eliminar"),
							"No se ha procesado." };

					DefaultTableModel model = (DefaultTableModel) table.getModel();

					model.addRow(objs);
				}

				JOptionPane.showMessageDialog(this, results);
			}
		} else if (choice.equals("procesar")) {
			System.out.println("procesar");

			if (lista.size() == 0) {
				JOptionPane.showMessageDialog(table, "No hay peticiones por procesar", "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			cadenaExportacion = "";

			int fila = 0;
			for (final ClsRequestDTO objeto : lista) {
				final ClsRequestDTO objeto2 = Utilidades.onProcesar(objeto);

				table.getModel().setValueAt(objeto2.getResultado(), fila, 8);

				if (!cadenaExportacion.trim().isEmpty()) {
					cadenaExportacion = cadenaExportacion
							+ "****************************************************************************************\r\n"
							+ "****************************************************************************************\n\n";
				}

				cadenaExportacion = cadenaExportacion + objeto2.getTitulo() + ": \n" + objeto2.getResultado() + "\n \n";
				fila++;
			}

			JOptionPane.showMessageDialog(this, "Cola de peticiones procesada.");
		}
	}

	private void setTabla() {
		System.out.println("Ejecutando [setTabla]...");

		String[] columnas = new String[] { "#", "Método", "Título", "URL", "Body", "Headers", "Resultado", "Eliminar",
				"MensajeResultado" };

		final Class[] tiposColumnas = new Class[] { String.class, String.class, String.class, String.class,
				String.class, String.class, JButton.class, JButton.class, String.class };

		table.setModel(new DefaultTableModel(columnas, 0) {
			Class[] tipos = tiposColumnas;

			@Override
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.setDefaultRenderer(JButton.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
					boolean tieneElFoco, int fila, int columna) {

				return (Component) objeto;
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				int columna = table.columnAtPoint(e.getPoint());

				if (table.getModel().getColumnClass(columna).equals(JButton.class)) {
					if (columna == 6) {
						JTextArea ta = new JTextArea(20, 60);
						ta.setText(table.getModel().getValueAt(fila, 8).toString());
						ta.setWrapStyleWord(true);
						ta.setLineWrap(true);
						ta.setCaretPosition(0);
						ta.setEditable(false);

						JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Mensaje",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(fila);

						lista.remove(fila);
						System.out.println("Tamaño de lista: " + lista.size());
					}
				}
			}
		});
	}
}

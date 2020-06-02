import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Frame;
import javax.swing.JTable;

public class CapturarPeticion extends JDialog {

	private static final long serialVersionUID = -7705919781443250037L;

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtUrl;

	private String data;

	JTextArea txtBody;
	private JTable table;

	Integer cantidadHeader = 1;

	public CapturarPeticion(Frame parent, String titulo) {
		super(parent, titulo, true);
		this.setModalityType(ModalityType.TOOLKIT_MODAL);
		setBounds(100, 100, 736, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("T\u00EDtulo:");

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("M\u00E9todo:");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "GET", "POST" }));

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblUrl = new JLabel("URL:");

		txtUrl = new JTextField();
		txtUrl.setColumns(10);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setActionCommand("on-save");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtTitulo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(table, "El Título es obligatorio.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (txtUrl.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(table, "La URL es obligatoria.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				Global.Auxiliar.getCurrent_obj().setTitulo(txtTitulo.getText());
				Global.Auxiliar.getCurrent_obj().setUrl(txtUrl.getText());
				Global.Auxiliar.getCurrent_obj().setMetodo((comboBox.getSelectedIndex() == 0 ? "GET" : "POST"));
				Global.Auxiliar.getCurrent_obj().setBody(txtBody.getText());

				HashMap<String, String> headers = new HashMap<String, String>();

				String key = null;
				String value = null;
				for (int j = 0; j < table.getModel().getRowCount(); j++) {
					key = (String) table.getModel().getValueAt(j, 0);
					value = (String) table.getModel().getValueAt(j, 1);

					if ((key != null && !key.trim().isEmpty()) && (value != null && !value.trim().isEmpty())) {
						headers.put(key, value);
					} else {
						if (key != null && !key.trim().isEmpty() && (value == null || value.trim().isEmpty())) {
							JOptionPane.showMessageDialog(table, "La llave " + key + " no tiene valor.", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
							return;
						} else if (key == null || key.trim().isEmpty() && (value != null && !value.trim().isEmpty())) {
							JOptionPane.showMessageDialog(table, "Hay valores sin llaves.", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}

				if (headers.size() > 0)
					Global.Auxiliar.getCurrent_obj().setHeaders(headers);

				data = "Petición guardada.";
				dispose();

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel).addComponent(lblUrl, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtUrl, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, 442,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox, 0, 116, Short.MAX_VALUE))))
						.addComponent(btnNewButton, Alignment.TRAILING))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1).addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUrl))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnNewButton).addContainerGap(12, Short.MAX_VALUE)));

		JTabbedPane pestanas = new JTabbedPane(JTabbedPane.TOP);

		JPanel panelBody = new JPanel();
		pestanas.add("Body", panelBody);

		txtBody = new JTextArea();
		GroupLayout gl_panelBody = new GroupLayout(panelBody);
		gl_panelBody.setHorizontalGroup(gl_panelBody.createParallelGroup(Alignment.LEADING).addComponent(txtBody,
				GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE));
		gl_panelBody.setVerticalGroup(gl_panelBody.createParallelGroup(Alignment.LEADING).addComponent(txtBody,
				GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE));

		panelBody.setLayout(gl_panelBody);

		//

		JPanel panelHeaders = new JPanel();
		pestanas.add("Headers", panelHeaders);

		JScrollPane scrollPaneHeader = new JScrollPane();
		GroupLayout gl_panelHeader = new GroupLayout(panelHeaders);
		gl_panelHeader.setHorizontalGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneHeader, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE));
		gl_panelHeader.setVerticalGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
						.addComponent(scrollPaneHeader, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addContainerGap()));

		table = new JTable();
		scrollPaneHeader.setViewportView(table);

		panelHeaders.setLayout(gl_panelHeader);

		setTabla();

		scrollPane.setViewportView(pestanas);
		contentPane.setLayout(gl_contentPane);

	}

	private void setTabla() {
		System.out.println("Ejecutando [setTabla]...");

		String[] columnas = new String[] { "Key", "Value", "Description", "" };

		final Class[] tiposColumnas = new Class[] { String.class, String.class, String.class, JButton.class };

		Object[][] datos = new Object[][] { { "", "", "", new JButton("Eliminar") } };

		table.setModel(new DefaultTableModel(datos, columnas) {
			Class[] tipos = tiposColumnas;

			@Override
			public Class getColumnClass(int columnIndex) {
				return tipos[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { true, true, true, false };

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

					if (cantidadHeader > 1) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(fila);
						cantidadHeader--;
					}
				} else {
					if (table.getModel().getRowCount() == 1) {
						Object[] objs = { "", "", "", new JButton("Eliminar") };

						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(objs);
						cantidadHeader++;
					} else {
						if (fila == cantidadHeader - 1) {
							Object[] objs = { "", "", "", new JButton("Eliminar") };

							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.addRow(objs);
							cantidadHeader++;
						}
					}
				}
			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}

	public String run() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		return data;
	}
}
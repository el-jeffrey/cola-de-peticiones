import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Modelo.ClsRequestDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Point;

public class CapturarPeticion extends JDialog {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtUrl;
	
	private ClsRequestDTO obj = new ClsRequestDTO();
	private String modo = Global.Auxiliar.modo;
	
	private String data;

	private JButton btnOk;
	private JButton btnCancel;

	JTextArea txtBody;
	public CapturarPeticion(Frame parent, String titulo) {
		super(parent, titulo, true);
		this.setModalityType(ModalityType.TOOLKIT_MODAL);
		setBounds(100, 100, 700, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo:");
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E9todo:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"GET", "POST"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblUrl = new JLabel("URL:");
		
		txtUrl = new JTextField();
		txtUrl.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setActionCommand("on-save");
		
		btnNewButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            System.out.println("Hola ");
				Global.Auxiliar.getCurrent_obj().setTitulo(txtTitulo.getText());
				Global.Auxiliar.getCurrent_obj().setUrl(txtUrl.getText());
				Global.Auxiliar.getCurrent_obj().setMetodo((comboBox.getSelectedIndex()==0?"GET":"POST"));
				Global.Auxiliar.getCurrent_obj().setBody(txtBody.getText());
				
				data= "guardado";
	            dispose();
	         }
	      });
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 116, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 656, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUrl, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtUrl, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
						.addComponent(btnNewButton, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUrl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		
		JTabbedPane pestañas = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelBody = new JPanel();		
		pestañas.add("Body", panelBody);
		
		txtBody = new JTextArea();
		GroupLayout gl_panelBody = new GroupLayout(panelBody);
		gl_panelBody.setHorizontalGroup(
			gl_panelBody.createParallelGroup(Alignment.LEADING)
				.addComponent(txtBody, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
		);
		gl_panelBody.setVerticalGroup(
			gl_panelBody.createParallelGroup(Alignment.LEADING)
				.addComponent(txtBody, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
		);
		
		panelBody.setLayout(gl_panelBody);
		
		JPanel panelHeaders = new JPanel();		
		pestañas.add("Headers", panelHeaders);
		
		scrollPane.setViewportView(pestañas);
		contentPane.setLayout(gl_contentPane);
	}

	public String run() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		return data;
	}
}
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import Modelo.ClsRequestDTO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class GetPeticion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private ClsRequestDTO obj = new ClsRequestDTO();
	private String modo = Global.Auxiliar.modo;
	
	public GetPeticion() {
		obj = Global.Auxiliar.getCurrent_obj();
		
		setTitle((modo == "N" ? "Nueva" : "Editar") + "Petici\u00F3n");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00E9todo:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"GET", "POST"}));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblUrl = new JLabel("URL:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 116, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 656, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUrl, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
						.addComponent(btnNewButton, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUrl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(btnNewButton))
		);
		
		JTabbedPane pestañas = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelBody = new JPanel();		
		pestañas.add("Body", panelBody);
		
		JTextArea textArea = new JTextArea();
		GroupLayout gl_panelBody = new GroupLayout(panelBody);
		gl_panelBody.setHorizontalGroup(
			gl_panelBody.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
		);
		gl_panelBody.setVerticalGroup(
			gl_panelBody.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
		);
		
		panelBody.setLayout(gl_panelBody);
		
		JPanel panelHeaders = new JPanel();		
		pestañas.add("Headers", panelHeaders);
		
		scrollPane.setViewportView(pestañas);
		contentPane.setLayout(gl_contentPane);
	      createAndShowUI();
	  }

	  private void createAndShowUI(){
		  
		  if(modo!="N") {
			  
		  }
		  
	    /*setTitle("This JFRAME looks like JDialog");
	    setSize(new Dimension(500,100));
	    setUndecorated(true);
	    setResizable(false);
	    getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);*/
	    //addWindowListener(new WindowAdapter(){
	    //  public void windowClosing(WindowEvent e) {
	    //    System.out.println("Window Closing");
	    //    System.exit(0);
	    //  }
	    //});
	  }
	
	  public void actionPerformed(ActionEvent ae) {
			String choice = ae.getActionCommand();
			if (choice.equals("Quit")) {
				System.exit(0);
			} else if (choice.equals("on-save")) {
				Global.Auxiliar.setCurrent_obj(new ClsRequestDTO());
//				CapturarPeticion dlg = new CapturarPeticion(this, tituloCapturaPeticion);
				GetPeticion  dlg = new GetPeticion();
				String results = dlg.run();
				/*if (results[0] != null) {
					JOptionPane.showMessageDialog(this, results[0] + ", color: " + results[1]);
				}*/
			}
		}
	  
	/**
	 * Launch the application.
	 */
	public static String run() {
		GetPeticion frame = new GetPeticion();
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		return "ok";
	}
}

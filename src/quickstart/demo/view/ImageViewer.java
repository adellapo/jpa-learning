package quickstart.demo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import quickstart.demo.Service.EmployeeService;
import quickstart.demo.model.Employee;

public class ImageViewer extends JFrame implements ActionListener {

	static {
		em = getEntityManager();
		service = getEmployeeService();
	}

	// variables y constantes
	private static final String PERSISTENT_UNIT = "EmployeeService";
	private static final long serialVersionUID = 1L;
	private static EmployeeService service;
	private static EntityManager em;
	private int id;
	private Employee emp;
	String filePath;

	// barra buscadora
	JPanel panelBuscador = new JPanel(new FlowLayout());
	JLabel labelBuscador = new JLabel("Ingrese el ID del Emp");
	JTextField inputBuscador = new JTextField(3);
	JButton buttonBuscador = new JButton("Buscar");

	// panel de datos de Emp
	JPanel panelDatosEmp = new JPanel(new GridLayout(6, 2));
	JLabel labelIdEmp = new JLabel("ID");
	JTextField inputIdEmp = new JTextField(3); // que coincida con la DB
	JLabel labelNomEmp = new JLabel("Nombre");
	JTextField inputNomEmp = new JTextField(80); // que coincida con la DB
	JLabel labelSalEmp = new JLabel("Salario");
	JTextField inputSalEmp = new JTextField();
	JLabel labelTelEmp = new JLabel("Telefono");
	JTextField inputTelEmp = new JTextField(45); // que coincida con la DB
	JLabel labelComEmp = new JLabel("Comentarios");
	JTextArea inputComEmp = new JTextArea(5, 50);
	JPanel panelImagenEmp = new JPanel();
	JLabel labelImaEmp = new JLabel("Imagen");
	JLabel imaEmp = new JLabel();
	JButton buttonImaEmp = new JButton("Cambiar Imagen");

	// panel de comandos
	JPanel panelComandos = new JPanel(new FlowLayout());
	JButton buttonNuevoEmp = new JButton("Nuevo");
	JButton buttonEditarEmp = new JButton("Editar");
	JButton buttonGuardarEmp = new JButton("Guardar");

	public ImageViewer() throws Exception {

		this.setTitle("");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		;

		// panel buscador
		panelBuscador.add(labelBuscador);
		panelBuscador.add(inputBuscador);
		panelBuscador.add(buttonBuscador);
		buttonBuscador.addActionListener(this);

		// panel datos Emp
		panelDatosEmp.add(labelIdEmp);
		panelDatosEmp.add(inputIdEmp);
		panelDatosEmp.add(labelNomEmp);
		panelDatosEmp.add(inputNomEmp);
		panelDatosEmp.add(labelSalEmp);
		panelDatosEmp.add(inputSalEmp);
		panelDatosEmp.add(labelTelEmp);
		panelDatosEmp.add(inputTelEmp);
		panelDatosEmp.add(labelComEmp);
		panelDatosEmp.add(inputComEmp);
		panelDatosEmp.add(labelImaEmp);
		panelDatosEmp.add(buttonImaEmp);
		panelDatosEmp.setSize(400, 400);
		// imagen
		panelImagenEmp.add(imaEmp);
		buttonImaEmp.addActionListener(this);
		panelImagenEmp.setSize(400, 400);

		// panel de comandos
		panelComandos.add(buttonEditarEmp);
		panelComandos.add(buttonGuardarEmp);
		panelComandos.add(buttonNuevoEmp);
		buttonEditarEmp.addActionListener(this);
		buttonGuardarEmp.addActionListener(this);
		buttonNuevoEmp.addActionListener(this);

		JScrollPane scroller = new JScrollPane();
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroller);

		// coloco los paneles
		this.add(panelBuscador, BorderLayout.NORTH);
		this.add(panelDatosEmp, BorderLayout.CENTER);
		this.add(panelImagenEmp, BorderLayout.EAST);
		this.add(panelComandos, BorderLayout.SOUTH);

		this.setVisible(true);

		validate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.buttonBuscador) {
			id = Integer.parseInt(inputBuscador.getText());
			cargarEmp(id);
		}

		if (id > 0) {

			if (e.getSource() == this.buttonEditarEmp) {
				editarEmp(id);
			} else if (e.getSource() == this.buttonGuardarEmp) {
				guardarEmp(id);
			} else if (e.getSource() == this.buttonNuevoEmp) {
				id = nuevoEmp();
			} else if (e.getSource() == this.buttonImaEmp) {
				System.out.println("Entre");
				cambiarImaEmp(id);
			} else {
				System.out.println("Otro boton: " + e.getSource());
			}

		}

	}

	private void cambiarImaEmp(int id) {

		JFileChooser fc = new JFileChooser();

		fc.showOpenDialog(null);

		try {

			File selectedFile = fc.getSelectedFile();
			System.out.println(selectedFile);
			filePath = selectedFile.getAbsolutePath();

			imaEmp.setIcon(new ImageIcon(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int nuevoEmp() {
		return 0;
	}

	private void guardarEmp(int id) {
		
		em.getTransaction().begin();

		if (emp != null && !filePath.isEmpty()) {
			
			try {
				System.out.println("PATH: " + filePath );
				File file = new File(filePath);
				byte[] picInBytes = new byte[(int) file.length()];
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(picInBytes);
				fileInputStream.close();
				emp.setPicture(picInBytes);
				System.out.println("Guardado");
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		em.getTransaction().commit();

		
	}

	private void editarEmp(int id) {
	}

	public Employee getEmployee(int id) {
		return service.findEmployee(id);
	}

	public void cargarEmp(int id) {

		try {

			System.out.println("ID: " + id);

			if (id != 0) {

				emp = service.findEmployee(id);

				if (emp != null) {
					System.out.println("Found " + emp);
					inputIdEmp.setText(String.valueOf(emp.getId()));
					inputNomEmp.setText(emp.getName());
					inputSalEmp.setText(String.valueOf(emp.getSalary()));
					inputTelEmp.setText(emp.getPhoneNumber());
					inputComEmp.setText(emp.getComments());
					imaEmp.setIcon(new ImageIcon(emp.getPicture()));
				}

			}

		} catch (Exception e) {

			this.dispose();

		}

	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
	}

	private static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	private static EmployeeService getEmployeeService() {
		return new EmployeeService(getEntityManager());
	}

}

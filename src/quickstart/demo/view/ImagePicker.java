package quickstart.demo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import quickstart.demo.Service.EmployeeService;
import quickstart.demo.model.Employee;

public class ImagePicker extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JPanel jp = new JPanel(new BorderLayout());
	JPanel panelBuscador = new JPanel(new FlowLayout());

	JLabel labelEmp = new JLabel("Ingrese el ID del emp");
	JTextField inputEmp = new JTextField(5);
	JLabel image = new JLabel();
	JButton buttonBuscar = new JButton("Buscar");
	JButton buttonConfirmar = new JButton("Confirmar");
	String filePath;

	public ImagePicker() {

		this.setTitle("");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		buttonBuscar.addActionListener(this);
		buttonConfirmar.addActionListener(this);

		panelBuscador.add(labelEmp);
		panelBuscador.add(inputEmp);
		panelBuscador.add(buttonBuscar);

		jp.setSize(300, 300);
		jp.add(panelBuscador, BorderLayout.NORTH);
		jp.add(image, BorderLayout.CENTER);
		jp.add(buttonConfirmar, BorderLayout.SOUTH);

		this.add(jp);

		validate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonBuscar) {

			JFileChooser fc = new JFileChooser();
			File selectedFile = fc.getSelectedFile();
			filePath = selectedFile.getAbsolutePath();
			image.setIcon(new ImageIcon(filePath));
			
			System.out.println(filePath);

		} else if (e.getSource() == buttonConfirmar) {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");

			EntityManager em = emf.createEntityManager();

			EmployeeService service = new EmployeeService(em);

			int id = Integer.parseInt(inputEmp.getText());

			Employee emp = service.findEmployee(id);

			System.out.println("Found " + emp);

			em.getTransaction().begin();

			if (emp != null) {
				try {
					File file = new File(filePath);
					byte[] picInBytes = new byte[(int) file.length()];
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(picInBytes);
					fileInputStream.close();
					emp.setPicture(picInBytes);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			em.getTransaction().commit();
		}

	}

}

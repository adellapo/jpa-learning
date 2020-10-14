package quickstart.demo.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import quickstart.demo.Service.EmployeeService;
import quickstart.demo.view.ImagePicker;
import quickstart.demo.view.ImageViewer;

public class Test {

	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");

		EntityManager em = emf.createEntityManager();

		EmployeeService service = new EmployeeService(em);

		Employee emp;

		// find a specific employee
		emp = service.findEmployee(3);
		System.out.println("Found " + emp);

//		ImageViewer iv = new ImageViewer();
//		iv.setVisible(true);
//		ImagePicker ip = new ImagePicker();
//		ip.setVisible(true);
		
		// create and persist an employee
//		em.getTransaction().begin();
//		emp = service.createEmployee(4, "Jesus", 55000, "1155326711");
//		emp.setComments("Some Comments");
//		em.getTransaction().commit();
//		System.out.println("Persisted " + emp);

		// find all employees
//		List<Employee> emps = service.findAllEmployees();
//		for (Employee e : emps) {
//			System.out.println("Found employee " + e);
//		}
//
//		// update an employee
		em.getTransaction().begin();
		Date dob = new Date((new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-11").getTime()));
		emp.setDob(dob);
//		emp.setType(EmployeeType.CONTRACT_EMPLOYEE);
//		emp = service.raiseEmployeeSalary(3, 65000);
		em.getTransaction().commit();
		System.out.println("Updated " + emp);
//
//		// remove an employee
//		em.getTransaction().begin();
//		service.deleteEmployee(2);
//		em.getTransaction().commit();
//		System.out.println("Employee deleted");

		// close EntityManagerFactory and EntityManager
		em.close();
		emf.close();

	}

}

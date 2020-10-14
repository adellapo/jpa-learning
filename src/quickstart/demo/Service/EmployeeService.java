package quickstart.demo.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import quickstart.demo.model.Employee;

public class EmployeeService {

	protected EntityManager em;

	public EmployeeService(EntityManager em) {
		this.em = em;
	}

	public Employee createEmployee(int id, String name, long salary, String phone) {
		Employee emp = new Employee();
		emp.setId(id);
		emp.setName(name);
		emp.setSalary(salary);
		emp.setPhoneNumber(phone);
		em.persist(emp);
		return emp;
	}

	public Employee findEmployee(int id) {
		return em.find(Employee.class, id);
	}

	public void deleteEmployee(int id) {
		// para borrar una entidad primero debe estar cargada en el contexto
		// de persistencia, osea que la aplicacion debe haber cargado
		// la entidad previamente
		Employee emp = em.find(Employee.class, id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Employee raiseEmployeeSalary(int id, long raise) {
		Employee emp = em.find(Employee.class, id);
		if (emp != null) {
			emp.setSalary(emp.getSalary() + raise);
		}
		return emp;
	}

	public List<Employee> findAllEmployees() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
		return query.getResultList();
	}

	public byte[] loadPicture(int id) {

		byte[] tmp = null;

		try {

			tmp = em.find(Employee.class, id).getPicture();

			System.out.println("Picture found");

		} catch (Exception e) {

			System.out.println("Picture not found");
		}

		return tmp;

	}

	public void savePicture(int id, byte[] image) {

		try {

			em.find(Employee.class, id).setPicture(image);

			System.out.println("Picture saved");

		} catch (Exception e) {

			System.out.println("Picture not saved");
		}

	}

}

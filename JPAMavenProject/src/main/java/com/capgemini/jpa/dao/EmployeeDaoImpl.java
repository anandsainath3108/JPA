package com.capgemini.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.capgemini.jpa.entity.Employee;
import com.capgemini.jpa.exception.EmployeeException;
import com.capgemini.jpa.utility.JPAUtil;

public class EmployeeDaoImpl implements IEmployeeDAO {
	EntityManager entityManager = null;

	@Override

	public void addNewEmployee(Employee employee) throws EmployeeException {

		try {
			/* entityManager=JPAUtil.getEntityManager(); */
			entityManager = JPAUtil.getEntityManager();
			// entityManager
			// =Persistence.createEntityManagerFactory("JPACRUDProject").createEntityManager();
			entityManager.getTransaction().begin();
			employee.setEmpid(null);
			entityManager.persist(employee);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			// TODO: Log to file
			throw new EmployeeException(e.getMessage());
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void deleteEmployee(Integer empid) throws EmployeeException {

		// TODO Auto-generated method stub
		try {
			/* entityManager=JPAUtil.getEntityManager(); */
			// entityManager
			// =Persistence.createEntityManagerFactory("JPACRUDProject").createEntityManager();
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Employee employee = entityManager.find(Employee.class, empid);
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			// TODO: Log to file
			throw new EmployeeException(e.getMessage());
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void updateEmployee(Employee employee) throws EmployeeException {
		// TODO Auto-generated method stub
		try {
			/* entityManager=JPAUtil.getEntityManager(); */
			// entityManager
			// =Persistence.createEntityManagerFactory("JPACRUDProject").createEntityManager();
			entityManager = JPAUtil.getEntityManager();
			entityManager.getTransaction().begin();

			entityManager.merge(employee);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			// TODO: Log to file
			throw new EmployeeException(e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Employee getEmployeeDetails(Integer empid) throws EmployeeException {
		// TODO Auto-generated method stub
		// entityManager
		// =Persistence.createEntityManagerFactory("JPACRUDProject").createEntityManager();
		try {
			Employee employee = entityManager.find(Employee.class, empid);
			return employee;

		} catch (PersistenceException e) {
			e.printStackTrace();
			// TODO: Log to file
			throw new EmployeeException(e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		// TODO Auto-generated method stub
		try {
			Query query = entityManager.createQuery("from employee");
			List<Employee> employeeList = query.getResultList();
			return employeeList;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		} finally {
			entityManager.close();

		}
	}

}

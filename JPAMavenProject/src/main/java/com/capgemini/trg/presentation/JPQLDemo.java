package com.capgemini.trg.presentation;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.jpa.entity.Employee;
import com.capgemini.jpa.utility.JPAUtil;

public class JPQLDemo {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("    Employee Details    ");
		System.out.println("_______________________________\n");
		System.out.println("1.Get all Employee details ");
		System.out.println("2.Get Employee details from a specific department");
	    System.out.println("3.Exit");
	
	    try {
			int option = scanner.nextInt();
			switch (option) {
			case 1:
			EntityManager entityManager = JPAUtil.getEntityManager();
		    String jql1 = "	select e from Employee e";
		    TypedQuery<Employee> typedQuery1 = entityManager.createQuery(jql1, Employee.class);
		    List<Employee> employeeList = typedQuery1.getResultList();
		    Query query = entityManager.createNamedQuery("q2"); 
			Query query1 = null;
			List<Employee> employeeList1 = query1 .getResultList();
			showEmployees(employeeList);
			break;
			case 2:
			EntityManager entityManager1 = JPAUtil.getEntityManager();
		    String jql2 = "	select e from Employee e where e.job=:pjob AND e.salary>:psalary";
		    TypedQuery<Employee> typedQuery2 = entityManager1.createQuery(jql2, Employee.class);
		    typedQuery2.setParameter("pjob", "DJ" );
		    typedQuery2.setParameter("psalary", 10000.0);
		    List<Employee> employeeList2 = typedQuery2.getResultList();
		    showEmployees(employeeList2);
			break;
			case 3:
				System.out.print("Exit Employee System");
				System.exit(0);
			default:
				System.out.println("Enter a valid option[1-3]");
			}// end of switch
		} catch (InputMismatchException e) {
			scanner.nextLine();
			System.err.println("Please enter a numeric value, try again");
		}
	} // end of while    
	

	private static void showEmployees(List<Employee> employeeList) {
		// TODO Auto-generated method stub
		Iterator<Employee> iterator = employeeList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

}

package com.capgemini.trg.presentation;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.jpa.entity.Employee;
import com.capgemini.jpa.exception.EmployeeException;
import com.capgemini.jpa.service.EmployeeServiceImpl;
import com.capgemini.jpa.service.IEmployeeService;

public class EmployeeTester {
	private static IEmployeeService employeeService = new EmployeeServiceImpl();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws EmployeeException {

		while (true) {
			// show menu
			System.out.println();
			System.out.println();
			System.out.println("    Employee Details    ");
			System.out.println("_______________________________\n");
			System.out.println("1.Add Employee ");
			System.out.println("2.Delete an Employee");
			System.out.println("3.Update Employee Details");
			System.out.println("4.Get Employee Details");
			System.out.println("5.Get All Employee Details");
			System.out.println("6.Exit");
			System.out.println("________________________________");
			System.out.println("Select an option:");
			// accept option
			try {
				int option = scanner.nextInt();
				switch (option) {
				case 1:
					Employee employee = new Employee();
					getEmployeeDetails(employee);
					addNewEmployee(employee);
					// TODO
					break;
				case 2:
					System.out.println("Enter Employee ID of the Employee to be deleted");
					Integer empid = scanner.nextInt();
                    employeeService.deleteEmployee(empid);
					break;
				case 3:

					break;
				case 4:
					System.out.println("Enter Employee ID to get the Employee details");
				    Integer empid1 = scanner.nextInt();
                    employeeService.getEmployeeDetails(empid1);

					break;
				case 5:

					break;
				case 6:
					System.out.print("Exit Employee System");
					System.exit(0);
				default:
					System.out.println("Enter a valid option[1-7]");
				}// end of switch
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("Please enter a numeric value, try again");
			}
		} // end of while
	}

	private static void getEmployeeDetails(Employee employee) {
		 System.out.println("Enter Employee Department Number");
         employee.setDeptno(scanner.nextInt());
		 System.out.println("Enter Employee Name");
         scanner.nextLine();// to clear string buffer
         employee.setEname(scanner.nextLine());
         System.out.println("Enter Employee Job");
		 employee.setJob(scanner.next());
         System.out.println("Enter Employee Salary");
	     employee.setSalary(scanner.nextDouble());
	     Calendar hiredate = GregorianCalendar.getInstance();
		employee.setHiredate(hiredate);
	}
	private static void addNewEmployee(Employee employee) {
		try {
			employeeService.addNewEmployee(employee);
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
	}
}

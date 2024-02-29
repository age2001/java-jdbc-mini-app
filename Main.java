import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Employee Dao
        EmployeeDAO employeeDao = EmployeeDaoFactory.getEmployeeDao();
        Scanner scan = new Scanner(System.in);
        int id;
        String name, email;

        boolean flag = true;
        while (flag) {
            id = -1;
            name = "";
            email = "";

            System.out.println("***************************");
            System.out.println("Select from options below");
            System.out.println("***************************");
            System.out.println("PRESS 1: Add Employee");
            System.out.println("PRESS 2: Update Employee");
            System.out.println("PRESS 3: Delete Employee");
            System.out.println("PRESS 4: Get All Employees");
            System.out.println("PRESS 5: Get Employee By Id");
            System.out.println("PRESS 6: Exit");

            int input = Integer.parseInt(scan.next());

            switch(input) {
                case 1:
                    System.out.println("Adding New User");
                    System.out.println("Enter Name: ");
                    name = scan.next();
                    System.out.println("Enter Email: ");
                    email = scan.next();
                    Employee employee = new Employee();
                    employee.setName(name);
                    employee.setEmail(email);
                    employeeDao.addEmployee(employee);
                    break;
                case 2:
                    System.out.println("Updating User");
                    System.out.println("Enter Id: ");
                    id = Integer.parseInt(scan.next());
                    System.out.println("Enter Name: ");
                    name = scan.next();
                    System.out.println("Enter Email: ");
                    email = scan.next();
                    employeeDao.updateEmployee(new Employee(id, name, email));
                    break;
                case 3:
                    System.out.println("Deleting User");
                    System.out.println("Enter Id: ");
                    id = Integer.parseInt(scan.next());
                    employeeDao.deleteEmployee(id);
                    break;
                case 4:
                    System.out.println("Showing All Users");
                    List<Employee> employees = employeeDao.getEmployees();
                    for(Employee emp: employees) {
                        System.out.println("Id: " + emp.getId() + ", Name: " + emp.getName() + ", Email: " + emp.getEmail());
                    }
                    break;
                case 5:
                    System.out.println("Find One User");
                    System.out.println("Enter Id: ");
                    id = Integer.parseInt(scan.next());
                    Employee singleEmp = employeeDao.getEmployeeById(id);
                    System.out.println("Id: " + singleEmp.getId() + ", Name: " + singleEmp.getName() + ", Email: " + singleEmp.getEmail());
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid input, input must be between 1 - 6");
                    break;
            }
        }
    }
}
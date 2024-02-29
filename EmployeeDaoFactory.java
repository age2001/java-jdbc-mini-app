public class EmployeeDaoFactory {
    private static EmployeeDAO employeeDao;

    private EmployeeDaoFactory() {

    }

    public static EmployeeDAO getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }
}

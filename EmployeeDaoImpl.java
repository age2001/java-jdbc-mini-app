import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO{
    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employee (name, email) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Successfully added employee");
        } else {
            System.out.println("An error occurred when adding employee");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE employee SET name = ?, email = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getId());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Employee was updated successfully");
        } else {
            System.out.println("There was an error in updating the employee");
        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employee WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Successfully deleted employee");
        } else {
            System.out.println("An error occurred when deleting employee");
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        while (resultset.next()) {
            employees.add(new Employee(resultset.getInt(1), resultset.getString(2), resultset.getString(3)));
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT name, email FROM employee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        return (new Employee(id, resultSet.getString(1), resultSet.getString(2)));
    }
}

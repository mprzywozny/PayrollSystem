import java.io.FileNotFoundException;

public class EmployeeTest {

    public static void main(String[] args) {
        try {
            System.out.println("Testing Employee Class");
            Employee employee1 = new Employee("Alan Baker", "Academic", "Professor", 4);
            System.out.println("Name: " + employee1.getName());
            System.out.println("Job Category: " + employee1.getJobCategory());
            System.out.println("Job Title: " + employee1.getJobTitle());
            System.out.println("Scale Point: " + employee1.getScalePoint());

            employee1.setJobTitle("Senior Lecturer");
            employee1.setScalePoint(5);

            System.out.println("Updated Job Title: " + employee1.getJobTitle());
            System.out.println("Updated Scale Point: " + employee1.getScalePoint());
            employee1.setSalary();
            System.out.println("Salary: " + employee1.getSalary());
        } catch (FileNotFoundException e) {
            System.err.println("Error: File for employee salary data not found.");
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}


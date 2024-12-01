import java.io.FileNotFoundException;

public class FullTimeEmployeeTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing FullTimeEmployee Class\n");

            // Creating a FullTimeEmployee object
            FullTimeEmployee fte = new FullTimeEmployee("Mina Alison", "Academic", "Lecturer", 3);
            System.out.println("Name: " + fte.getName());
            System.out.println("Job Category: " + fte.getJobCategory());
            System.out.println("Job Title: " + fte.getJobTitle());
            System.out.println("Scale Point: " + fte.getScalePoint());
            System.out.println("Salary: " + fte.getSalary());

            // Moving up salary scale
            System.out.println("\nAttempting to move up salary scale... ");
            fte.moveupsalarypoint();

            // Trying to move beyond the top scale
            fte.setScalePoint(fte.getTopPayScale()); // Simulate reaching the top scale point
            System.out.println("\nSetting employee to top scale point... ");
            System.out.println("Scale Point: " + fte.getScalePoint());
            System.out.println("Attempting to move up salary scale... ");
            fte.moveupsalarypoint();

            // Moving up in job category
            System.out.println("\nAttempting to move up in job category... ");
            fte.moveUpInCategory();

            // Simulating the case where the employee is already at the top of their category
            fte.setJobTitle("Professor"); // Let's assume  that "Professor" is the highest title
            fte.setScalePoint(fte.getTopPayScale());
            System.out.println("\nSetting employee to the top of their category...");
            System.out.println("Job Title: " + fte.getJobTitle());
            System.out.println("Scale Point: " + fte.getScalePoint());
            System.out.println("Attempting to move up in job category...");
            fte.moveUpInCategory();

        } catch (FileNotFoundException e) {
            System.err.println("Error: Required file not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}


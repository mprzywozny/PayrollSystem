import java.io.FileNotFoundException;

public class PartTimeEmployeeTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing PartTimeEmployee Class...");

            // Creating a PartTimeEmployee object
            PartTimeEmployee pte = new PartTimeEmployee("Owen Martin", "IT", "Technician", 4, 20.0);
            System.out.println("Name: " + pte.getName());
            System.out.println("Job Category: " + pte.getJobCategory());
            System.out.println("Job Title: " + pte.getJobTitle());
            System.out.println("Scale Point: " + pte.getScalePoint());
            System.out.println("Hourly Rate: " + pte.getHourlyRate());

            // Setting and getting hours worked
            pte.setHoursWorked(40);
            System.out.println("Hours Worked: " + pte.getHoursWorked());

            // Calculating gross pay
            System.out.println("Gross Pay: " + pte.grossPay());

            // Submitting a pay claim
            pte.payClaim();
            System.out.println("Pay claim submitted and hours reset: " + pte.getHoursWorked());
        } catch (FileNotFoundException e) {
            System.err.println("Error: File for part-time employee salary data not found.");
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}

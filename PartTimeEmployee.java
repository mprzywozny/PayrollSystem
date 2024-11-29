import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Part-time employees must submit a pay claim form
 * by the second Friday of a month in order to be paid that month.
 * The payroll system should generate pay slips for all full-time staff
 * and hourly paid staff (with current claims) on the 25th day of each month.
 */
public class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private double hoursWorked;

    /**
     * Constructor taking parameters from parent class (Employee) and adding hourly rate.
     *
     * @param name The name of the employee.
     * @param jobCategory The job category of the employee.
     * @param jobTitle The job title of the employee.
     * @param scalePoint The current scale point of the employee.
     * @param hourlyRate The hourly rate of the employee.
     * @throws FileNotFoundException If the file with pay scale is not found/invalid
     */
    public PartTimeEmployee(String name, String jobCategory, String jobTitle, int scalePoint, double hourlyRate) throws FileNotFoundException {
        super(name, jobCategory, jobTitle, scalePoint);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0; // Hours reset each month, hence 0, new hours for current period are to be added using setter method below
    }

    /**
     * Getter method for hourly rate.
     *
     * @return The hourly rate of the employee.
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Getter method for hours worked.
     *
     * @return The total hours worked by employee in given month.
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Setter method to set the hours worked in a given month.
     *
     * @param hours Number of hours worked this month.
     * @return The updated total hours worked.
     */
    public double setHoursWorked(double hours) {
        this.hoursWorked += hoursWorked + hours;
        return hoursWorked;
    }

    /**
     * Method to set the employee's salary manually based on the gross pay.
     */
    public void setSalary() {
        this.manualsetSalary(grossPay());
    }

    /**
     * Calculate the gross pay based on hours worked and hourly rate.
     *
     * @return The gross pay.
     */
    public double grossPay() {
        double grossPay = hoursWorked * hourlyRate;
        return grossPay;
    }

    /**
     * Claim form method. This resets the hours worked and displays the gross pay.
     */
    public void payClaim() {
        this.hoursWorked = 0;
        System.out.printf("Pay claim submitted successfully. Gross pay: %.2f", grossPay());
    }

    /**
     * Method to move the staff up to the next category, same as in the FullTimeEmployee class and Employee.
     *
     * @throws FileNotFoundException If the file with pay scale data is not found/invalid
     */
    @Override
    public void moveUpInCategory() throws FileNotFoundException {
        String current = this.getJobTitle();
        line = new Scanner(new File("../group/src/payscale for partime.csv"));
        boolean done = false;
        String change = current;
        line.nextLine();
        while (!done && line.hasNext()) {
            String input = line.nextLine();
            if (input.equals(this.getJobCategory())) {
                // In the right category
                while (line.hasNext() && !done) {
                    input = line.nextLine();
                    String[] parts = input.split(",");
                    if (current.equals(parts[0])) {
                        done = true;
                    } else {
                        change = parts[0];
                    }
                }
            }
        }
        if (change != current) {
            this.setJobTitle(change);
            this.setScalePoint(1);
            this.setSalary();
        } else {
            System.out.print("At the top\n");
        }
    }

    /**
     * Method to get the top pay scale for a given category.
     *
     * @return top pay scale for given category or -1 if not found.
     * @throws FileNotFoundException If the file with pay scale data is not found/invalid
     */
    public int getTopPayScale() throws FileNotFoundException {
        line = new Scanner(new File("../group/src/payscale for partime.csv"));

        boolean done = false;
        // int topPayscale;
        line.nextLine();
        while (line.hasNext()) {
            String input = line.nextLine();

            if (input.equals(this.getJobCategory())) {

                while (line.hasNext() && !done) {
                    input = line.nextLine();
                    String[] parts = input.split(",");

                    if (this.getJobTitle().equals(parts[0])) {
                        input = line.nextLine();
                        return Integer.parseInt(parts[1]);
                    }

                }
            }
        }

        return -1;
    }

    /**
     * Method to update the employee's salary if moved up in category.
     *
     * @throws FileNotFoundException If the file with pay scale data is not found/invalid
     */
    public void moveUpSalaryPoint() throws FileNotFoundException {
        // Check if they can move up
        if (this.gettopPayscale() > this.getScalePoint()) {
            this.setScalePoint(this.getScalePoint() + 1);
            this.setSalary();
        } else {
            System.out.println("Top of payscale\n");
        }
    }

    /**
     * To string method
     *
     * @return A string containing name, job category, job title, scale point, hourly rate, and hours worked
     */
    @Override
    public String toString() {
        return this.getName() + "," + this.getJobCategory() + "," + this.getJobTitle() + "," + this.getScalepPoint() + "," + this.hourlyRate + "," + this.hoursWorked;
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** 
 * In October each year,
 * full-time staff are moved to the next point on their salary scale
 * (if they are not already at the top of that scale).
 * From time to time an employee may be promoted to the next salary scale
 * within their professional category (Academic/Administrative/IT/Technical etc..).
 * The point to which a staff member is promoted to the new salary scale
 * is dependent on the number of years spent at the top of the previous scale.
 */
public class FullTimeEmployee extends Employee {

    /**
     * Constructor using the same parameters as the parent class (employee).
     * 
     * @param name The name of the employee.
     * @param jobCategory The job category of the employee.
     * @param jobTitle The job title of the employee ,e.g professor.
     * @param scalePoint The current scale point of the employee.
     * @throws FileNotFoundException If the file is not found/ invalid.
     */
    public FullTimeEmployee(String name, String jobCategory, String jobTitle, int scalePoint) throws FileNotFoundException {
        super(jobCategory, jobTitle, scalePoint);
    }

    /**
     * Method that moves staff to the next point on the salary scale if they are not already at the top.
     * 
     * @throws FileNotFoundException If the file with pay scale is not found/ invalid.
     */
    @Override
    public void moveupsalarypoint() throws FileNotFoundException {
        int topScale = getTopPayscale();
        if (this.getScalepoint() < topScale) {
            this.setscalepoint(this.getScalepoint() + 1);
            this.setsalary();
            System.out.printf("New scale point: %d\nNew salary: %.2f\n", this.getScalepoint(), this.getsalary());
        } else {
            System.out.println("Reached top of payscale.");
        }
    }

    /** 
     * If the employee is already at the top of the pay scale, move them to the next job category 
     * and reset the scale point to 1.
     * 
     * @throws FileNotFoundException If the file with pay scale is not found/ invalid.
     */
    public void moveupincategory() throws FileNotFoundException {

        /* Read the pay scale file using scanner */
        Scanner line = new Scanner(new File("../ulpay/src/payscale.csv"));

        String currentTitle = this.getJobtitle(); // Current job title
        String change = currentTitle; // Next job title (if exists)

        boolean categoryFound = false; // Is the category found?
        boolean done = false; // Has the employee been promoted?

        if (line.hasNextLine()) {
            line.nextLine();
        }

        while (!done && line.hasNext()) {
            String input = line.nextLine();

            /* Check if the current line in the file matches the current job category */
            if (input.equals(this.getJobcatagory())) {
                categoryFound = true;
                continue;
            }

            /* If the category is found, check if there are any more titles within the category */
            if (categoryFound) {
                if (input.isBlank()) {
                    break; // No more titles in the category
                }

                String[] parts = input.split(",");

                /* Check if current title matches and change it accordingly */
                if (currentTitle.equals(parts[0])) {
                    done = true;
                } else {
                    change = parts[0];
                }
            }

        }

        /* If promoted, change job title, update salary and reset scale point back to 1, using methods from the parent class */
        if (change != currentTitle) {
            this.setjobtitle(change);
            this.setscalepoint(1);
            this.setsalary();
            System.out.printf("New position: %s\nNew scale point: %d\nNew salary: %f\n", getJobtitle(), getScalepoint(), getsalary());
        } else {
            System.out.print("At the top\n");
        }
    }
}

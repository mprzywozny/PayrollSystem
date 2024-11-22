import java.io.File;
import java.io.FileNotFoundException;

public class PartTimeEmployee extends employee {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String jobCategory, String jobTitle, int scalePoint, double hourlyRate) throws FileNotFoundException {
        super(jobCategory, jobTitle, scalePoint);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0; //hours reset each month hence 0, new hours for current period are to be added using setter method below
    }
    /**Part-time employees must submit a pay claim form
     * by the second Friday of a month in order to be paid that month.
     * The payroll system should generate pay slips for all full-time staff
     * and hourly paid staff (with current claims) on the 25th day of each month.
     */

    //Getter method for hourly rate
    public double getHourlyRate(){
        return hourlyRate;
    }

    //Getter method for hours worked
    public double getHoursWorked(){
        return hoursWorked;
    }

    //Setter method to get hours worked in a given month
    public double setHoursWorked(double hours){
        this.hoursWorked += hoursWorked + hours;
        return hoursWorked;
    }


   //Calculate gross pay given hours worked and hourly rate
    public double grossPay() {
        double grossPay = hoursWorked + hourlyRate;
        return grossPay;
    }

    //Claim form method
    public void payClaim() {
        this.hoursWorked = 0;
        System.out.printf("Pay claim submitted successfully. Gross pay: %.2f", grossPay());
    }
}

import java.time.LocalDate;

public class Payslip {
    private String employeeName;
    private String jobTitle;
    private String jobCategory;
    private int scalePoint;
    private double salary;
    private double deductions;
    private LocalDate payDate;
    private String payPeriod;
    private String additionalDetails;
    private double netSalary;

    // Constructor
    public Payslip(String employeeName, String jobTitle, String jobCategory, int scalePoint, double salary, double deductions, LocalDate payDate, String payPeriod, String additionalDetails) {
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.jobCategory = jobCategory;
        this.scalePoint = scalePoint;
        this.salary = salary;
        this.deductions = deductions;
        this.payDate = payDate;
        this.payPeriod = payPeriod;
        this.additionalDetails = additionalDetails;
        this.calculateNetSalary();
    }

    // Method to calculate the net salary
    private void calculateNetSalary() {
        this.netSalary = this.salary - this.deductions;
        if (this.netSalary < 0) {
            this.netSalary = 0; // Ensure net salary is not negative
        }
    }

    // Getters
    public String getEmployeeName() {
        return employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public int getScalePoint() {
        return scalePoint;
    }

    public double getSalary() {
        return salary;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    // String representation of the payslip
    @Override
    public String toString() {
        return "Payslip for " + employeeName + "\n" +
               "Job Title: " + jobTitle + "\n" +
               "Job Category: " + jobCategory + "\n" +
               "Scale Point: " + scalePoint + "\n" +
               "Salary: " + String.format("%.2f", salary) + "\n" +
               "Deductions: " + String.format("%.2f", deductions) + "\n" +
               "Net Salary: " + String.format("%.2f", netSalary) + "\n" +
               "Pay Date: " + payDate + "\n" +
               "Pay Period: " + payPeriod + "\n" +
               "Additional Details: " + additionalDetails + "\n";
    }
}

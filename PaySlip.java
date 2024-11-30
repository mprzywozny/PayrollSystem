package ulpayproject;
import java.time.LocalDate;

/**
 *The Payslip class represents an employee's payslip,
 *including their personal details, salary information and deductions.
 */
public class PaySlip {
    //Attributes of the Payslip
    private String employeeName; //Employee's full name
    private String jobTitle; // Employee's Job title
    private String scalePoint; // Employee's salary scale point
    private double netSalary; // Net salary after deductions
    private double deductions; // Total deductions applied to the gross salary
    private double grossSalary; // Total gross salary before deductions


    /**
     * Constructor to initialize the payslip object
     *
     * @param employeeName the name of the employee
     * @param jobTitle     the job title of the employee
     * @param scalePoint   the salary scale point for the employee
     * @param netSalary    the calculated net salary after deductions
     * @param deductions   the total deductions from gross salary
     * @param grossSalary  the total gross salary before deductions
     */
    public PaySlip(String employeeName, String jobTitle, String scalePoint, double netSalary, double deductions, double grossSalary) {
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.scalePoint = scalePoint;
        this.netSalary = netSalary;
        this.deductions = deductions;
        this.grossSalary = grossSalary;
    }

   /* private void calculateNetSalary() {
        this.netSalary = this.salary - this.deductions;
        if (this.netSalary < 0) {
            this.netSalary = 0; // Ensure net salary is not negative
        }
    }*/


    /**
     * Retrives the name of the employee
     *
     * @return the name of the employee
     */
    public String getEmployeeName() {
        return employeeName;
    }


    /**
     * Retrives the job title of the employee
     *
     * @return the job title of the employee
     */
    public String getJobTitle() {
        return jobTitle;
    }


    /**
     * Retrives the salary scale point of the employee
     *
     * @return the salary scale point of the employee
     */
    public String getScalePoint() {
        return scalePoint;
    }


    /**
     * Retrives the total deductions applied to the employee's salary
     *
     * @return the total deductions
     */
    public double getDeductions() {
        return deductions;
    }


    /**
     * Retrives the net salary of the employee after deductions
     *
     * @return the net salary
     */
    public double getNetSalary() {
        return netSalary;
    }

    /**
     * Retrives the gross salary of the employee before deductions.
     *
     * @return the gross salary
     */
    public double getGrossSalary() {
        return grossSalary;
    }

    /**
     * Returns a formatted string representation of the payslip object
     * Includes employee details and salary information
     *
     * @return a string representation of the payslip
     */
    @Override
    public String toString() {
        return "Payslip for " + employeeName + "|" +
                "Job Title: " + jobTitle + "|" +
                "Scale Point: " + scalePoint + "|" +
                "Net Salary: " + String.format("%.2f", netSalary) + "|" +
                "Deductions: " + String.format("%.2f", deductions) + "|" +
                "Gross Salary: " + String.format("%.2f", grossSalary);

    }
}

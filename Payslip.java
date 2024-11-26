import java.time.LocalDate;

public class Payslip {
    private String employeeName;
    private String jobTitle;
    private String scalePoint;
    private double netSalary;
    private double deductions;
    
    private double grossSalary;

    public Payslip(String employeeName, String jobTitle, String scalePoint, double salary, double deductions,double grossSalary) {
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.scalePoint = scalePoint;
        this.netSalary = salary;
        this.deductions = deductions;
        this.grossSalary=grossSalary;
          }

   /* private void calculateNetSalary() {
        this.netSalary = this.salary - this.deductions;
        if (this.netSalary < 0) {
            this.netSalary = 0; // Ensure net salary is not negative
        }
    }*/

    public String getEmployeeName() {
        return employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }


    public String getScalePoint() {
        return scalePoint;
    }



    public double getDeductions() {
        return deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }


   
    /** Print the Payslip*/
    @Override
    public String toString() { return "Payslip for " + employeeName  +"|"+
            "Job Title: " + jobTitle+"|"+
            "Scale Point: " + scalePoint+"|"+
            "Net Salary: " + String.format("%.2f", netSalary) +"|"+
            "Deductions: " + String.format("%.2f", deductions) +"|"+
            "Gross Salary: " + String.format("%.2f", grossSalary);
            
          
          

    }
}

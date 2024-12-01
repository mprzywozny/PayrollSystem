public class PaySlipTest {
    public static void main(String[] args) {
        System.out.println("Testing Payslip Class");

        // Creating a Payslip object
        PaySlip payslip = new PaySlip("Anna Taylor", "Manager", "3", 45000.0, 10000.0, 55000.0);

        // Printing payslip details
        System.out.println("Employee Name: " + payslip.getEmployeeName());
        System.out.println("Job Title: " + payslip.getJobTitle());
        System.out.println("Scale Point: " + payslip.getScalePoint());
        System.out.println("Net Salary: " + payslip.getNetSalary());
        System.out.println("Deductions: " + payslip.getDeductions());
        System.out.println("Gross Salary: " + payslip.getGrossSalary());

        // Printing formatted payslip
        System.out.println("Payslip Details: " + payslip.toString());
    }
}

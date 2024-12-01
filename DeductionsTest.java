public class DeductionsTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Deductions Class");

            // Creating a Deductions object
            Deductions deductions = new Deductions("Lily Johnson", 60000.0);

            // Printing PRSI, USC, and income tax
            System.out.println("PRSI: " + deductions.getPRSI());
            System.out.println("USC: " + deductions.getUSC());
            System.out.println("Income Tax: " + deductions.getIncomeTax());

            // Printing total deductions and net salary
            System.out.println("Total Deductions: " + deductions.deductionTotal());
            System.out.println("Net Salary: " + deductions.netSalary());
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}

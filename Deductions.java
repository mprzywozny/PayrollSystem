import java.io.FileNotFoundException;

/**Deductions class that calculates all necessary deductions that need to be taken away from gross salary (PRSI, USC, Income Tax) based on employee’s earnings 
Then, it combines all those deductions and calculates net salary by subtracting the sum from gross salary 
*/

public class Deductions {
    private String employee;
    private double grossSalary;

    /* PRSI, USC and Income Tax are the deductions that need to be calculated */
    private double PRSI;
    private double USC;
    private double incomeTax;

    /** 
     * Constructor that takes an employee object as an argument as well as their gross salary.
     * 
     * @param abc The name of the employee
     * @param grossSalary The gross salary of the employee.
     * @throws FileNotFoundException If file with salary data is not found
     */
    public Deductions(String abc, double grossSalary) throws FileNotFoundException {
        this.employee = abc;
        this.grossSalary = grossSalary;
        // this.grossSalary = employee.getSalary();
        this.PRSI = calculatePRSI();
        this.USC = calculateUSC();
        this.incomeTax = calculateIncomeTax();
    }

    /** 
     * Method to calculate PRSI based on gross pay (it is 4% for the majority).
     * 
     * @return Calculated PRSI
     */
    public double calculatePRSI() {
        PRSI = grossSalary * 0.04;
        return PRSI;
    }

    /** 
     * Method to find USC based on gross pay.
     * 
     * @return Calculated USC
     */
    public double calculateUSC() {
        int[] thresholds = {12012, (12012 + 13748), (12012 + 13748 + 44284)}; // Array that stores thresholds determining different rates of USC
        if (grossSalary <= thresholds[0]) {
            USC = grossSalary * 0.005; // 0.5% for income up to €12,012
        } else if (grossSalary <= thresholds[1]){
            USC = grossSalary * 0.02; // 2% for the next €13,748
        } else if (grossSalary <= thresholds[2]){
            USC = grossSalary * 0.04; // 4% for the next €44,248
        } else {
            USC = grossSalary * 0.08; // 8% for anything higher than that
        }
        return USC;
    }

   /** 
     * Method to calculate Income Tax.
     * 
     * @return Calcualated income tax
     */
    public double calculateIncomeTax() {
        double cutOff = 42000; // standard cutoff for 2024 for a single person
        double standardRate = 0.2; // 20% standard rate
        double increasedRate = 0.4; // 40% rate for higher incomes

        if (grossSalary <= cutOff) {
            incomeTax = grossSalary * standardRate; // If income is below or equal to cutoff, 20% rate applies
        } else {
            double standardTax = cutOff * standardRate;
            double higherTax = (grossSalary - cutOff) * increasedRate;
            incomeTax = standardTax + higherTax; // for higher incomes, the tax is the sum of tax for cutoff and increased custom rate
        }
        return incomeTax;
    }

    /** 
     * Getter method for gross salary.
     * 
     * @return The gross salary of the employee.
     */
    public double getGrossSalary(){
        return grossSalary;
    }

    /** 
     * Getter method for PRSI.
     * 
     * @return PRSI of the employee
     */
    public double getPRSI() {
        return PRSI;
    }

    /** 
     * Getter method for USC.
     * 
     * @return USC of the employee
     */
    public double getUSC() {
        return USC;
    }

    /** 
     * Getter method for income tax.
     * 
     * @return The income tax 
     */
    public double getIncomeTax() {
        return incomeTax;
    }

    /** 
     * Method to calculate total deductions.
     * 
     * @return The total deductions (PRSI + USC + income tax).
     */
    public double deductionTotal() {
        return PRSI + USC + incomeTax;
    }

    /** 
     * Method to calculate net salary.
     * 
     * @return The net salary after deductions.
     */
    public double netSalary() {
        double netSalary = grossSalary - deductionTotal();
        return netSalary;
    }

}

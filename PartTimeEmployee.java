import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**Part-time employees must submit a pay claim form
     * by the second Friday of a month in order to be paid that month.
     * The payroll system should generate pay slips for all full-time staff
     * and hourly paid staff (with current claims) on the 25th day of each month.
     */

public class PartTimeEmployee extends Employee
{
    private double hourlyRate;
    private double hoursWorked;

/*Constructor taking parameters from parent class(employee) and adding hourly rate*/
    public PartTimeEmployee(String name,String jobCategory, String jobTitle, int scalePoint, double hourlyRate) throws FileNotFoundException {
        super(name,jobCategory, jobTitle, scalePoint);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0; //hours reset each month hence 0, new hours for current period are to be added using setter method below
    }

    /*Getter method for hourly rate*/
    public double getHourlyRate(){
        return hourlyRate;
    }

    /*Getter method for hours worked*/
    public double getHoursWorked(){
        return hoursWorked;
    }

    /*Setter method to get hours worked in a given month*/
    public double setHoursWorked(double hours){
        this.hoursWorked += hoursWorked + hours;
        return hoursWorked;
    }
    public void setsalary() {
    	this.manualsetSalary(grossPay());
    }

   /*Calculate gross pay given hours worked and hourly rate*/
    public double grossPay() {
        double grossPay = hoursWorked * hourlyRate;
        return grossPay;
    }

    /*Claim form method*/
    public void payClaim() {
        this.hoursWorked = 0;
        System.out.printf("Pay claim submitted successfully. Gross pay: %.2f", grossPay());
    }

	/*Method to move staff up to the next category, same as in full time employee class and employee*/
    @Override
	public void moveupincatagory() throws FileNotFoundException {
		String current=this.getJobtitle();
		line = new Scanner(new File("../group/src/payscale for partime.csv"));
		boolean done=false;
		String change=current;
		line.nextLine();
		while(!done&&line.hasNext()) {
			String input=line.nextLine();
			if(input.equals(this.getJobcatagory())) {
				//in the right catagory
				while(line.hasNext()&&!done) {
					input=line.nextLine();
					String[] parts=input.split(",");
					if( current.equals(parts[0]) )
					{
					done=true;
					}
					else{change=parts[0];}
				
				}
				}
			
		}
		if(change!=current) {
		this.setjobtitle(change);
		this.setscalepoint(1);
		this.setsalary();
		}
		else {System.out.print("At the top\n");}
		
	}

	/*Method to get the top pay scale for given category*/
	public int gettopPayscale() throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payscale for partime.csv"));
		
		boolean done=false;
		//int topPayscale;
		line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			
			if(input.equals(this.getJobcatagory())) {
				
				while(line.hasNext()&&!done) {
					input=line.nextLine();
					String[] parts=input.split(",");
					
					if( this.getJobtitle().equals(parts[0]) )
					{
					input=line.nextLine();
					
					return Integer.parseInt(parts[1]);
					}
				
				}
				}
		}
	
		return -1;
	}

	/*Method to update employee's salary if moved up in category*/
	public void moveupsalarypoint() throws FileNotFoundException {//check if theycan move up
		if(this.gettopPayscale()>this.getScalepoint()) {
			this.setscalepoint(this.getScalepoint()+1);
			this.setsalary();
			;}
		else {System.out.println("Top of payscale\n");}
	}
	
	/*To string method*/
    @Override
    public String toString() {
    	return this.getName()+","+this.getJobcatagory()+","+this.getJobtitle()+","+this.getScalepoint()+","+this.hourlyRate+","+this.hoursWorked ;
		
    }
  
}

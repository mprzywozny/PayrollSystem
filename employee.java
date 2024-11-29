package ulpayproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
/**Class that represents the employees*/
public class employee {
	private String name;
	private int scalepoint;
	private String jobtitle;
	private String jobcategory;
	private double salary;
    Scanner line ;  // Create a Scanner object

	/**
	 * Constructor for employee
	 * @param name name of employee
	 * @param jc job category they are in
	 * @param jt their job title 
	 * @param sp the scale point they are on
	 * @throws FileNotFoundException If a file is not found this is thrown*/
	employee(String name,String jc,String jt,int sp)throws FileNotFoundException
	{
		this.name=name.toUpperCase();
		this.jobcategory=jc.toUpperCase();
		this.jobtitle=jt.toUpperCase();
		this.scalepoint=sp;
		this.setsalary();
		
		
	}
	/**Constructor for employee
	 * @param input takes the input and splits into parts to initialize employee
	 * @throws FileNotFoundException If a file is not found this is thrown*/
	 

	employee(String input)throws FileNotFoundException{
		String[] parts=input.split(",");

		this.name=parts[0].toUpperCase();
		this.jobcategory=parts[1].toUpperCase();
		this.jobtitle=parts[2].toUpperCase();
		this.scalepoint=Integer.parseInt(parts[3]);
		this.setsalary();
		
	}
	
	//getter methods
	
	/**
	 * gets the salary of this employee
	 * @return the salary as a double
	 * @throws FileNotFoundException If a file is not found this is thrown
	 * */
	public double getSalary() throws FileNotFoundException{
		line = new Scanner(new File("../ulpay/src/payscale.csv"));
		boolean done=false;
		String header=line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			if(input.equals(this.getJobcatagory())) {
				while(line.hasNext()) {
					//finds salary
					input=line.nextLine();
					String[] parts=input.split(",");
					
					if(this.scalepoint==Integer.parseInt(parts[1]) && this.jobtitle.equals(parts[0]) )
					{
						return Double.parseDouble(parts[2]);
					}
				
				}
			}
		}
		return -1;
	}
	/**
	 * gets the job category of this employee
	 * @return The job catagory
	 * 
	 * */
	public String getJobcategory() {
		return this.jobcategory;
	}
	/**
	 * Gets the salary of this employee
	 * @return The salary of this employee
	 *
	 * */
	public double getsalary() {
		return this.salary;
	}
	/**
	 * Gets the job title of this employee
	 * @return the job title
	 * */
	public String getJobtitle(){
		return this.jobtitle;
	}
	/**
	 * Gets the scale point of this employee
	 * @return The scale point of this employee
	 * */
	public int getScalepoint(){
		return this.scalepoint;
	}
	/**Gets the name of the employee
	 * @return name of employee*/
	public String getName(){
		return this.name;
	}
	//setter
	/**
	 * sets the scale point of the employee
	 * @param sp number of the point of the scale as an int.
	 * */
	public void setscalepoint(int sp) {
		this.scalepoint=sp;
	} 
	/**
	 * sets the job title of the employee
	 * @param jt the title of the job gets in upper or lowercase.
	 * */
	public void setjobtitle(String jt) {
		jt.toUpperCase();
		this.jobtitle=jt;
	} 
	/**
	 * sets the job category ie. academic,information technology ect.
	 * @param jc the title of the job gets in upper or lower case.*/
	public void setjobcategory(String jc) {
		jc.toUpperCase();
		this.jobcategory=jc;
	} 
	/**
	 * sets the salary of this employee
	 * @param s The salary*/
	public void manualsetSalary(double s) {
		this.salary=s;
	}
	
	/**
	 * based on the jobtitle and scale point it finds what the salary should be and sets that as the salary
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public void setsalary() throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payscale.csv"));
		boolean done=false;
		line.nextLine();
		while(line.hasNext()&&!done)
		{
			String input=line.nextLine();
			if(input.equals(this.jobcatagory)) {
				while(line.hasNext()&&!done) {
					//finds salary
					input=line.nextLine();
					String[] parts=input.split(",");
					
					if(this.scalepoint==Integer.parseInt(parts[1]) && this.jobtitle.equals(parts[0]) )
					{
						this.salary=Double.parseDouble(parts[2]);
						done=true;
					}
				
				}
			}
		}
	}
	/**Gets the top of the pay scale for a given profession.
	 *@return the top of the payscale for this employee's job type
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public int getTopPayscale() throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payscale.csv"));
		boolean done=false;
		int topPayscale;
		//line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			if(input.equals(this.jobcatagory)) {
				while(line.hasNext()&&!done) {
					input=line.nextLine();
					String[] parts=input.split(",");
					if( this.jobtitle.equals(parts[0]) )
					{
					input=line.nextLine();
					
					return topPayscale=Integer.parseInt(parts[1]);
					}
				
				}
				}
		}
	
		return -1;
	}
	
	/**
	 * Moves an employee to the next job title up and sets their salary. 
	 * It then puts them on the first point of the payscale.
	 * @throws  FileNotFoundException if payscale.csv not found

	 * */
	public void moveupincategory() throws FileNotFoundException {
		String current=this.getJobtitle();
		line = new Scanner(new File("../group/src/payscale.csv"));
		boolean done=false;
		String change=current;
		line.nextLine();
		while(!done&&line.hasNext()) {
			String input=line.nextLine();
			if(input.equals(this.jobcategory)) {
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
		this.setsalary();}
		else {System.out.print("At the top\n");}
		
	}
	/**
	 * moves employee up a salary point
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public void moveupsalarypoint() throws FileNotFoundException {//check if theycan move up
		if(this.getTopPayscale()>this.scalepoint) {
			this.setscalepoint(this.scalepoint+1);
			this.setsalary();
			;}
		else {System.out.println("Top of payscale\n");}
	}
	
	/**
	 * converts datafields to string in a form that can be written into a csv file
	 * */
	
	public String toString() {
		//name,jobcatagory,jobtitle,scalepoint,salary
		return this.name+","+this.jobcategory+","+this.jobtitle+","+this.scalepoint+","+this.getsalary();
	}
	
	
	
	
}




package ulpayproject;
import  java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**Class that represents the employees*/
public class Employee {
	private String name;
	private int scalePoint;
	private String jobTitle;
	private String jobCategory;
	private double salary;
	Scanner line ;  // Create a Scanner object

	/**
	 * Constructor for employee
	 * @param name name of employee
	 * @param jc job category they are in
	 * @param jt their job title
	 * @param sp the scale point they are on
	 * @throws FileNotFoundException If a file is not found this is thrown*/
	public Employee(String name,String jc,String jt,int sp)throws FileNotFoundException
	{
		this.name = name.toUpperCase();
		this.jobCategory = jc.toUpperCase();
		this.jobTitle = jt.toUpperCase();
		this.scalePoint = sp;
		this.setSalary();


	}
	/**Constructor for employee
	 * @param input takes the input and splits into parts to initialize employee
	 * @throws FileNotFoundException If a file is not found this is thrown*/


	Employee(String input)throws FileNotFoundException{
		String[] parts=input.split(",");

		this.name=parts[0].toUpperCase();
		this.jobCategory=parts[1].toUpperCase();
		this.jobTitle=parts[2].toUpperCase();
		this.scalePoint=Integer.parseInt(parts[3]);
		this.setSalary();

	}

	//getter methods

	/**
	 * Gets the salary of this employee
	 * @return the salary as a double
	 * @throws FileNotFoundException If a file is not found this is thrown
	 * */
	public double getsalary() throws FileNotFoundException{
		line = new Scanner(new File("./PayScale.csv"));
		
		line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			if(input.equals(this.getJobCategory())) {
				while(line.hasNext()) {
					//finds salary
					input=line.nextLine();
					String[] parts=input.split(",");

					if(this.scalePoint == Integer.parseInt(parts[1]) && this.jobTitle.equals(parts[0]) )
					{
						return Double.parseDouble(parts[2]);
					}

				}
			}
		}
		return -1;
	}
	/**
	 * Gets the job category of this employee
	 * @return The job category
	 *
	 * */
	public String getJobCategory() {
		return this.jobCategory;
	}
	/**
	 * Gets the salary of this employee
	 * @return The salary of this employee
	 *
	 * */
	public double getSalary() {
		return this.salary;
	}
	/**
	 * Gets the job title of this employee
	 * @return the job title
	 * */
	public String getJobTitle(){
		return this.jobTitle;
	}
	/**
	 * Gets the scale point of this employee
	 * @return The scale point of this employee
	 * */
	public int getScalePoint(){
		return this.scalePoint;
	}
	/**Gets the name of the employee
	 * @return name of employee*/
	public String getName(){
		return this.name;
	}
	/**
	 * Sets the scale point of the employee
	 * @param sp number of the point of the scale as an int.
	 * */
	public void setScalePoint(int sp) {
		this.scalePoint = sp;
	}
	/**
	 * Sets the job title of the employee
	 * @param jt the title of the job gets in upper or lowercase.
	 * */
	public void setJobTitle(String jt) {
		this.jobTitle = jt.toUpperCase();
	}
	/**
	 * Sets the job category i.e. academic,information technology ect.
	 * @param jc the title of the job gets in upper or lower case.*/
	public void setJobCategory(String jc) {
		this.jobCategory = jc.toUpperCase();
	}
	/**
	 * Sets the salary of this employee
	 * @param s The salary*/
	public void manualsetSalary(double s) {
		this.salary = s;
	}

	/**
	 * Based on the job title and scale point it finds what the salary should be and sets that as the salary
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public void setSalary() throws FileNotFoundException {
		line = new Scanner(new File("./PayScale.csv"));
		boolean done = false;
		line.nextLine();
		while(line.hasNext()&&!done)
		{
			String input = line.nextLine();
			if(input.equals(this.jobCategory)) {
				while(line.hasNext()&&!done) {
					//finds salary
					input = line.nextLine();
					String[] parts = input.split(",");

					if(this.scalePoint == Integer.parseInt(parts[1]) && this.jobTitle.equals(parts[0]) )
					{
						this.salary = Double.parseDouble(parts[2]);
						done = true;
					}

				}
			}
		}
	}
	/**Gets the top of the pay scale for a given profession.
	 *@return the top of the payscale for this employee's job type
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public int getTopPayScale() throws FileNotFoundException {
		line = new Scanner(new File("./PayScale.csv"));
		boolean done = false;
		
		//line.nextLine();
		while(line.hasNext())
		{
			String input = line.nextLine();
			if(input.equals(this.jobCategory)) {
				while(line.hasNext()&&!done) {
					input = line.nextLine();
					String[] parts = input.split(",");
					if( this.jobTitle.equals(parts[0]) )
					{
						input=line.nextLine();

						return Integer.parseInt(parts[1]);
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
	public void moveUpInCategory() throws FileNotFoundException {
		String current = this.getJobTitle();
		line = new Scanner(new File("./PayScale.csv"));
		boolean done = false;
		String change = current;
		line.nextLine();
		while(!done&&line.hasNext()) {
			String input = line.nextLine();
			if(input.equals(this.jobCategory)) {
				//in the right category
				while(line.hasNext()&&!done) {
					input = line.nextLine();
					String[] parts=input.split(",");
					if( current.equals(parts[0]) )
					{
						done=true;
					}
					else{change=parts[0];}

				}
			}

		}
		if(change != current) {
			this.setJobTitle(change);
			this.setScalePoint(1);
			this.setSalary();}
		else {System.out.print("At the top\n");}

	}
	/**
	 * Moves employee up a salary point
	 *@throws  FileNotFoundException if payscale.csv not found
	 * */
	public void moveUpSalaryPoint() throws FileNotFoundException {//check if they can move up
		if(this.getTopPayScale()>this.scalePoint) {
			this.setScalePoint(this.scalePoint+1);
			this.setSalary();
			;}
		else {System.out.println("Top of payscale\n");}
	}

	/**
	 * Converts data fields to string in a form that can be written into a csv file
	 * */

	public String toString() {
		//name,jobCategory,jobTitle,scalePoint,salary
		return this.name + "," + this.jobCategory + "," + this.jobTitle + "," + this.scalePoint + "," + this.salary;
	}




}

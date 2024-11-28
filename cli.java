package ulpayproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**class for cli*/
public class cli {
		private String usertype;
		private Scanner in;
		private Scanner userfind;
		private Scanner line;
		private employee e;
		private LocalDate Today;
		static String[] usertypes= {"employee","human resources","admin"};
		//static Overwrite o=new Overwrite();
		static Deductions d;
		static Payslip payslip;
		/**initialises the scanner*/
		 public cli()
		   {
		      in = new Scanner(System.in);
		   }
		 /**Starts the cli the CLI 
		  * @throws java.io.IOException if a csv file is not found.
		  * */
		 public void run()
		         throws IOException
		   {

		      boolean more = true;
		      
		      while (more)
		      { 
		    	 // Today=LocalDate.now();
   			
		    	 LocalDate Today = LocalDate.of(2024, 11, 25);
   			//used for testing
		    	
   			
   			if(Today.getDayOfMonth()==25) {
				//generate payslip	
   				//find names in sub pay slip
   				if(!(payclaimtoArrayList().isEmpty())) {
   				ArrayList<String> payclaims=payclaimtoArrayList();//payclaims list of names submitted payclaimes
   				
   				for(String p:payclaims) {
   					//find employee
   					System.out.println(p);
   					String[] check=p.split(",");
   					
   					    String[] YMD=check[1].split("-");
   					    LocalDate date = LocalDate.of(Integer.parseInt(YMD[0]), Integer.parseInt(YMD[1]), Integer.parseInt(YMD[2]));
   					    LocalDate firstday= LocalDate.of(Integer.parseInt(YMD[0]), Integer.parseInt(YMD[1]), 1);
   					    int secondfriday = 0;
   					    if(firstday.getDayOfWeek()==DayOfWeek.FRIDAY) {secondfriday=8;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.SATURDAY) {secondfriday=9;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.SUNDAY) {secondfriday=10;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.MONDAY) {secondfriday=11;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.TUESDAY) {secondfriday=12;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.WEDNESDAY) {secondfriday=13;}
   					    else if(firstday.getDayOfWeek()==DayOfWeek.THURSDAY) {secondfriday=14;}
   					    if(date.getDayOfMonth()<=secondfriday||date.isBefore(firstday)) {
   					    String employeedetales=SearchEmployee(check[0]);
   					    String[] eds=employeedetales.split(",");
   					    
   					    //NAME,JOBCATAGORY,JOBTYPE,SCALEPOINT,HOURLYRATE,HOURSDONE
   					    double HOURLYRATE=Double.parseDouble(eds[4]);
   					    
   					    double HOURSDONE=Double.parseDouble(eds[5]);
   					    d=new Deductions(check[0],HOURSDONE*HOURLYRATE);
   					    //System.out.println(d.toString());
   					    //String employeeName, String jobTitle, int scalePoint, double salary, double deductions,double grossSalary
   					    String netpay=Double.toString(HOURSDONE*HOURLYRATE);
   					    payslip=new Payslip(eds[0],eds[2],eds[3],(HOURSDONE*HOURLYRATE),d.deductionTotal(),d.getGrossSalary());
   					    addtopayslip(check[0],payslip.toString());
   					    payclaimdone(check[0]);
   					    }}}
   				
   					line = new Scanner(new File("../group/src/employees.csv"));
   					line.nextLine();
   					while(line.hasNext()) {
   						String i=line.nextLine();
   						String[] s=i.split(",");
   					
   						d=new Deductions(s[0],Double.parseDouble(s[4]));
   						//NAME,JOBCATAGORY,JOBTITLE,SCALEPOINT,SALARY
   					    //String employeeName, String jobTitle, int scalePoint, double salary, double deductions,double grossSalary
   						payslip=new Payslip(s[0],s[2],s[3],Double.parseDouble(s[4]),d.deductionTotal(),d.getGrossSalary());
   					    addtopayslip(s[0],payslip.toString());
   					  
   					//print to payslip
   					
   				}
				}
   			
		    	  usertype=null;
		         System.out.println("user name:");
		         String user = in.nextLine().toUpperCase();
		         userfind = new Scanner(new File("../group/src/users.csv"));
		     		boolean done=false;
		     		while(userfind.hasNext()&& done!=true) {
		     			
		     			
		     			
		     			String input=userfind.nextLine();
		     			String[] parts=input.split(",");		
		     			if(user.equals(parts[0].toUpperCase()))
		     					{ 	usertype=parts[1];
		     						System.out.printf("user:%s\nusertype:%s\n",user,usertype);
		     						done=true;
		     					}
		     				
		     		}
		     		if(this.usertype==null) {
		     			System.out.print("No user detected,add new user?y/n");
		     			String answer = in.nextLine();
		     			if(answer.toUpperCase().equals("Y")) {
		     				System.out.print("user name:");
		     				String un = in.nextLine();
		     				System.out.println("user type 1)employee 2)human resources 3)admin");
		     				int ut = Integer.parseInt(in.nextLine());
		     				if(ut==1) {System.out.println("Only admins can add employees.");}
		     				else {
		     				usertype=usertypes[ut-1];
		     				user u=new user(un,usertype);}
		     				
		     			}
		     			else if(answer.toUpperCase().equals("N")) {
		     			System.out.print("exiting");
		     			more=false;
		     			
		     			}
		     		}
		     		  //usertypes= ["employee","human resources","admin"];
		     		if (usertype==null) {
		     		
		     		}
		     		else if(usertype.equals("employee")) {
		     			//employeecli
		     			
		     			
		     			
		     			String Name=user;
		     			//confirm changes that admin made
		     			String c=searchnotif(Name.toUpperCase());
		     			String answer;
						if (c.equals("")) {
		     				System.out.println("No changes were made.");
		     			}
		     			else {
		     			String[] list=c.split("!");
		     			for (int i=0;i<list.length;i++) {
		     				System.out.println(list[i]);
		     				}
		     			System.out.println("confirm changes were made:Y/N");
		     			answer = in.nextLine();
		     			if(answer.toUpperCase().equals("Y")) {
		     				//String[] list=c.split("!");
		     				for (String x:list) {
		     				String[] l=x.split(",");
		     				
		     				adressednotif(l[0]);
		     				}
		     			}
		     			}
		     			//see details
		     			System.out.println("Are you p)artime or f)ulltime");
		     			answer = in.nextLine();
		     			String info="";
		     			if(answer.toUpperCase().equals("P")) {
		     				info=searchemployees(Name,"Parttime employees");
		     				String[] format=info.split(",");
		     				
		     				System.out.printf("name: %s\nJob title: %s\nScale point: %s\nHourly rate: %s\n", format[0], format[2], format[3], format[4]);
		     				//Part-time employees must submit a pay claim form by the second Friday of a month in order to be paid that month.  
		     				//if not already
		     				if(!inpayclaim(Name)){
		     				System.out.println("Submit payclaim form?(Y/N) has to be done by the second friday of the month.");
		     				String Submit = in.nextLine();
		     					if(Submit.toUpperCase().equals("Y")) {
		     						//add to list
		     						submitpayclaim(Name);
		     					}
		     				}
		     				else{
		     					System.out.println("payclaim form submitted");
		     				}
		     			}
		     			else if(answer.toUpperCase().equals("F")) {
		     				info=searchemployees(Name,"employees");
		     				String[] format=info.split(",");		     				
		     				System.out.printf("name: %s\nJob title: %s\nscale point: %s\nsalary: %s\n", format[0], format[2], format[3], format[4]);
		     				//The payroll system should generate pay slips for all full-time staff and hourly paid staff (with current claims)
		     				//on the 25th day of each month. 
		     				
		     			}
		     			//pay claim form
		     			
		     			//see pay slips
		     			//with name and date
		     			System.out.println("See pay slips?(Y/N)");
		     			String seepayslips = in.nextLine();
		     			if(seepayslips.toUpperCase().equals("Y")) {
		     				
		     				
		     				//getchoicesofpayslip(String Name)
		     				
		     				if(!(getchoicesofpayslip(Name).isEmpty())){
		     				ArrayList <String> choices=getchoicesofpayslip(Name);
		     				System.out.println("Input date:");
		     				 char ch = 'A';
		     		         for (String choice : choices)
		     		         {
		     		            System.out.println(ch + ") " + choice); 
		     		            ch++;
		     		         }
		     		         String input = in.nextLine();
		     		         int n = input.toUpperCase().charAt(0) - 'A';
		     		        String inputdate= choices.get(n);
		     				String ps=seepayslip(Name,inputdate);
		     				System.out.println(ps);}
		     				else {System.out.print("No payslip for this employee");}
		     			}
		     			
		     			
		     			more=false;
		     			
		     		}
		     		
		     		
		     		//hr
		     		else if(usertype.equals("human resources")){
		     			//hr cli
		     			boolean hrworking=true;
		     			while(hrworking==true&&more==true) {
		     			
		     			System.out.println("p)romote,q)uit");
		     			String answer = in.nextLine();
		     			if(answer.toUpperCase().equals("P")) {
		     				System.out.println("f)ulltime P)arttime Q)uit");
		     				String fpq=in.nextLine();
		     				if(fpq.toUpperCase().equals("F")) {
		     				System.out.println("type the name of the person to promote.");
			     			String who = in.nextLine();
			     			Scanner line = new Scanner(new File("../group/src/employees.csv"));
			     			String header=line.nextLine();
			     			boolean foundperson=false;
			     			while(line.hasNext()) {
			     						//finds salary
			     						String input=line.nextLine();
			     						String[] parts=input.split(",");
			     						
			     						if(who.toLowerCase().equals(parts[0].toLowerCase()))
			     						{System.out.println(parts[0]+",is a "+parts[2]+" at scale point "+parts[3]);
			     						foundperson=true;
			     						e=new FullTimeEmployee(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]));
			     						}
			     						}
			     			
			     			if (foundperson) {
			     				System.out.println("a) move up pay scale\nb) move up jobtitle\nq) quit");
			     				String abq=in.nextLine();
			     				
			     				
			     				if(abq.toUpperCase().equals("A")) {
			     					//name,jobcatagory,jobtitle,scalepoint
			     					((FullTimeEmployee)e).moveupsalarypoint();
			     					String[] format=e.toString().split(",");
			     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
			     					String change="You have been moved up a scale point";
			     					createnotif(format[0],change);
			     					overwrite_employee((FullTimeEmployee)e);
					     			}
			     				
					     			
			     				
			     					
			     			else if(abq.toUpperCase().equals("B")) {
		     					//name,jobcatagory,jobtitle,scalepoint
			     				((FullTimeEmployee)e).moveupincatagory();
		     					String[] format=e.toString().split(",");
		     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
		     					String change="You have been moved up to the next job catagory";
		     					createnotif(format[0],change);
		     					overwrite_employee((FullTimeEmployee)e);
			     			}
			     			else if(abq.toUpperCase().equals("Q")) {
			     				System.out.println("exited the program");
			     				more=false;
			     				hrworking=false;
			     				}}}//end of f
		     			else if(fpq.toUpperCase().equals("P")) {
		     				//parttime to edit
		     			
		     				System.out.println("type the name of the person to promote.");
			     			String who = in.nextLine();
			     			Scanner line = new Scanner(new File("../group/src/Parttime employees.csv"));
			     			String header=line.nextLine();
			     			boolean foundperson=false;
			     			while(line.hasNext()) {
			     						//finds salary
			     						String input=line.nextLine();
			     						String[] parts=input.split(",");
			     						
			     						if(who.toLowerCase().equals(parts[0].toLowerCase()))
			     						{System.out.println(parts[0]+",is a "+parts[2]+" at scale point "+parts[3]);
			     						foundperson=true;
			     						e=new PartTimeEmployee(parts[0],parts[1],parts[2],Integer.parseInt(parts[3]),Double.parseDouble(parts[4]));
			     						}
			     						}
			     			
			     			if (foundperson) {
			     				System.out.println("a) move up pay scale\nb) move up jobtitle\nq) quit");
			     				String abq=in.nextLine();
			     				
			     				
			     				if(abq.toUpperCase().equals("A")) {
			     					//name,jobcatagory,jobtitle,scalepoint
			     					((PartTimeEmployee) e).moveupsalarypoint();
			     					String[] format=e.toString().split(",");
			     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
			     					String change="You have been moved up a scale point";
			     					createnotif(format[0],change);
			     					overwrite_employee((PartTimeEmployee)e);
					     			}
			     			
					     			
			     				
			     					
			     			else if(abq.toUpperCase().equals("B")) {
		     					//name,jobcatagory,jobtitle,scalepoint
			     				((PartTimeEmployee) e).moveupincatagory();
		     					String[] format=((PartTimeEmployee) e).toString().split(",");
		     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
		     					String change="You have been moved up to the next job catagory";
		     					createnotif(format[0],change);
		     					overwrite_employee((PartTimeEmployee)e);
		     				
		     			}//end of p
			     			}}
		     				
		     			else if(fpq.toUpperCase().equals("Q")) {
		     				System.out.println("exited the program");
		     				more=false;
		     				hrworking=false;
		     			}//end of q
		     			}
		     				
		     			else if(answer.toUpperCase().equals("Q")) {
		     				System.out.println("exited the program");
		     				more=false;
		     				hrworking=false;
		     				}
		     			
		     			}
		     			
		     		}
		   
		     		//admin
		     else if(usertype.equals("admin")){
		     			//admin cli
		     			
		     			//add employee
		    	 boolean admin_working=true;
		    	 
		    	 while(admin_working==true&&more==true) {
		    		 	System.out.println("Add employee?(y/n)");
		     			String addemployee = in.nextLine();
		     			if(addemployee.toUpperCase().equals("Y")){
		     				//yes
		     				System.out.println("f)ulltime P)arttime Q)uit");
		     				String answer1 = in.nextLine();
		     				if(answer1.toUpperCase().equals("F")) {
		     					//(String name,String jobCategory, String jobTitle, int scalePoint)
		     					System.out.println("Name:");
			     				String Name = in.nextLine().toUpperCase();
			     				//pick the job catagory
			     				System.out.println("select job category:");
			     				String jobCategory;
			     				ArrayList <String> choices=getchoicesinjc();
			     				 char c = 'A';
			     		         for (String choice : choices)
			     		         {
			     		            System.out.println(c + ") " + choice); 
			     		            c++;
			     		         }
			     		         String input = in.nextLine();
			     		         int n = input.toUpperCase().charAt(0) - 'A';
			     		         if (0 <= n && n < choices.size()) {
			     		        	jobCategory= choices.get(n);
			     		        	System.out.println(jobCategory);
			     		         
			     		       
			     		        System.out.println("select job title: ");
			     				String jobTitle;
			     				ArrayList <String> choicesofjt=getchoicesinjtwithjc(jobCategory);
			     				 c = 'A';
			     		         for (String choice : choicesofjt)
			     		         {
			     		            System.out.println(c + ") " + choice); 
			     		            c++;
			     		         }
			     		         String input2 = in.nextLine();
			     		         int n2 = input2.toUpperCase().charAt(0) - 'A';
			     		         if (0 <= n2 && n2 < choicesofjt.size()) {
			     		        	jobTitle= choicesofjt.get(n2);
			     		        	System.out.println(jobTitle);
			     		        	
			     		        	//payscale
			     		        	int payscalepoint=-1;
			     		        	//employee e =new employee();
			     		        	while(payscalepoint<0 || payscalepoint>getTopPayscale(jobCategory, jobTitle)) {
			     		        	 System.out.printf("Select point on payscale equal to or below %d: \n",getTopPayscale(jobCategory, jobTitle));
			     		        	 payscalepoint = in.nextInt();}
			     		        	//info gathered
			     		        	//System.out.printf("jc %s jt %s psp %d\n",jobCategory,jobTitle,payscalepoint);
			     		        	user u=new user(Name,"employee");			     		 
			     		        	FullTimeEmployee f=new FullTimeEmployee(Name,jobCategory,jobTitle,payscalepoint);
			     		        	writetolistofusers(f.toString(),"employees");
			     		        	System.out.print("added: "+f.toString());

				     				admin_working=false;
				     				more=false;
			     		         }else {System.out.println("sorry something went wrong try again");}//added both jc and jt
			     		         
			     		         }else {System.out.println("sorry something went wrong try again");}
			     		         
			     		           
			     			//end of F	
		     				}
		     				if(answer1.toUpperCase().equals("P")) {
		     					//(String name,String jobCategory, String jobTitle, int scalePoint)
		     					System.out.println("Name:");
			     				String Name = in.nextLine().toUpperCase();
			     				//pick the job catagory
			     				System.out.println("select job category:");
			     				String jobCategory;
			     				ArrayList <String> choices=ptgetchoicesinjc();
			     				 char c = 'A';
			     		         for (String choice : choices)
			     		         {
			     		            System.out.println(c + ") " + choice); 
			     		            c++;
			     		         }
			     		         String input = in.nextLine();
			     		         int n = input.toUpperCase().charAt(0) - 'A';
			     		         if (0 <= n && n < choices.size()) {
			     		        	jobCategory= choices.get(n);
			     		        	System.out.println(jobCategory);
			     		         
			     		       
			     		        System.out.println("select job title: ");
			     				String jobTitle;
			     				ArrayList <String> choicesofjt=ptgetchoicesinjtwithjc(jobCategory);
			     				 c = 'A';
			     		         for (String choice : choicesofjt)
			     		         {
			     		            System.out.println(c + ") " + choice); 
			     		            c++;
			     		         }
			     		         String input2 = in.nextLine();
			     		         int n2 = input2.toUpperCase().charAt(0) - 'A';
			     		         if (0 <= n2 && n2 < choicesofjt.size()) {
			     		        	jobTitle= choicesofjt.get(n2);
			     		        	System.out.println(jobTitle);
			     		        	
			     		        	//payscale
			     		        	int payscalepoint=-1;
			     		        	//employee e =new employee();
			     		        	while(payscalepoint<0 || payscalepoint>ptgetTopPayscale(jobCategory, jobTitle)) {
			     		        	 System.out.printf("Select point on payscale equal to or below %d: \n",ptgetTopPayscale(jobCategory, jobTitle));
			     		        	 payscalepoint = in.nextInt();
			     		        	//hourly rate
			     		        	}
			     		        	
			     		        	Scanner payscalept = new Scanner(new File("../group/src/payscale for partime.csv"));
			     		        	payscalept.nextLine();
			     		        	double hourlyrate=0;
			     		        	while(payscalept.hasNext())
			     		        	{
			     					String search=payscalept.nextLine();
			     					String[] parts=search.split(",");
			     					
			     					if(parts.length>1&&jobTitle.equals(parts[0])&&Integer.parseInt(parts[1])==payscalepoint) {
			     						hourlyrate=Double.parseDouble(parts[2]);
			     						
			     						}
			     		        	}
			     		        	//info gathered
			     		        	//System.out.printf("jc %s jt %s psp %d\n",jobCategory,jobTitle,payscalepoint);
			     		        	PartTimeEmployee f=new PartTimeEmployee(Name,jobCategory,jobTitle,payscalepoint,hourlyrate);
			     		        	user u=new user(Name,"employee");			     		 
			     		        	writetolistofusers(f.toString(),"Parttime employees");
			     		        	System.out.print("added: "+f.toString());
			     		        	more=false;
				     				admin_working=false;
			     		         }else {System.out.println("sorry something went wrong try again");}//added both jc and jt
			     		         
			     		         }else {System.out.println("sorry something went wrong try again");}
			     				
		     				//end of p
			     				}
		     				else if(answer1.toUpperCase().equals("Q")) {
		     				more=false;
		     				admin_working=false;
		     				}
		     				
		     			//end of yes	
		     			}
		     			//no
		     			else if (addemployee.toUpperCase().equals("N")){
		     			more=false;
		     			admin_working=false;
		     			in.close();
		     			}
		     			
		     		}
		     }
		     			
		      else {System.out.print("failed");
		      
		     	}
		      
		      }}


		 	
		 	private Scanner line1;
		 	private PrintWriter w;
		 	/**
		 	 * Overwrites the employee csv file with an updated employee in the space where the employee was.
		 	 * @param e A FullTimeEmployee object.
		 	 * @throws IOException If the file is not found it throws this exception.
		 	 * */
		 	public void overwrite_employee(FullTimeEmployee e) throws IOException {
		 	ArrayList<String> temp=new ArrayList<String>();
		 		line1 = new Scanner(new File("../group/src/employees.csv"));
		 		temp.add(line1.nextLine());
		 		while(line1.hasNext()) {
		 			String i=line1.nextLine();
		 			String[] s=i.split(",");
		 			if((s[0].toUpperCase()).equals(e.getName().toUpperCase())) {
		 			temp.add(e.toString());
		 			}
		 		
		 			else {
		 				
		 				temp.add(i);}
		 		}
		 		
		 		PrintWriter clear=new PrintWriter("../group/src/employees.csv");
		 		for(String x:temp) {
		 			PrintWriter writer=new PrintWriter(new FileWriter("../group/src/employees.csv",true));
		 			writer.println(x.toUpperCase());
		 			writer.close();
		 		}
		 		}
		 	/**Overwrites the employee csv file with an updated part time employee 
		 	 * in the space where the part time employee was.
		 	 * 
		 	 * @param e A PartTimeEmployee object.
		 	 * 
		 	 * @throws IOException If the file is not found it throws this exception.
		 	 * */
		 	public void overwrite_employee(PartTimeEmployee e) throws IOException {
		 		ArrayList<String> temp=new ArrayList<String>();
		 			line1 = new Scanner(new File("../group/src/Parttime employees.csv"));
		 			temp.add(line1.nextLine());
		 			while(line1.hasNext()) {
		 				String i=line1.nextLine();
		 				String[] s=i.split(",");
		 				if((s[0].toUpperCase()).equals(e.getName().toUpperCase())) {
		 				temp.add(e.toString());
		 				}
		 			
		 				else {
		 					
		 					temp.add(i);}
		 			}
		 			PrintWriter clear=new PrintWriter("../group/src/Parttime employees.csv");
		 			for(String x:temp) {
		 				PrintWriter writer=new PrintWriter(new FileWriter("../group/src/Parttime employees.csv",true));
		 				writer.println(x.toUpperCase());
		 				writer.close();
		 			}
		 			}
		 	/**
		 	 * Adds an employee to the file either in part time or full time based on the csv file you input.
		 	 * @param employeeinformation employee information to be input to this csv file.
		 	 * @param csvlistname csv file name
		 	 * @throws IOException If file is not found*/
		 	public void writetolistofusers(String employeeinformation,String csvlistname) throws IOException {
		 		//find a new line
		 		w=new PrintWriter(new FileWriter("../group/src/"+csvlistname+".csv",true));
		 		
		 		w.println(employeeinformation);
		 		w.close();
		 		}
		 	/** finds the employee name in the csv file chosen and returns that value
		 	 * 
		 	 * @param name The name of the employee.
		 	 * 
		 	 * @param csvname The name of the csv file.
		 	 *
		 	 * @return String employee information
		 	 * @throws FileNotFoundException If csv file is not found it will throw this exception.
		 	 * */
		 	public String searchemployees(String name,String csvname) throws FileNotFoundException {
		 		line1 = new Scanner(new File("../group/src/"+csvname+".csv"));
		 		String r="";
		 		name=name.toUpperCase();
		 		while(line1.hasNext()) {
		 			String find=line1.nextLine();
		 			String[] results=find.split(",");
		 			if (results[0].equals(name)){
		 				r=find;
		 			}
		 		}
		 		return r;
		 	}
		 	/**	
		 	 * searches the notifs csv file and sees if that name is in it if it is it returns it.
		 	 * @param name The name of the employee 
		 	 * @return String notif information
		 	 * @throws FileNotFoundException If no file found this exception is thrown. */
		 	public String searchnotif(String name) throws FileNotFoundException {
		 		line1 = new Scanner(new File("../group/src/notifs.csv"));
		 		String r="";
		 		while(line1.hasNext()) {
		 			String find=line1.nextLine();
		 			String[] results=find.split(",");
		 			if (results[0].equals(name)){
		 				r+=results[0]+","+results[1];
		 			}
		 		}
		 		return r;
		 	}
		 	/**
		 	 * Creates a notification that an employee has been promote.
		 	 * @param name name of employee
		 	 * @param notif notification to be input into the csv file
		 	 * @throws IOException If the file is not found.
		 	 * */
		 	public void createnotif(String name,String notif) throws IOException {
		 		//find a new line
		 		w=new PrintWriter(new FileWriter("../group/src/notifs.csv",true));
		 		
		 		w.println(name+","+notif+"!");
		 		w.close();
		 		}
		 	/**
		 	 * Deletes notification from notif.csv
		 	 * @param name This is the employee name that you want delete.
		 	 * @throws IOException If the file is not found.
		 	 * */
		 	public void adressednotif(String name) throws IOException {
		 		//find a new line
		 		ArrayList<String> temp=new ArrayList<String>();
		 		line1 = new Scanner(new File("../group/src/notifs.csv"));
		 		temp.add(line1.nextLine());
		 		while(line1.hasNext()) {
		 			String i=line1.nextLine();
		 			String[] s=i.split(",");
		 			if((s[0].toUpperCase()).equals(name.toUpperCase())) {
		 			
		 			}
		 			else {
		 				
		 				temp.add(i);}
		 		}
		 		PrintWriter clear=new PrintWriter("../group/src/notifs.csv");
		 		for(String x:temp) {
		 			PrintWriter writer=new PrintWriter(new FileWriter("../group/src/notifs.csv",true));
		 			writer.println(x.toUpperCase());
		 			writer.close();
		 		}
		 		}
		 	/**Searches payscale.csv for the job categories and adds them to an Array list
		 	 * @return A List of job categories
		 	* @throws FileNotFoundException If the file is not found.
		 	 * */
		 	public ArrayList<String> getchoicesinjc() throws FileNotFoundException{
		 			line1 = new Scanner(new File("src/payscale.csv"));
		 			ArrayList<String> choices=new ArrayList<String>();
		 			while(line1.hasNext()) {
		 				String input=line1.nextLine();
		 				if(input.split(",").length==1) {
		 					choices.add(input);
		 				}
		 					
		 			}
		 			return choices;
		 			//method end
			}
		 	/**Searches payscale.csv for the job categories and adds them to a list
		 	 * @return A List of job categories.
		 	 * @throws FileNotFoundException If the file is not found.

		 	 * */
		 	public ArrayList<String> ptgetchoicesinjc() throws FileNotFoundException{
		 		line1 = new Scanner(new File("src/payscale for partime.csv"));
		 		ArrayList<String> choices=new ArrayList<String>();
		 		while(line1.hasNext()) {
		 			String input=line1.nextLine();
		 			if(input.split(",").length==1) {
		 				choices.add(input);
		 			}
		 				
		 		}
		 		return choices;
		 		//method end
		 	}

		 	/**
		 	 * Searches pay scale for partime.csv for the job category 
		 	 * and adds all the job titles in the job category to the array list
		 	 * @param jc The job category
		 	 * @return List of job titles
		 	 * @throws FileNotFoundException If the file is not found.
*/
		 	public ArrayList<String> getchoicesinjtwithjc(String jc) throws FileNotFoundException{
		 		line1 = new Scanner(new File("../group/src/payscale.csv"));
		 		ArrayList<String> choices=new ArrayList<String>();
		 		while(line1.hasNext()) {
		 			String input=line1.nextLine();
		 			if(input.split(",").length==1&&input.equals(jc)) {
		 			
		 				input=line1.nextLine();
		 				//System.out.println(1+" "+input);
		 				while(input.split(",").length>1) {
		 					//System.out.println(2+" "+input);
		 					
		 					String[] S=input.split(",");
		 					if (!choices.contains(S[0])&&S.length>1) {
		 						//System.out.println(3+" "+input);
		 						choices.add(S[0]);
		 						
		 				}input=line1.nextLine();
		 			
		 				
		 		}
		 		}
		 		}
		 		return choices;
		 		//method end
		 	}
		 	/**
		 	 * Searches pay scale for pay scale for partime.csv for the job category 
		 	 * and adds all the job titles in the job category to the array list,
		 	 * @param jc The job category
		 	 * @return List of job titles in the job category.
		 	 ** @throws FileNotFoundException If the file is not found.
*/
		 	public ArrayList<String> ptgetchoicesinjtwithjc(String jc) throws FileNotFoundException{
		 		line1 = new Scanner(new File("../group/src/payscale for partime.csv"));
		 		ArrayList<String> choices=new ArrayList<String>();
		 		while(line1.hasNext()) {
		 			String input=line1.nextLine();
		 			if(input.split(",").length==1&&input.equals(jc)) {
		 			
		 				input=line.nextLine();
		 				//System.out.println(1+" "+input);
		 				while(input.split(",").length>1) {
		 					//System.out.println(2+" "+input);
		 					
		 					String[] S=input.split(",");
		 					if (!choices.contains(S[0])&&S.length>1) {
		 						//System.out.println(3+" "+input);
		 						choices.add(S[0]);
		 						
		 				}input=line1.nextLine();
		 			
		 				
		 		}
		 		}
		 		}
		 		return choices;
		 		//method end
		 	}
		 	/**
		 	 * Searches for the top point of the pay scale in the payscale.csv file.
		 	 * @param jobcatagory the job category that the job title is in.
		 	 * @param jobtitle the job title that you want to find the top pay scale point.
		 	 * @return pay scale point
		 	 * @throws FileNotFoundException Throws this if the file was not found*/
		 	public int getTopPayscale(String jobcatagory,String jobtitle) throws FileNotFoundException {
		 		line1 = new Scanner(new File("../group/src/payscale.csv"));
		 		boolean done=false;
		 		int topPayscale;
		 		//line.nextLine();
		 		while(line1.hasNext())
		 		{
		 			String input=line1.nextLine();
		 			if(input.equals(jobcatagory)) {
		 				while(line1.hasNext()&&!done) {
		 					input=line1.nextLine();
		 					String[] parts=input.split(",");
		 					if(jobtitle.equals(parts[0]) )
		 					{
		 					input=line1.nextLine();
		 					return topPayscale=Integer.parseInt(parts[1]);
		 					}
		 				
		 				}
		 				}
		 		}
		 	
		 		return -1;
		 	}		
		 	/**
		 	 * Searches for the top point of the payscale in the payscale for partime.csv file
		 	 * @param jobcatagory the job category that the job title is in.
		 	 * @param jobtitle the job title that you want to find the top pay scale point.
		 	 * @return pay scale point
		 	 * @throws FileNotFoundException This is thrown when the csv file was not found*/
		 	public int ptgetTopPayscale(String jobcatagory,String jobtitle) throws FileNotFoundException {
		 		line1 = new Scanner(new File("../group/src/payscale for partime.csv"));
		 		boolean done=false;
		 		int topPayscale;
		 		//line.nextLine();
		 		while(line1.hasNext())
		 		{
		 			String input=line1.nextLine();
		 			if(input.equals(jobcatagory)) {
		 				while(line1.hasNext()&&!done) {
		 					input=line1.nextLine();
		 					String[] parts=input.split(",");
		 					if(jobtitle.equals(parts[0]) )
		 					{
		 					input=line1.nextLine();
		 					return topPayscale=Integer.parseInt(parts[1]);
		 					}
		 				
		 				}
		 				}
		 		}
		 	
		 		return -1;
		 	}
		 	/**Checks if this employee submitted a pay claim by looking through the pay claim csv file.
		 	 * 
		 	 * @param name The name of employee you want to see is in payclaimSubmit.csv
		 	 * @return If it is in the csv file or not
		 	 *@throws FileNotFoundException This is thrown when the csv file was not found
		 	 **/
		 	public boolean inpayclaim(String name) throws FileNotFoundException {
		 		line1 = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		 		
		 		while(line1.hasNext()) {
		 			String find=line1.nextLine();
		 			String[] results=find.split(",");
		 			if (results[0].equals(name)){
		 				return true;
		 			}
		 		}
		 		
		 		return false;
		 	}
		 	/**
		 	 *Adds this employee to payclaimSubmit.csv along with the time it was submitted.
		 	 *@param name name of the employee you want to add 
		 	 * @throws IOException This is thrown when the csv file was not found
		 	 **/
		 	public void submitpayclaim(String name) throws IOException {
		 		//find a new line
		 		LocalDate timesub=LocalDate.now();
		 		w=new PrintWriter(new FileWriter("../group/src/payclaimSubmit.csv",true));
		 		
		 		w.println(name+","+timesub);
		 		w.close();
		 		}
		 	/**
		 	 *Removes this employee from payclaimSubmit.csv.
		 	 *@param name employee name that you want to remove from payclaimSubmit.
		 	 * @throws IOException This is thrown when the csv file was not found
		 	 *
		 	 **/
		 	public void payclaimdone(String name) throws IOException {
		 		//find a new line
		 		ArrayList<String> temp=new ArrayList<String>();
		 		line1 = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		 		while(line1.hasNext()) {
		 			String i=line1.nextLine();
		 			String[] s=i.split(",");
		 			if((s[0].toUpperCase()).equals(name.toUpperCase())) {
		 			
		 			}
		 			else {
		 				
		 				temp.add(i);}
		 		}
		 		PrintWriter clear=new PrintWriter("../group/src/payclaimSubmit.csv");
		 		for(String x:temp) {
		 			PrintWriter writer=new PrintWriter(new FileWriter("../group/src/payclaimSubmit.csv",true));
		 			writer.println(x.toUpperCase());
		 			writer.close();
		 		}
		 	}
		 	/**gets the pay claims form the csv files
		 	 * @return The pay claims in payclaimSubmit.csv as a list
		 	 @throws FileNotFoundException This is thrown when the csv file was not found*/
		 	public ArrayList<String> payclaimtoArrayList() throws FileNotFoundException{
		 		ArrayList<String> temp=new ArrayList<String>();
		 		line1 = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		 		while(line1.hasNext()) {
		 			String i=line1.nextLine();
		 				temp.add(i);
		 				
		 				}
		 		return temp;
		 		}
		 	/**
		 	 * Finds this employee in Parttime employees.csv.
		 	 * @return Employee information
		 	 * @param name Employee name
		 	 @throws FileNotFoundException This is thrown when the csv file was not found*/
		 	public String SearchEmployee(String name) throws FileNotFoundException{
		 		
		 		line1 = new Scanner(new File("../group/src/Parttime employees.csv"));
		 		while(line1.hasNext()) {
		 			String i=line1.nextLine();
		 			String[] s=i.split(",");
		 			if((s[0].toUpperCase()).equals(name.toUpperCase())) {
		 			return i;
		 				
		 				}
		 		
		 		}
		 		return "not found";}
		 	/**
		 	 * Adds a payslip to payslip.csv with a timestamp.
		 	 * @param name Employee name
		 	 * @param info Payslip information
		 	 * 		 	 @throws FileNotFoundException This is thrown when the csv file was not found*/

		 	  
		 	public void addtopayslip(String name,String info) throws IOException {
		 		LocalDate timeadded=LocalDate.now();
		 		w=new PrintWriter(new FileWriter("../group/src/payslip.csv",true));
		 		
		 		w.println(name+","+timeadded+","+info);
		 		w.close();
		 	}
		 	/**
		 	 * Gets this employee's pay slip at this date.
		 	 * @param name Employee name
		 	 * @param inputdate Payslip input date
		 	 * @return the payslip of this employee at this date
		 	 *  @throws FileNotFoundException This is thrown when the csv file was not found*/
		 	public String seepayslip(String name, String inputdate) throws FileNotFoundException {
		 	line1 = new Scanner(new File("../group/src/payslip.csv"));
		 	String ret="";
		 	while(line1.hasNext()) {
		 		String i=line1.nextLine();
		 		String[] s=i.split(",");
		 		if((s[0].toUpperCase()).equals(name.toUpperCase())&&s[1].equals(inputdate)) {
		 			
		 			return s[2];
		 			}
		 		
		 	}
		 	return ret;
		 	}/**Finds the dates of the pay slips for this employee.
		 		@param Name Employee name
		 		@return the dates of this employees pay slips
		 		 @throws FileNotFoundException This is thrown when the csv file was not found*/

		 		
		 	public ArrayList<String> getchoicesofpayslip(String Name) throws FileNotFoundException{
		 		line1 = new Scanner(new File("../group/src/payslip.csv"));
		 		ArrayList<String> choices=new ArrayList<String>();
		 		line1.hasNext();
		 		while(line1.hasNext()) {
		 			String input=line1.nextLine();
		 			String[] inputsplit=input.split(",");
		 			if(inputsplit[0].equals(Name.toUpperCase())&&!(choices.contains(inputsplit[1]))) {
		 				choices.add(inputsplit[1]);
		 			}
		 				
		 		}
		 		return choices;
		 		//method end
		 	}
		 		
		 	
		 //end
		 

/**Uses the run method and runs the project.
  @param args an array of strings that is used to pass command line arguments to a Java application
 * @throws IOException if the files were not found */

public static void main(String []args) throws IOException {
/*The application has three user types.
 *  An employee can log in to the system, see their details, and view their most recent or historical payslips.
 *  An admin user can log in and can add a new employee to the system. 
 *  A human resources user can log in to implement the promotion functionality for full-time staff. 
 *  In such cases the staff member should be asked to confirm the promotional changes being applied. 
 *  A command line interface (CLI) should be included to facilitate this interaction between users and the application. 
 *  The CLI should be included with the expectation that a Graphical User Interface (GUI) may be required in the future. 
 *  In other words the separation between the text-based user interface should be well-defined to allow a graphical user interface to be substituted easily. 
*/
	//check user type
	cli login = new cli();
    login.run();
	}
}


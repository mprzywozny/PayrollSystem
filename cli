import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class cli {
		private String usertype;
		private Scanner in;
		private Scanner userfind;
		private Scanner line;
		private employee e;
		private LocalDate Today;
		static String[] usertypes= {"employee","human resources","admin"};
		static Overwrite o=new Overwrite();
		static Deductions d;
		static Payslip payslip;
		 public cli()
		   {
		      in = new Scanner(System.in);
		   }
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
   				if(!(o.payclaimtoArrayList().isEmpty())) {
   				ArrayList<String> payclaims=o.payclaimtoArrayList();//payclaims list of names submitted payclaimes
   				
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
   					    String employeedetales=o.SearchEmployee(check[0]);
   					    String[] eds=employeedetales.split(",");
   					    
   					    //NAME,JOBCATAGORY,JOBTYPE,SCALEPOINT,HOURLYRATE,HOURSDONE
   					    double HOURLYRATE=Double.parseDouble(eds[4]);
   					    
   					    double HOURSDONE=Double.parseDouble(eds[5]);
   					    d=new Deductions(check[0],HOURSDONE*HOURLYRATE);
   					    //System.out.println(d.toString());
   					    //String employeeName, String jobTitle, int scalePoint, double salary, double deductions,double grossSalary
   					    String netpay=Double.toString(HOURSDONE*HOURLYRATE);
   					    payslip=new Payslip(eds[0],eds[2],eds[3],(HOURSDONE*HOURLYRATE),d.deductionTotal(),d.getGrossSalary());
   					    o.addtopayslip(check[0],payslip.toString());
   					    o.payclaimdone(check[0]);
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
   					    o.addtopayslip(s[0],payslip.toString());
   					  
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
		     			String c=o.searchnotif(Name.toUpperCase());
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
		     				
		     				o.adressednotif(l[0]);
		     				}
		     			}
		     			}
		     			//see details
		     			System.out.println("Are you p)artime or f)ulltime");
		     			answer = in.nextLine();
		     			String info="";
		     			if(answer.toUpperCase().equals("P")) {
		     				info=o.searchemployees(Name,"Parttime employees");
		     				String[] format=info.split(",");
		     				
		     				System.out.printf("name: %s\nJob title: %s\nScale point: %s\nHourly rate: %s\n", format[0], format[2], format[3], format[4]);
		     				//Part-time employees must submit a pay claim form by the second Friday of a month in order to be paid that month.  
		     				//if not already
		     				if(!o.inpayclaim(Name)){
		     				System.out.println("S)ubmit payclaim form?(Y/N) has to be done by the second friday of the month.");
		     				String Submit = in.nextLine();
		     					if(Submit.toUpperCase().equals("Y")) {
		     						//add to list
		     						o.submitpayclaim(Name);
		     					}
		     				}
		     				else{
		     					System.out.println("payclaim form submitted");
		     				}
		     			}
		     			else if(answer.toUpperCase().equals("F")) {
		     				info=o.searchemployees(Name,"employees");
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
		     				
		     				if(!(o.getchoicesofpayslip(Name).isEmpty())){
		     				ArrayList <String> choices=o.getchoicesofpayslip(Name);
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
		     				String ps=o.seepayslip(Name,inputdate);
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
			     					o.createnotif(format[0],change);
			     					o.overwrite_employee((FullTimeEmployee)e);
					     			}
			     				
					     			
			     				
			     					
			     			else if(abq.toUpperCase().equals("B")) {
		     					//name,jobcatagory,jobtitle,scalepoint
			     				((FullTimeEmployee)e).moveupincatagory();
		     					String[] format=e.toString().split(",");
		     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
		     					String change="You have been moved up to the next job catagory";
		     					o.createnotif(format[0],change);
		     					o.overwrite_employee((FullTimeEmployee)e);
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
			     					o.createnotif(format[0],change);
			     					o.overwrite_employee((PartTimeEmployee)e);
					     			}
			     			
					     			
			     				
			     					
			     			else if(abq.toUpperCase().equals("B")) {
		     					//name,jobcatagory,jobtitle,scalepoint
			     				((PartTimeEmployee) e).moveupincatagory();
		     					String[] format=((PartTimeEmployee) e).toString().split(",");
		     					System.out.println(format[0]+" is a "+format[2]+" at scale point "+format[3]+"");
		     					String change="You have been moved up to the next job catagory";
		     					o.createnotif(format[0],change);
		     					o.overwrite_employee((PartTimeEmployee)e);
		     				
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
			     				ArrayList <String> choices=o.getchoicesinjc();
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
			     				ArrayList <String> choicesofjt=o.getchoicesinjtwithjc(jobCategory);
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
			     		        	while(payscalepoint<0 || payscalepoint>o.getTopPayscale(jobCategory, jobTitle)) {
			     		        	 System.out.printf("Select point on payscale equal to or below %d: \n",o.getTopPayscale(jobCategory, jobTitle));
			     		        	 payscalepoint = in.nextInt();}
			     		        	//info gathered
			     		        	//System.out.printf("jc %s jt %s psp %d\n",jobCategory,jobTitle,payscalepoint);
			     		        	user u=new user(Name,"employee");			     		 
			     		        	FullTimeEmployee f=new FullTimeEmployee(Name,jobCategory,jobTitle,payscalepoint);
			     		        	o.writetolistofusers(f.toString(),"employees");
			     		        	
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
			     				ArrayList <String> choices=o.ptgetchoicesinjc();
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
			     				ArrayList <String> choicesofjt=o.ptgetchoicesinjtwithjc(jobCategory);
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
			     		        	while(payscalepoint<0 || payscalepoint>o.ptgetTopPayscale(jobCategory, jobTitle)) {
			     		        	 System.out.printf("Select point on payscale equal to or below %d: \n",o.ptgetTopPayscale(jobCategory, jobTitle));
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
			     		        	o.writetolistofusers(f.toString(),"Parttime employees");
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


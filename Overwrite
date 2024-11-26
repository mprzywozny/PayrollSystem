import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Overwrite {
	//misc methods
	private Scanner line;
	private PrintWriter w;
	public void overwrite_employee(FullTimeEmployee e) throws IOException {
	ArrayList<String> temp=new ArrayList<String>();
		line = new Scanner(new File("../group/src/employees.csv"));
		temp.add(line.nextLine());
		while(line.hasNext()) {
			String i=line.nextLine();
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
	public void overwrite_employee(PartTimeEmployee e) throws IOException {
		ArrayList<String> temp=new ArrayList<String>();
			line = new Scanner(new File("../group/src/Parttime employees.csv"));
			temp.add(line.nextLine());
			while(line.hasNext()) {
				String i=line.nextLine();
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
	/**adds an employee to the file either in parttime or fulltime*/
	public void writetolistofusers(String x,String csvlistname) throws IOException {
		//find a new line
		w=new PrintWriter(new FileWriter("../group/src/"+csvlistname+".csv",true));
		
		w.println(x);
		w.close();
		}
	public String searchemployees(String name,String csvname) throws FileNotFoundException {
		line = new Scanner(new File("../group/src/"+csvname+".csv"));
		String r="";
		name=name.toUpperCase();
		while(line.hasNext()) {
			String find=line.nextLine();
			String[] results=find.split(",");
			if (results[0].equals(name)){
				r=find;
			}
		}
		return r;
	}
	
	public String searchnotif(String name) throws FileNotFoundException {
		line = new Scanner(new File("../group/src/notifs.csv"));
		String r="";
		while(line.hasNext()) {
			String find=line.nextLine();
			String[] results=find.split(",");
			if (results[0].equals(name)){
				r+=results[0]+","+results[1];
			}
		}
		return r;
	}
	/**used in hr to mark a change */
	public void createnotif(String name,String notif) throws IOException {
		//find a new line
		w=new PrintWriter(new FileWriter("../group/src/notifs.csv",true));
		
		w.println(name+","+notif+"!");
		w.close();
		}
	/**used in employee*/
	public void adressednotif(String name) throws IOException {
		//find a new line
		ArrayList<String> temp=new ArrayList<String>();
		line = new Scanner(new File("../group/src/notifs.csv"));
		temp.add(line.nextLine());
		while(line.hasNext()) {
			String i=line.nextLine();
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
	//get choices of jc
	public ArrayList<String> getchoicesinjc() throws FileNotFoundException{
			line = new Scanner(new File("../group/src/payscale.csv"));
			ArrayList<String> choices=new ArrayList<String>();
			while(line.hasNext()) {
				String input=line.nextLine();
				if(input.split(",").length==1) {
					choices.add(input);
				}
					
			}
			return choices;
			//method end
		}
	public ArrayList<String> ptgetchoicesinjc() throws FileNotFoundException{
		line = new Scanner(new File("../group/src/payscale for partime.csv"));
		ArrayList<String> choices=new ArrayList<String>();
		while(line.hasNext()) {
			String input=line.nextLine();
			if(input.split(",").length==1) {
				choices.add(input);
			}
				
		}
		return choices;
		//method end
	}

	
	public ArrayList<String> getchoicesinjtwithjc(String jc) throws FileNotFoundException{
		line = new Scanner(new File("../group/src/payscale.csv"));
		ArrayList<String> choices=new ArrayList<String>();
		while(line.hasNext()) {
			String input=line.nextLine();
			if(input.split(",").length==1&&input.equals(jc)) {
			
				input=line.nextLine();
				//System.out.println(1+" "+input);
				while(input.split(",").length>1) {
					//System.out.println(2+" "+input);
					
					String[] S=input.split(",");
					if (!choices.contains(S[0])&&S.length>1) {
						//System.out.println(3+" "+input);
						choices.add(S[0]);
						
				}input=line.nextLine();
			
				
		}
		}
		}
		return choices;
		//method end
	}
	public ArrayList<String> ptgetchoicesinjtwithjc(String jc) throws FileNotFoundException{
		line = new Scanner(new File("../group/src/payscale for partime.csv"));
		ArrayList<String> choices=new ArrayList<String>();
		while(line.hasNext()) {
			String input=line.nextLine();
			if(input.split(",").length==1&&input.equals(jc)) {
			
				input=line.nextLine();
				//System.out.println(1+" "+input);
				while(input.split(",").length>1) {
					//System.out.println(2+" "+input);
					
					String[] S=input.split(",");
					if (!choices.contains(S[0])&&S.length>1) {
						//System.out.println(3+" "+input);
						choices.add(S[0]);
						
				}input=line.nextLine();
			
				
		}
		}
		}
		return choices;
		//method end
	}
	
	public int getTopPayscale(String jobcatagory,String jobtitle) throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payscale.csv"));
		boolean done=false;
		int topPayscale;
		//line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			if(input.equals(jobcatagory)) {
				while(line.hasNext()&&!done) {
					input=line.nextLine();
					String[] parts=input.split(",");
					if(jobtitle.equals(parts[0]) )
					{
					input=line.nextLine();
					return topPayscale=Integer.parseInt(parts[1]);
					}
				
				}
				}
		}
	
		return -1;
	}		
	
	public int ptgetTopPayscale(String jobcatagory,String jobtitle) throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payscale for partime.csv"));
		boolean done=false;
		int topPayscale;
		//line.nextLine();
		while(line.hasNext())
		{
			String input=line.nextLine();
			if(input.equals(jobcatagory)) {
				while(line.hasNext()&&!done) {
					input=line.nextLine();
					String[] parts=input.split(",");
					if(jobtitle.equals(parts[0]) )
					{
					input=line.nextLine();
					return topPayscale=Integer.parseInt(parts[1]);
					}
				
				}
				}
		}
	
		return -1;
	}
	
	public boolean inpayclaim(String name) throws FileNotFoundException {
		line = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		
		while(line.hasNext()) {
			String find=line.nextLine();
			String[] results=find.split(",");
			if (results[0].equals(name)){
				return true;
			}
		}
		
		return false;
	}
	public void submitpayclaim(String name) throws IOException {
		//find a new line
		LocalDate timesub=LocalDate.now();
		w=new PrintWriter(new FileWriter("../group/src/payclaimSubmit.csv",true));
		
		w.println(name+","+timesub);
		w.close();
		}
	
	public void payclaimdone(String name) throws IOException {
		//find a new line
		ArrayList<String> temp=new ArrayList<String>();
		line = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		while(line.hasNext()) {
			String i=line.nextLine();
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
	public ArrayList<String> payclaimtoArrayList() throws FileNotFoundException{
		ArrayList<String> temp=new ArrayList<String>();
		line = new Scanner(new File("../group/src/payclaimSubmit.csv"));
		while(line.hasNext()) {
			String i=line.nextLine();
				temp.add(i);
				
				}
		return temp;
		}
	
	public String SearchEmployee(String name) throws FileNotFoundException{
		
		line = new Scanner(new File("../group/src/Parttime employees.csv"));
		while(line.hasNext()) {
			String i=line.nextLine();
			String[] s=i.split(",");
			if((s[0].toUpperCase()).equals(name.toUpperCase())) {
			return i;
				
				}
		
		}
		return "not found";}
	
	public void addtopayslip(String name,String info) throws IOException {
		LocalDate timeadded=LocalDate.now();
		w=new PrintWriter(new FileWriter("../group/src/payslip.csv",true));
		
		w.println(name+","+timeadded+","+info);
		w.close();
	}
	public String seepayslip(String name, String inputdate) throws FileNotFoundException {
	line = new Scanner(new File("../group/src/payslip.csv"));
	String ret="";
	while(line.hasNext()) {
		String i=line.nextLine();
		String[] s=i.split(",");
		if((s[0].toUpperCase()).equals(name.toUpperCase())&&s[1].equals(inputdate)) {
			
			return s[2];
			}
		
	}
	return ret;
	}
	public ArrayList<String> getchoicesofpayslip(String Name) throws FileNotFoundException{
		line = new Scanner(new File("../group/src/payslip.csv"));
		ArrayList<String> choices=new ArrayList<String>();
		line.hasNext();
		while(line.hasNext()) {
			String input=line.nextLine();
			String[] inputsplit=input.split(",");
			if(inputsplit[0].equals(Name.toUpperCase())&&!(choices.contains(inputsplit[1]))) {
				choices.add(inputsplit[1]);
			}
				
		}
		return choices;
		//method end
	}
	public void Loghours(String name) {
		
	}
				
	
//end
}



package display;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import persistence.FileRead;

import util.ValidateDate;

import data.CombinedFlight;

/**
 *
 * @author AirJ
 */

public class StdDisplayManager implements Display{

    String source,file1,file2,date;
    int passenger,d,mon;
    String day,m,y;
    
    public StdDisplayManager(String file1,String file2) {
        this.file1=file1;
        this.file2=file2;
    }
    
    @Override
    public void displaySearchPage(String file1,String file2) {
        int flag=0,val=0;
        String choice="n";
        Scanner sc=new Scanner(System.in);
        System.out.println("\t\tAirJ  The simpler,smarter and easier way of booking flights.\n");
        System.out.println("\t\tFly anywhere with us. Book flights in 3 simple steps.");
        System.out.println("===========================================================================================");
        
        do {         
        	System.out.println("Enter Source City:(DELHI/MUMBAI/PUNE)");
        	source=sc.next();
        	source=source.toUpperCase();
        	
        	//***********************************************
        	 //PassCount needs to be input
        	//***********************************************
        	
        	if (source.equalsIgnoreCase("DELHI")==false && source.equalsIgnoreCase("MUMBAI")==false && source.equalsIgnoreCase("PUNE")==false) {
        		System.out.println("Invalid input."); 
        		continue;
        	}
        	System.out.println("Destination City is SINGAPORE.");
        	try {
        		System.out.println("Enter no.of passengers:");
        		passenger=sc.nextInt();
        		if(passenger<=0 || passenger>10) {
        			System.out.println("Invalid input.");                
        			continue;
        		}        
        		System.out.println("Enter day:");
        		day=sc.next();
        		System.out.println("Enter month:");
        		m=sc.next();
        		System.out.println("Enter year:");
        		y=sc.next();
        		ValidateDate vd=new ValidateDate(day,m,y,this.file1,this.file2);
        		val=vd.validateDate();
        		mon=vd.checkMonth();
        		if(val==1)
        			d=vd.checkDay(Integer.parseInt(day),mon); 
        		else
        		 {                
        			System.out.println("Invalid input."); 
        			continue;
        			
        		}
        		
        			
        		  
        	}
        	catch(InputMismatchException exception) {                      
                      System.out.println("This is not an integer");
                      continue;
            }        
        	System.out.println("You have entered: ");
        	System.out.println("Source City: " + source);
        	System.out.println("Date: " + day + "/"+ m+"/"+y);
        	System.out.println("No of tickets: " + passenger);
        	System.out.println("Press 'Y' to Search Flights. Press any key to continue again:");
        	choice=sc.next();        
        	if(choice.equalsIgnoreCase("y")==true) {
        		
        		displayDisplayPage(file1,file2);
        	}
        	else
        		System.out.println("----------------------------------------------------------------------------------------");
        }while(choice.equalsIgnoreCase("y")==false);
    }

    @Override
    public void displayDisplayPage(String file1,String file2) {
        int i,ch,flag=0;
        String choice="y";
        CombinedFlight temp=null;
        Scanner sc = new Scanner(System.in);
        
        //***********************************************
    	 //PassCount needs to be input
    	//***********************************************
        date=day+"/"+mon;
    	CombinedFlight cf = new  CombinedFlight(source,d,date, passenger,file1,file2);
        CombinedFlight filteredFlight[]=new CombinedFlight[cf.flightCount];
        filteredFlight=cf.combine(source,d, passenger);
        System.out.println("\n\nShowing available flights...");
        System.out.println("===========================================================================================");
        System.out.println("Total number of available flights: " + cf.flightCount);
        
        if(cf.flightCount==0) {
            System.out.println("Press 'E' to exit. Press any key to search another flight");
                choice=sc.next();
                if(choice.equalsIgnoreCase("E")==true) {
                	System.out.println("Goodbye,Please visit us again");               
                	//System.exit(0);
                }
                else
                	displaySearchPage(file1,file2);
        }
            
        for(i=0;i<cf.flightCount;i++) {
            System.out.print(i+1 + ": ");
            System.out.print(filteredFlight[i].getDeptSpice()+"\t");
            System.out.print(filteredFlight[i].getSpiceFlightNo()+"\t");
            System.out.println(filteredFlight[i].getArrSpice());
            System.out.print("\t\tHalt At: " +filteredFlight[i].getIntermediate());
            System.out.println("\t Total duration: " +filteredFlight[i].getDuration());           
            System.out.print("   "+filteredFlight[i].getDeptSilk());
            System.out.print("\t"+filteredFlight[i].getSilkFlightNo());
            System.out.println("\t"+filteredFlight[i].getArrSilk());
            System.out.println("\t\t"+filteredFlight[i].getVia());            
            System.out.println();
        }
        do {
            if(cf.flightCount==1)
                System.out.println("\nSelect choice (1):");
            else
            	System.out.println("\nSelect choice ("+ 1 +" to "+cf.flightCount +"):");
            try {
                ch=sc.nextInt();
                if(ch<1 || ch>cf.flightCount) {
                    System.out.println("Invalid Entry");
                    continue;
                }                   
            }
            catch(InputMismatchException exception) {                     
                System.out.println("This is not an integer");
                continue;
            }
            
            try {
            	temp=filteredFlight[ch-1];
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Error occurred");
            }
            System.out.println("You have selected: "+ch+"  Continue?");
            System.out.println("Press 'Y' to proceed. Press 'S ' to search flight. Press any key to select again: ");
            choice=sc.next();
            
            if(choice.equalsIgnoreCase("s")==true) {
                flag=1;
                break;
            }
            if(choice.equalsIgnoreCase("y")==true) {
               displayBookingPage(temp);
            }
        }while(choice.equalsIgnoreCase("y")==false);
        
        if(flag==1)
            displaySearchPage(file1,file2);
    }

    @Override
    public void displayBookingPage(CombinedFlight temp) {
            
            String name,email,choice;
            System.out.println("Your selection");
            System.out.println("===========================================================================================");
            System.out.print(temp.getDeptSpice()+"\t");
            System.out.print(temp.getSpiceFlightNo()+"\t");
            System.out.println(temp.getArrSpice());
            System.out.print("\t\tHalt At: " +temp.getIntermediate());
            System.out.println("\t Total duration: " +temp.getDuration());
           
            System.out.print(temp.getDeptSilk());
            System.out.print("\t"+temp.getSilkFlightNo());
            System.out.println("\t"+temp.getArrSilk());
            System.out.println("\t\t"+temp.getVia());
            
            System.out.println();
            
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter name:");
            name=sc.next();
            System.out.println("Enter email-id:");
            email=sc.next();
            
            System.out.println("Press 'Y' to proceed. Press any key to search another flight.");
            choice=sc.next();
            
            if(choice.equalsIgnoreCase("y")==true) {
            	FileRead fr=new FileRead();
            	fr.saveBooking(temp, date, passenger);
                System.out.println("You have successfully booked with us.Thank You! Happy Journey!");
                System.out.println("Press 'E' to exit. Press any key to search another flight.");
                choice=sc.next();
                if(choice.equalsIgnoreCase("E")==true) {
                	System.out.println("Goodbye! Please visit us again.");
                }
                else
                	displaySearchPage(file1,file2);             
            }
            else {
                displaySearchPage(file1,file2);
            }       
    }

    
    
   
}

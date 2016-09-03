import display.*;

/**
 *
 * @author AirJ
 */

public class FlightManager {
     public static void main(String argsv[]) { 
    	 if((argsv[0].equals("spicejet.Schedule.csv") && argsv[1].equals("silkair.Schedule.csv")) || (argsv[0].equals("spicejet.Schedule1.csv") && argsv[1].equals("silkair.Schedule1.csv"))) {
	    	 if (argsv.length==3) {
	        	 if(argsv[2].equals("swing")==true) {
	            	    DisplayManager dm = new DisplayManager(argsv[0],argsv[1]);
	                    dm.displaySearchPage(argsv[0],argsv[1]);
	        	 }
	        	 else {
	        		  System.out.println(argsv[2]+" display is not implemented.Reverting to terminal display");
	        	 	  StdDisplayManager sdm=new StdDisplayManager(argsv[0],argsv[1]);
	                  sdm.displaySearchPage(argsv[0],argsv[1]); 
	        	 }
	         }
	         else if(argsv.length==2) {
	         	     StdDisplayManager sdm=new StdDisplayManager(argsv[0],argsv[1]);
	                 sdm.displaySearchPage(argsv[0],argsv[1]); 
	         }	         
    	 }
    	 else if((argsv[0].equalsIgnoreCase("spicejet.Schedule.csv") && argsv[1].equalsIgnoreCase("silkair.Schedule1.csv")) || (argsv[0].equalsIgnoreCase("spicejet.Schedule1.csv") && argsv[1].equalsIgnoreCase("silkair.Schedule.csv")))
    		 System.out.println("Incompatible dates! Please check input data!");
    	 else
	         System.out.println("Invalid Entry! Please check input data!");
    }
}

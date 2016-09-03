package display;

import data.CombinedFlight;

/**
 *
 * @author AirJ
 */

public class DisplayManager implements Display{

    SearchPage sm,objsearch;
    DisplayPage objdisplay;
    String file1,file2;
   
    public DisplayManager(String file1,String file2) {
    	this.file1=file1;
    	this.file2=file2;
        
    }
    public DisplayManager(Object obj) {
    	
        objsearch=(SearchPage)obj;
    }
    public DisplayManager(Object obj1,Object obj2) {
        objsearch=(SearchPage)obj2;
        objdisplay=(DisplayPage)obj1;
    }
   
    @Override
    public void displaySearchPage(String file1,String file2) {
     sm=new SearchPage(file1,file2);      
    }

    @Override
    public void displayDisplayPage(String file1,String file2) {        
        new DisplayPage(objsearch,file1,file2);
    }

    @Override
    public void displayBookingPage(CombinedFlight cf) {
        new BookingPage(objdisplay,objsearch);
    }

   
    
}

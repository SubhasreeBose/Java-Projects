package display;

import data.CombinedFlight;

/**
 *
 * @author AirJ
 */

public interface Display {
    public void displaySearchPage(String file1,String file2);
    public void displayDisplayPage(String file1,String file2);
    public void displayBookingPage(CombinedFlight cf);
    
}

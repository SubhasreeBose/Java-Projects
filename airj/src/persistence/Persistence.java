package persistence;

import data.*;
/**
 *
 * @author AirJ
 */

public interface Persistence {
    public Flight[] readSilkAirFile(String file);
    public Flight[] readSpiceJetFile(String file);
    public Flight[] readBooking(Flight[] flights, int size, String date, int passCount);
    public void saveBooking(CombinedFlight cf, String date, int passCount);
}

package persistence;
/**
 *
 * @author AirJ
 */
import java.util.*;

import data.*;

public class SpiceJetSchedule extends Flight {
    
    public Flight flights[];
    public int size;
    
    public SpiceJetSchedule(String file) {
        FileRead fr = new FileRead();
        flights = fr.readSpiceJetFile(file);
        size = fr.spiceSize();
    }
    
    public void getBookedFilghts(String date, int passCount) {
    	FileRead fr = new FileRead();
    	flights = fr.readBooking(flights, size, date, passCount);
    	size = fr.size;
    }
    
    public int getHr(int i, int j) {
        if(j == 1) {
            String arrive = flights[i].getArrTime();
            StringTokenizer part = new StringTokenizer(arrive, ":");
            String str = part.nextToken();
            int arr = Integer.parseInt(str);
            if(arrive.contains("PM"))
                arr += 12;
            return arr;
        }
        else {
            String depart = flights[i].getDepTime();
            StringTokenizer part = new StringTokenizer(depart, ":");
            String str = part.nextToken();
            int dep = Integer.parseInt(str);
            if(depart.contains("PM"))
                dep += 12;
            return dep;
        }
    }
    
    public int getMin(int i, int j) {
        if(j == 1) {
            String arrive = flights[i].getArrTime();
            StringTokenizer part = new StringTokenizer(arrive, ": ");
            String str = part.nextToken();
            str = part.nextToken();
            return (Integer.parseInt(str));
        }
        else {
            String depart = flights[i].getDepTime();
            StringTokenizer part = new StringTokenizer(depart, ": ");
            String str = part.nextToken();
            str = part.nextToken();
            return (Integer.parseInt(str));
        }
    }
    
    public void display() {
    	int j,k;
    	System.out.println("\nOrigin:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getSource());

        System.out.println("\nDestination:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getDestination());

        System.out.println("\nFrequency:\n");
        for(j=0; j<size; j++){
            for(k=0; k<8; k++)
                System.out.print(flights[j].getFrequency(k)+"\t");
            System.out.println();
        }

        System.out.println("\nFlight No.:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getFlightNo());

        System.out.println("\nDeparture:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getDepTime());

        System.out.println("\nArrival:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getArrTime());

        System.out.println("\nVia:\n");
        for(j=0; j<size; j++)
            System.out.println(flights[j].getVia());
     }
}
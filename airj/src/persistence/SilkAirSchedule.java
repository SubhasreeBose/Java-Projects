package persistence;

import data.*;
/**
 *
 * @author AirJ
 */
    
public class SilkAirSchedule extends Flight {
	   
    public Flight flights[];
    public int size;

    public SilkAirSchedule(String file) {
        FileRead fr = new FileRead();
        size = fr.silkSize(file);
        flights = fr.readSilkAirFile(file);       
    }
    
    public void getBookedFilghts(String date, int passCount) {
    	FileRead fr = new FileRead();
    	flights = fr.readBooking(flights, size, date, passCount);
    	size = fr.size;
    }
    
    public int getHr(int i, int j) {
        if(j == 0) {
            String depart = flights[i].getDepTime();
            int dep = Integer.parseInt(depart.substring(0, 2));
            return dep;
        }
        else {
            String arrive = flights[i].getArrTime();
            int arr = Integer.parseInt(arrive.substring(0, 2));
            return arr;
        }
    }
    
    public int getMin(int i, int j) {
        if(j == 0) {
            String depart = flights[i].getDepTime();
            int dep = Integer.parseInt(depart.substring(2, 4));
            return dep;
        }
        else {
            String arrive = flights[i].getArrTime();
            int arr = Integer.parseInt(arrive.substring(2, 4));
            return arr;
        }
    }
    
    public void display() {
	   
	int j, k;
        System.out.println("\nOrigin:\n");
        for(j=0; j<size; j++)
           System.out.println(flights[j].getSource());
                
       
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
    }
}
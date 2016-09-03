package persistence;
/**
 *
 * @author AirJ
 */

import data.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileRead implements Persistence {
    
    Flight flights[];
    int size;
    
    public FileRead() {
    	flights = null;
    	size = 0;
    }
    
    @Override
    public Flight[] readSilkAirFile(String file) {
        FileReader reader = null;
        BufferedReader br = null;
        String str; 
        int i = 0;
        try {
           reader = new FileReader("Files/"+file);
           br = new BufferedReader(reader);
        }
        catch(FileNotFoundException e) {
            System.out.println("File is not found");
        }
        catch(NullPointerException e) {
        	System.out.println("Null value of reader!");
        }
        
        try {
            str = br.readLine();
            while(str != null) {
                i++;
                str = br.readLine();
            }
            reader.close();
            br.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        flights = new Flight[i-3];
        for(int j=0;j<i-3;j++)
                flights[j]=new Flight();

        str = null;
        i = 0;    

        try {
            reader = new FileReader("Files/"+file);
            br = new BufferedReader(reader);
            while(i<3) {
                str = br.readLine();
                i++;
            }

            str = br.readLine();
            i=0;
            String strPart1 = "", strPart2 = "", strPart3 = "", strPart4 = "";
            while(str != null) {
            	if(str.contains("\"")) {
	            	StringTokenizer data = new StringTokenizer(str,"\"");
	                strPart1 = data.nextToken();
	                strPart2 = data.nextToken();
	                strPart3 = data.nextToken();
	                if(strPart1 != null) {
	                    StringTokenizer dataPart = new StringTokenizer(strPart1, ",");
	                    flights[i].setSource(dataPart.nextToken().trim());
	                }
	                String tmp = "";
	                if(strPart2 != null){
	                    StringTokenizer dataPart = new StringTokenizer(strPart2, ",");
	                    int j = dataPart.countTokens();
	                    while(--j >= 0) {
	                        tmp = dataPart.nextToken();
	                        if(tmp.compareTo("Sun") == 0) {
	                            flights[i].setFrequency(0,true);
	                            flights[i].setFrequency(7,true);
	                        }
	                        else if(tmp.compareTo("Mon") == 0)
	                            flights[i].setFrequency(1,true); 
	                        else if(tmp.compareTo("Tue") == 0)
	                            flights[i].setFrequency(2,true);
	                        else if(tmp.compareTo("Wed") == 0)
	                            flights[i].setFrequency(3,true);
	                        else if(tmp.compareTo("Thu") == 0)
	                            flights[i].setFrequency(4,true);
	                        else if(tmp.compareTo("Fri") == 0)
	                            flights[i].setFrequency(5,true);
	                        else if(tmp.compareTo("Sat") == 0)
	                            flights[i].setFrequency(6,true);
	                    }
	                }
	                if(strPart3 != null) {
	                    StringTokenizer dataPart3 = new StringTokenizer(strPart3, ",");
	                    flights[i].setFlightNo(dataPart3.nextToken());
	                    strPart4 = dataPart3.nextToken();
	                    StringTokenizer dataPart4 = new StringTokenizer(strPart4, "/");
	                    flights[i].setDepTime(dataPart4.nextToken());
	                    flights[i].setArrTime(dataPart4.nextToken());
	                }	                
	            }
            	else {
            		StringTokenizer data = new StringTokenizer(str,",");
	                strPart1 = data.nextToken();
	                if(strPart1 != null) {
	                    flights[i].setSource(strPart1.trim());
	                }
	                strPart2 = data.nextToken();
	                if(strPart2 != null){
	                	flights[i].setFrequency(0,true);
                        flights[i].setFrequency(7,true);
	                }
	                strPart3 = data.nextToken();
	                flights[i].setFlightNo(strPart3.trim());
	                
	                strPart4 = data.nextToken();
	                if(strPart4 != null) {	                    
	                    StringTokenizer dataPart4 = new StringTokenizer(strPart4, "/");
	                    flights[i].setDepTime(dataPart4.nextToken());
	                    flights[i].setArrTime(dataPart4.nextToken());
	                }	                
            	}
            	i++;
                strPart1 = null;
                strPart2 = null;
                strPart3 = null;
                str = br.readLine();
            }
            reader.close();
            br.close();
        }
        catch (IOException e) {
           System.out.println("Cannot read the file");
        }
        finally {
           if(reader != null) {
               try {
            	   reader.close();
                   br.close();
               }
               catch (IOException e) {
                   System.out.println("Error in closing the file");
               }
           }
        }
        
        return flights;
    }
    
    int silkSize(String file) {
        FileReader reader = null;
        BufferedReader br = null;
        String str = ""; 
        int i = 0;
        try {
           reader = new FileReader("Files/"+file);
           br = new BufferedReader(reader);
        }
        catch(FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        catch(NullPointerException e) {
        	System.out.println("Null value of reader!");
        }
        
        try {
            str = br.readLine();
            while(str != null) {
                i++;
                str = br.readLine();
            }
            reader.close();
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return i-3;
    }
    
    public Flight[] readSpiceJetFile(String file) {
        FileReader reader = null;
        BufferedReader br = null;
        boolean b1;
        String eff_from[], eff_till[];
        String strPart1 = "", strPart2 = "", strPart3 = "";
        String str = "", temp = "";
        int i = 0;
        try {
           reader = new FileReader("Files/"+file);
           br = new BufferedReader(reader);
        }
        catch(FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        catch(NullPointerException e) {
        	System.out.println("Reader is null!");
        }
        
        try{
            str = br.readLine();
            while(str != null) {
                i++;
                str = br.readLine();
            }
            reader.close();
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        size = i-1;
        flights = new Flight[i-1];
        for(int j=0;j<i-1;j++)
            flights[j]=new Flight();
        eff_from = new String[i-1];
        eff_till = new String[i-1];
        i=0;
        
        try {
            reader = new FileReader("Files/"+file);
            br = new BufferedReader(reader);            
            str = br.readLine();            
            str = br.readLine();
            
            while(str != null) {
                b1 = str.contains("\"");
                if(b1){
                    StringTokenizer data = new StringTokenizer(str,"\"");
                    strPart1 = data.nextToken();
                    strPart2 = data.nextToken();
                    strPart3 = data.nextToken();
                    if(strPart1 != null){
                        StringTokenizer dataPart = new StringTokenizer(strPart1, ",");
                        flights[i].setSource(dataPart.nextToken());
                        flights[i].setDestination(dataPart.nextToken());
                    }
                    String tmp = "";
                    if(strPart2 != null){
                        StringTokenizer dataPart = new StringTokenizer(strPart2, ",");
                        int j = dataPart.countTokens();
                        while(j-- > 0) {
                            tmp = dataPart.nextToken();
                            tmp = tmp.trim();
                            if(tmp.compareTo("Sunday") == 0){
                                flights[i].setFrequency(0,true);
                                flights[i].setFrequency(7,true);
                            }
                            else if(tmp.compareTo("Monday") == 0)
                            	flights[i].setFrequency(1,true); 
                            else if(tmp.compareTo("Tueday") == 0)
                            	flights[i].setFrequency(2,true);
                            else if(tmp.compareTo("Wednesday") == 0)
                                flights[i].setFrequency(3,true);
                            else if(tmp.compareTo("Thursday") == 0)
                                flights[i].setFrequency(4,true);
                            else if(tmp.compareTo("Friday") == 0)
                                flights[i].setFrequency(5,true);
                            else if(tmp.compareTo("Saturday") == 0)
                                flights[i].setFrequency(6,true);
                        }
                        
                    }
                    if(strPart3 != null) {
                        StringTokenizer dataPart3 = new StringTokenizer(strPart3, ",");
                        flights[i].setFlightNo(dataPart3.nextToken());
                        flights[i].setDepTime(dataPart3.nextToken());
                        flights[i].setArrTime(dataPart3.nextToken());
                        flights[i].setVia(dataPart3.nextToken().toUpperCase());
                        eff_from[i] = dataPart3.nextToken();
                        temp = dataPart3.nextToken();
                        if(temp.contains("Oct") || temp.contains("Mar"))
                            eff_till[i] = temp;
                        else {
                        	i--;
                        	size--;
                        }
                    }
                }
                else {
                    StringTokenizer data = new StringTokenizer(str,",");
                    flights[i].setSource(data.nextToken().trim());
                    flights[i].setDestination(data.nextToken().trim());
                    temp = data.nextToken();
                    
                    if(temp.compareTo("Daily") == 0) {
                        for(int x=0; x<=7; x++)
                            flights[i].setFrequency(x, true);
                    }
                    else
                        flights[i].setFrequency(7, true);
                    
                    flights[i].setFlightNo(data.nextToken());
                    flights[i].setDepTime(data.nextToken());
                    flights[i].setArrTime(data.nextToken());
                    flights[i].setVia(data.nextToken().toUpperCase());
                    eff_from[i] = data.nextToken();
                    temp = data.nextToken();
                    if(temp.contains("Oct") || temp.contains("Mar"))
                        eff_till[i] = temp;
                    else {
                        i--;
                        size--;
                    }
                }
                i++;
                strPart1 = null;
                strPart2 = null;
                strPart3 = null;
                str = br.readLine();
            }
            br.close();
            reader.close();
        }
        catch (IOException e) {
           System.out.println("Cannot read the file!");
        }
        finally {
            if(reader != null) {
                try {
                	reader.close();
                    br.close();
                }
                catch (IOException e) {
                   System.out.println("Error in closing the file");
                }
            }
        }
        return flights;
    }
    
    int spiceSize() {
        return size;
    }
    
    @Override
    public Flight[] readBooking(Flight[] flights, int size, String date, int passCount) {
        FileReader reader = null;
        BufferedReader br = null;
        String str, strPart1, strPart2, strPart3;
        int i, cap;
        ArrayList<Flight> newFlights = new ArrayList<Flight>();
        for(i=0; i<size; i++)
            newFlights.add(flights[i]);
        try {
            File f = new File("Files/book.csv");
            if(!f.exists())
                f.createNewFile();
            else {
            	for(i=0; i<size; i++) {
            		reader = new FileReader(f);
            		br = new BufferedReader(reader);
            		str = br.readLine();
            		
            		while(str != null) {            			
            			StringTokenizer data1 = new StringTokenizer(str, ",");
            			strPart1 = data1.nextToken();
            			if(strPart1.compareTo(flights[i].getFlightNo()) == 0){
            				strPart2 = data1.nextToken();            				
            				if(strPart2.compareTo(date) == 0) {
            					strPart3 = data1.nextToken();            					
            					cap = Integer.parseInt(strPart3);
            					if(passCount > cap){
            						newFlights.remove(i);
            					}
            				}
            			}
            			str = br.readLine();
            		}
            	}
            }
            try {
    			br.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(!newFlights.isEmpty()) {
            this.size = newFlights.size();
            for(i=0; i<this.size; i++)
                flights[i] = newFlights.get(i);
        }
        return flights;
    }
    
    public void saveBooking(CombinedFlight cf, String date, int passCount) {
        FileReader reader = null;
        BufferedReader br = null;
        FileWriter fw;
        File file = new File("Files/book.csv");
        String str, strPart1, strPart2, strPart3, newFile="";
        int cap; boolean flagSpice = false, flagSilk = false;
        try{
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            str = br.readLine();
            if(str!=null){
                while(str != null) {
                    newFile += str + "\r\n";
                    StringTokenizer data1 = new StringTokenizer(str,",");
                    strPart1 = data1.nextToken();
                    if(strPart1.compareTo(cf.getSpiceFlightNo()) == 0){
                        strPart2 = data1.nextToken();
                        if(strPart2.compareTo(date) == 0) {    ////check flight of that day
                            strPart3 = data1.nextToken();
                            cap = Integer.parseInt(strPart3);
                            newFile = newFile.replace((strPart1+","+strPart2+","+strPart3), (strPart1+","+strPart2+","+Integer.toString(cap-passCount)));
                            flagSpice = true;
                        }
                    }
                    
                    if(strPart1.compareTo(cf.getSilkFlightNo()) == 0){
                        strPart2 = data1.nextToken();
                        if(strPart2.compareTo(date) == 0) {
                            strPart3 = data1.nextToken();
                            cap = Integer.parseInt(strPart3);
                            newFile = newFile.replace((strPart1+","+strPart2+","+strPart3), (strPart1+","+strPart2+","+Integer.toString(cap-passCount)));
                            flagSilk = true;
                        }
                    }
                    str = br.readLine();
                }
            }
            else {
                newFile += cf.getSpiceFlightNo() + "," + date + "," + (15-passCount) + "\r\n";
                newFile += cf.getSilkFlightNo() + "," + date + "," + (15-passCount) + "\r\n";
                flagSpice = true; flagSilk = true;
            }
            if(!flagSpice)
                newFile += cf.getSpiceFlightNo() + "," + date + "," + (15-passCount) + "\r\n";
            if(!flagSilk)
                newFile += cf.getSilkFlightNo() + "," + date + "," + (15-passCount) + "\r\n";
            fw = new FileWriter(file);
            fw.write(newFile);
            fw.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
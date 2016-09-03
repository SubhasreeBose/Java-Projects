package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValidateDate {
    String day,month,year,file1,file2;
    
   public ValidateDate(String day,String month,String year,String file1,String file2) {
	   this.day=day;
	   this.month=month;
	   this.year=year;
	   this.file1=file1;
	   this.file2=file2;
   }
   
   public int validateDate() {
	   if(file1.compareTo("spicejet.Schedule.csv") == 0) {
		   if(year.trim().equalsIgnoreCase("2014") && month.equalsIgnoreCase("Oct") && Integer.parseInt(day) < 26)
			   return 1;
	   }
	   else {
		   if(year.trim().equalsIgnoreCase("2014")) {
			   if(month.equalsIgnoreCase("Oct") && Integer.parseInt(day) > 25)
				   return 1;
			   if(month.equalsIgnoreCase("Nov") && Integer.parseInt(day) < 31)
				   return 1;
			   if(month.equalsIgnoreCase("Dec"))
				   return 1;
		   }
		   
		   else if(year.trim().equalsIgnoreCase("2015")) {
			   if(month.equalsIgnoreCase("Feb") || month.equalsIgnoreCase("Mar")) {
				   if(!day.equalsIgnoreCase("31") || !day.equalsIgnoreCase("29") || !day.equalsIgnoreCase("30"))
					   return 1;
			   }
			   if(month.equalsIgnoreCase("Jan"))
				   return 1;
		   }
	   }		   
	   return 0;
   }
   
   public int checkMonth() {
	   
	   if(month.equalsIgnoreCase("Oct")==true )
		   return 10;
	   if(month.equalsIgnoreCase("Nov")==true )
		   return 11;
	   if(month.equalsIgnoreCase("Dec")==true )
		   return 12;
	   if(month.equalsIgnoreCase("Jan")==true )
		   return 1;
	   if(month.equalsIgnoreCase("Feb")==true )
		   return 2;
	   if(month.equalsIgnoreCase("Mar")==true )
		   return 3;	   
	   return -1;
   }
   
   public  int checkDay(int dd,int mm) {
       Date date = (new GregorianCalendar(2014, mm-1, dd)).getTime();
       SimpleDateFormat f = new SimpleDateFormat("EEEE");
       String d = f.format(date);
       if(d.compareTo("Sunday") == 0)
           return 0;
       else if(d.compareTo("Monday") == 0)
           return 1;
       else if(d.compareTo("Tuesday") == 0)
           return 2;
       else if(d.compareTo("Wednesday") == 0)
           return 3;
       else if(d.compareTo("Thursday") == 0)
           return 4;
       else if(d.compareTo("Friday") == 0)
           return 5;
       else
           return 6;
   }
}

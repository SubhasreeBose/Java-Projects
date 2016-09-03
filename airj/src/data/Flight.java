package data;

public class Flight {
    private String flight_no;
    private String source;
    private String destination;
    private String via;
    private String dep_time;
    private String arr_time;
    private boolean freq[];
    private int capacity;
    
    public Flight(){
        flight_no = "";
        source = "";
        destination = "";
        via = "";
        dep_time = "";
        arr_time = "";
        freq = new boolean[8];
        capacity = 0;
    }
    
    
    //Getter Methods
    public void setFlightNo(String flightNo){
        flight_no = flightNo;
    }
    public void setSource(String origin){
        source = origin;
    }
    public void setDestination(String dest){
        destination = dest;
    }
    public void setVia(String via){
        this.via = via;
    }
    public void setDepTime(String dep_time){
        this.dep_time = dep_time;
    }
    public void setArrTime(String arr_time){
        this.arr_time = arr_time;
    }
    public void setFrequency(int i,boolean check) {
    	freq[i]=check;	
    }
    
    //Setter Methods
    public String getFlightNo(){
        return flight_no;
    }
    public String getSource(){
        return source;
    }
    public String getDestination(){
    	return destination;
    }
    public String getVia(){
    	return this.via;
    }
    public String getDepTime(){
    	return this.dep_time;
    }
    public String getArrTime(){
    	return this.arr_time;
    }
    public boolean getFrequency(int i) {
    	return freq[i];	
    }
    public int getCapacity() {
    	return capacity;	
    }
    
    //Check Method
    public boolean isHopping() {
    	if(via.compareTo("DIRECT ") != 0)
    		return true;
    	return false;
    }
}

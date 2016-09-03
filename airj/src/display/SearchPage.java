package display;

/**
 *
 * @author AirJ
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

import data.CombinedFlight;
import util.FontAwesome;

public class SearchPage extends JFrame{
    
    static final int max=10;
    static final int min=1,initial=5;
    static final int MY_MINIMUM = 0;
    final static int interval = 50;
    static final int MY_MAXIMUM = 100;
    public String file1,file2;
    int i;

    JPanel Psearch=new JPanel(null);
    String[] places = {"MUMBAI", "DELHI", "PUNE"};
    String[] number={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    JProgressBar pbar;
    
    JButton BSearch;
    JFrame frame;
    Timer timer;
    SearchPage objsearch;
    JComboBox CBPlace,CBDatem,CBDatey,CBDated;
    JSlider SlidePerson;
    JCheckBox licenseBox;
   
    public SearchPage(String file1,String file2) { 
    	
    	this.file1=file1;
    	this.file2=file2;        
        
        String []month = {"Oct","Nov","Dec","Jan","Feb","Mar"};
        String []year = {"2014","2015"};
        
        frame=new JFrame("Search");
        frame.setSize(650, 650);
        
        ImageIcon img = new ImageIcon("Images/jet-icon1.png");
        frame.setIconImage(img.getImage());
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
      
        Icon img1 = new ImageIcon("Images\\logo-without-bg1.png");
        JLabel LLogo = new JLabel("", img1, SwingConstants.LEFT);
        JLabel Ltag=new JLabel("<html><i><font face=\"verdana\" size=\"3\" color=\"white\">The smarter, easier and faster way to fly.</font></i></html>");
        Ltag.setBounds(130, 50, 350, 20);
        Ltag.setForeground(Color.black);
        LLogo.setBounds(0, 0, 370, 70);
        LLogo.setVisible(true);
        
        JLabel LHeading=new JLabel("<html><font face=\"Castellar\" size=\"4\" color=\"white\">Fly anywhere with us. Book flights in 3 simple steps.</font></html>");        
        LHeading.setBounds(50, 80, 600, 50);
         
        JPanel fillpane=new JPanel();
        FontAwesome f=new FontAwesome();        
        JLabel LTo=new JLabel("<html><B><font size=\"4\" >To</font></B></html>");
        JLabel LFrom=new JLabel("<html><B><font size=\"4\">From</font></B></html>");  
        JLabel Larrow=new JLabel("\uf072");
        Larrow.setFont(f.med);
        Larrow.setForeground(Color.black);
        
        JLabel LPlace=new JLabel("<html><b>SINGAPORE</b></html>");
        CBPlace=new JComboBox(places);
       
        LTo.setBounds(465, 230, 40, 30);
        LFrom.setBounds(129, 230, 50, 30);
        LPlace.setBounds(445, 270, 170, 20);
        CBPlace.setBounds(107, 270, 100, 20);
        Larrow.setBounds(300, 250, 150, 50); 
        
        LTo.setForeground(Color.black);
        LFrom.setForeground(Color.black);
        LPlace.setForeground(Color.black);
        JLabel LDate=new JLabel("<html><B><font color= \"black\"size=\"4\">DEPARTS ON</font></B></html>");        
        JLabel Limgdate = new JLabel("\uf073");
        Limgdate.setFont(f.sm);
        Limgdate.setForeground(Color.black);
        
        CBDated=new JComboBox(number);
        CBDatem=new JComboBox(month);
        CBDatey=new JComboBox(year);
        
        LDate.setBounds(107, 365, 100, 20);
        Limgdate.setBounds(137,330,30,30);
        CBDated.setBounds(75,400,40,20);
        CBDatem.setBounds(124,400,55,20);
        CBDatey.setBounds(187,400,55,20); 
        CBDated.setBackground(Color.decode("#B0D4E6"));
        CBDatem.setBackground(Color.decode("#B0D4E6"));
        CBDatey.setBackground(Color.decode("#B0D4E6"));
        CBDated.setForeground(Color.black);
        CBDatem.setForeground(Color.black);
        CBDatey.setForeground(Color.black);
        CBPlace.setForeground(Color.black);
        CBPlace.setBackground(Color.decode("#B0D4E6"));
        
        JLabel Limgpassenger=new JLabel("\uf0c0");
        Limgpassenger.setFont(f.sm);
        Limgpassenger.setForeground(Color.black);
        JLabel LPerson=new JLabel("<html><B><font color=\"black\" size=\"4\">PASSENGER COUNT</font></B></html>");
        SlidePerson=new JSlider(JSlider.HORIZONTAL,min,max,initial);
        SlidePerson.setMajorTickSpacing(1);
        SlidePerson.setMinorTickSpacing(1);
        SlidePerson.setPaintTicks(true);
        SlidePerson.setPaintLabels(true);
        SlidePerson.setBackground(Color.decode("#B0D4E6"));        
        SlidePerson.setForeground(Color.black);
        
        LPerson.setBounds(410, 360, 200, 30);
        SlidePerson.setBounds(390, 395, 185, 50);
        Limgpassenger.setBounds(465, 320, 50, 50);  
        
        licenseBox = new JCheckBox("Yes, I agree that all passengers are between 1 and 70 years of age.");
        licenseBox.setMnemonic(KeyEvent.VK_C);
        licenseBox.setBounds(49, 470, 470, 30);  
        licenseBox.setBackground(Color.decode("#2c3e50"));
        licenseBox.setForeground(Color.white);
        
        fillpane.add(LFrom);
        fillpane.add(LTo);
        fillpane.add(Larrow);
        fillpane.add(LPlace);
        fillpane.add(CBPlace);
        fillpane.add(LDate);
        fillpane.add(CBDated);
        fillpane.add(LPerson);
        fillpane.add(SlidePerson);
        fillpane.add(licenseBox);
        //fillpane.setBackground(Color.decode("#1abc9c"));
        fillpane.setBackground(Color.decode("#B0D4E6"));
        fillpane.setBounds(0,220,650,240);        
        
        final JLabel Lpbar=new JLabel("<html>Getting flight details and availability.....</html>");
        pbar=new JProgressBar();
        pbar.setMinimum(MY_MINIMUM);
        pbar.setMaximum(MY_MAXIMUM);        
        pbar.setForeground(Color.decode("#66A3FF"));
        Lpbar.setBounds(212,555,800,30);
        Lpbar.setForeground(Color.white);
        Lpbar.setVisible(false);
        JLabel LDummy=new JLabel("<html><B> </B></html>");
        
        BSearch=new JButton("Find Flights");  
        BSearch.setBounds(230,520,180,30);  
        
        JLabel LOne=new JLabel("\uf002");
        LOne.setFont(f.lg);
        LOne.setForeground(Color.white);
        Icon imglinew=new ImageIcon("Images\\linew.png");
        Icon imgline=new ImageIcon("Images\\line.png");
        JLabel Lline1=new JLabel("", imglinew, SwingConstants.LEFT);
        JLabel Lline2=new JLabel("", imglinew, SwingConstants.LEFT);
        JLabel Lline3=new JLabel("", imgline, SwingConstants.LEFT);
        
        JLabel LThree=new JLabel("\uf00c");
        LThree.setFont(f.sm);
        LThree.setForeground(Color.white);
        
        JLabel LTwo=new JLabel("\uf0ca");
        LTwo.setFont(f.sm);
        LTwo.setForeground(Color.white);
        JLabel search=new JLabel("<html><B><font color=\"white\"size=\"3\">Search Flight</font></B></html>");
        JLabel display=new JLabel("<html><B><font color=\"white\" size=\"3\">Select Flight</font></B></html>");
        JLabel book=new JLabel("<html><B><font color=\"white\" size=\"3\">Book Flight</font></B></html>");
        
        LOne.setBounds(40, 105, 70, 100);
        LTwo.setBounds(300, 130, 50, 50);
        LThree.setBounds(550, 130, 50, 50);
        search.setBounds(28, 180, 150, 30);
        display.setBounds(280, 180, 150, 30);
        book.setBounds(535, 180, 150, 30);
        Lline1.setBounds(90,150,200,20);
        Lline2.setBounds(340,150,200,20);
        Lline3.setBounds(0,315,1150,2);
        fillpane.add(Lline3);
        	
        frame.add(LOne);
        frame.add(Ltag);
        frame.add(Lline3);
        frame.add(LTwo);
        frame.add(LThree);
        frame.add(Lline1);
        frame.add(Lline2);
        frame.add(search);
        frame.add(display);
        frame.add(book);
        frame.add(LLogo);
        frame.add(LHeading);        
        frame.add(LTo);
        frame.add(Larrow);
        frame.add(LFrom);
        frame.add(LPlace);
        frame.add(CBPlace);              
        frame.add(LDate);        
        frame.add(CBDated);
        frame.add(CBDatem);
        frame.add(CBDatey);
        frame.add(LPerson);
        frame.add(SlidePerson);
        frame.add(Limgpassenger);
        frame.add(Limgdate);
        frame.add(fillpane);    
        frame.add(licenseBox);        
        frame.add(BSearch);        
        frame.add(pbar);
        frame.add(Lpbar);        
        frame.add(LDummy);
        frame.getContentPane().setBackground(Color.decode("#2c3e50"));        

        //frame.getContentPane().setBackground(Color.decode("#2b87c3"));
        
        frame.setResizable(false);
        frame.setVisible(true);
        BSearch.addActionListener(new SButton(this));
        
        objsearch=this;
        
        timer = new Timer(interval, new ActionListener() {
        	String f1,f2;
            public void actionPerformed(ActionEvent evt) {
                i++;
                Lpbar.setVisible(true);
                pbar.setValue(i);
                f1=getFile1();
                f2=getFile2();
                if (i == 100){
                    Toolkit.getDefaultToolkit().beep();
                    timer.stop();
                    BSearch.setEnabled(true);
                    pbar.setValue(0);
                    pbar.setBounds(0,0,0,0);
                    Lpbar.setVisible(false);
                    i=0;
                    DisplayManager dm = new DisplayManager(objsearch);                    
                    dm.displayDisplayPage(f1, f2);           
                    frame.dispose();            
                }               
            }
        });       
    }
    
    public String getFile1() {
    	return file1;
    }
    public String getFile2() {
    	return file2;
    }        
}
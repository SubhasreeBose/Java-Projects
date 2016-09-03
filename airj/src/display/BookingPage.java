package display;

/**
 *
 * @author AirJ
 */

import util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookingPage extends JFrame {
    JFrame Bframe;
    JLabel search,display;
    JButton BBook;
    JTextField TxtName,Txtemail;
    SearchPage objsearch;
    DisplayPage objdisplay;
    JCheckBox licenseBox;
    String flight1,flight2,inter;
    
    BookingPage(DisplayPage objdisplay,SearchPage objsearch) {
        this.objdisplay=objdisplay;
        this.objsearch=objsearch;
        
        Bframe=new JFrame("Confirm Booking");
        Bframe.setSize(650,650);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - Bframe.getWidth()) / 2;
        int y = (screenSize.height - Bframe.getHeight()) / 2;
        Bframe.setLocation(x, y);
        Bframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("Images/jet-icon1.png");
        Bframe.setIconImage(img.getImage());
       
        FontAwesome f=new FontAwesome();
                
        Icon img1 = new ImageIcon("Images/logo-without-bg1.png");
        JLabel LLogo = new JLabel("", img1, SwingConstants.LEFT);
        LLogo.setBounds(0, 0, 700,70);
        JLabel Ltag=new JLabel("<html><i><font face=\"verdana\" size=\"3\" color=\"white\">The smarter, easier and faster way to fly.</font></i></html>");
        Ltag.setBounds(130, 50, 350, 20);
        Ltag.setForeground(Color.white);
   
        Icon imgedit=new ImageIcon("Images/Edit.gif");        
        
        JLabel LName=new JLabel("<html><B> Name: </B></html>");
        LName.setForeground(Color.black);
        TxtName=new JTextField();
        JLabel LEmail=new JLabel("<html><B> Email ID: </B></html>");
        Txtemail=new JTextField();
        LEmail.setForeground(Color.black);
        JLabel info=new JLabel("<html><font color=red size=1> * Details of your booking will be e-mailed to you</font></html>");
        
      
        JLabel Limglogo1 = new JLabel("\uf1d9");
        Limglogo1.setFont(f.sm);
        Limglogo1.setForeground(Color.black);
        JLabel Limglogo2 = new JLabel("\uf1d8"); 
        Limglogo2.setFont(f.sm);
        Limglogo2.setForeground(Color.black);
        JLabel Lflightno1=new JLabel();
        flight1=(String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 1);
        Lflightno1.setText(flight1);
        Lflightno1.setForeground(Color.black);
        JLabel Lflightno2=new JLabel();
        flight2=(String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 6);
        Lflightno2.setText(flight2);
        Lflightno2.setForeground(Color.black);
        JLabel Larr1=new JLabel();
        Larr1.setText((String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 0));
        Larr1.setForeground(Color.black);
        JLabel Ldept1=new JLabel();
        Ldept1.setText((String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 2));
        Ldept1.setForeground(Color.black);
        JLabel Larr2=new JLabel();
        Larr2.setText((String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 5)); 
        Larr2.setForeground(Color.black);
        JLabel Ldept2=new JLabel();
        Ldept2.setForeground(Color.black);
        Ldept2.setText((String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 7)); 
        
        
        JLabel LDate=new JLabel("<html><B>Date</B></html>");
        LDate.setForeground(Color.black);
        JLabel LFlightS1=new JLabel("<html><B></B></html>");
        LFlightS1.setForeground(Color.black);
        LFlightS1.setText(objsearch.CBPlace.getSelectedItem().toString());
        JLabel LFlightS2=new JLabel("<html><B></B></html>");
        inter=(String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 3);
        LFlightS2.setText(inter);
        LFlightS2.setForeground(Color.black);
        JLabel LFlightD1=new JLabel("<html><B></B></html>");
        LFlightD1.setText(inter);
        LFlightD1.setForeground(Color.black);
        JLabel LFlightD2=new JLabel("<html><B>SINGAPORE</B></html>");
        LFlightD2.setForeground(Color.black);
        Icon imgline=new ImageIcon("Images\\line.png");
        Icon imglinew=new ImageIcon("Images\\linew.png");
        JLabel Limgarrow1 = new JLabel("", imgline, SwingConstants.LEFT);
        JLabel Limgarrow2 = new JLabel("", imgline, SwingConstants.LEFT);
        
        JLabel imgtime = new JLabel("\uf017");
        imgtime.setFont(f.sm);
        imgtime.setForeground(Color.black);
        imgtime.setBounds(50, 410, 100, 50);
        JLabel LTime=new JLabel("<html><B>Total Time Duration: "+(String) objdisplay.TFlight.getModel().getValueAt(objdisplay.selRow, 8)+"</B></html>");
        LTime.setForeground(Color.black);
        
        licenseBox = new JCheckBox(" I agree that flight schedule is subject to change under certain conditions.");
        licenseBox.setMnemonic(KeyEvent.VK_C); 
        licenseBox.setBackground(Color.decode("#2c3e50"));
        licenseBox.setForeground(Color.white);
        
        BBook=new JButton("Book");        
        
        JLabel LDummy=new JLabel("<html><B> </B></html>");   
        JLabel selection=new JLabel("Selected Flight:");
        selection.setForeground(Color.white);        
        selection.setBounds(50,180,170,50);
         
        Limglogo1.setBounds(50,235,50,50);
        Limglogo2.setBounds(50,335,50,50);
        LFlightS1.setBounds(120,250,300,30);
        LFlightD1.setBounds(520,250,300,30);
        LFlightS2.setBounds(120,350,300,30);
        LFlightD2.setBounds(520,350,250,30);
        Limgarrow1.setBounds(202,262,300,3);
        Limgarrow2.setBounds(202,362,300,3);
        LTime.setBounds(80,420,300,30);        
        TxtName.setBounds(110, 480, 180, 20);
        LName.setBounds(50, 480, 40, 20);
        Txtemail.setBounds(420, 480, 180, 20);
        info.setBounds(370, 520, 250, 20);
        LEmail.setBounds(350, 480, 60, 20);        
        BBook.setBounds(500,570,100,30);
        licenseBox.setBounds(30,570,450,30);
        
        
        
        Lflightno1.setBounds(50,260,50,50);
        Larr1.setBounds(250,260,250,50);
        Ldept1.setBounds(370,260,250,50);
        Lflightno2.setBounds(50,360,50,50);
        Larr2.setBounds(250,360,250,50);
        Ldept2.setBounds(370,360,250,50);
        
        JPanel pane=new JPanel();
        pane.add(Lflightno1);
        pane.add(Lflightno2);
        pane.add(Larr1);
        pane.add(Larr2);
        pane.add(Ldept1);
        pane.add(Ldept2);
        pane.add(Limglogo1);
        pane.add(Limglogo2);
        pane.add(LTime);
        pane.add(LFlightS1);
        pane.add(Limglogo2);
        pane.add(LFlightS2);
        pane.add(LFlightD2);
        pane.add(LFlightD1);
        pane.add(Limgarrow2);
        pane.add(Limgarrow1);
        pane.setBounds(0,220,650,300);
        pane.setBackground(Color.decode("#B0D4E6"));
        
        
        
        JLabel LOne=new JLabel("\uf002");
        LOne.setFont(f.sm);
        LOne.setForeground(Color.decode("#B0D4E6"));
        
        JLabel Lline1=new JLabel("", imglinew, SwingConstants.LEFT);
        JLabel Lline2=new JLabel("", imglinew, SwingConstants.LEFT);
        
        
        JLabel LThree=new JLabel("\uf00c");
        LThree.setFont(f.lg);
        LThree.setForeground(Color.white);
        
        JLabel LTwo=new JLabel("\uf0ca");
        LTwo.setFont(f.sm);
        LTwo.setForeground(Color.decode("#B0D4E6"));
        search=new JLabel("\uf044");
        search.setFont(f.sm2);
        search.setForeground(Color.white);
        display=new JLabel("\uf044");
        display.setFont(f.sm2);
        display.setForeground(Color.white);
        JLabel book=new JLabel("<html><B><font size=\"3\">Book Flight</font></B></html>");
        book.setForeground(Color.white);
        
        LOne.setBounds(55, 100, 50, 50);
        LTwo.setBounds(300, 100, 50, 50);
        LThree.setBounds(550, 75, 70, 100);
        search.setBounds(60, 145, 150, 30);
        display.setBounds(300, 145, 150, 30);
        book.setBounds(530, 145, 150, 30);
        
        Lline1.setBounds(90,120,200,10);
        Lline2.setBounds(340,120,200,10);
        
        Bframe.add(Lline1);
        Bframe.add(Lline2);
        Bframe.add(Ltag);
        Bframe.add(LOne);
        Bframe.add(LTwo);
        Bframe.add(LThree);
        Bframe.add(search);
        Bframe.add(display);
        Bframe.add(book);
        Bframe.add(LLogo);
        Bframe.add(TxtName);
        Bframe.add(LName);
        Bframe.add(Txtemail);
        Bframe.add(LEmail);
        Bframe.add(LTime);        
        Bframe.add(BBook);
        Bframe.add(licenseBox);
        Bframe.add(Limglogo1);
        Bframe.add(Limglogo1);
        Bframe.add(Limglogo2);
        Bframe.add(LFlightS1);
        Bframe.add(LFlightS2);
        Bframe.add(LFlightD1);
        Bframe.add(LFlightD2);
        Bframe.add(Limgarrow1);
        Bframe.add(Limgarrow2);
        Bframe.add(imgtime);
        Bframe.add(LDate);      
        Bframe.add(selection);
        Bframe.add(Lflightno1);
        Bframe.add(Lflightno2);
        Bframe.add(Larr1);
        Bframe.add(Ldept2);
        Bframe.add(Ldept1);
        //Bframe.add(info);
        
        Bframe.add(Larr2);
        Bframe.add(pane);
        Bframe.add(LDummy);
        
        Bframe.setVisible(true);
        Bframe.setResizable(false);
        Bframe.getContentPane().setBackground(Color.decode("#2c3e50"));
        
        BBook.addActionListener(new BookButton(this));
        search.addMouseListener(new ESMouse(this,true));
        display.addMouseListener(new EDMouse(this,true));         
    }    
}

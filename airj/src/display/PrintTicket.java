package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.FontAwesome;

public class PrintTicket extends JFrame {
    JFrame Pframe;
    
    PrintTicket(BookingPage obj) {
        Pframe=new JFrame("Ticket");
        Pframe.setSize(500,300);
        
        FontAwesome f=new FontAwesome();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - Pframe.getWidth()) / 2;
        int y = (screenSize.height - Pframe.getHeight()) / 2;
        Pframe.setLocation(x, y);
        Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("Images/jet-icon1.png");
        Pframe.setIconImage(img.getImage());
        
        Icon img1 = new ImageIcon("Images\\logo-without-bg1.png");
        JLabel LLogo = new JLabel("", img1, SwingConstants.LEFT);
        LLogo.setBounds(10,0,150,70);
        JLabel LName=new JLabel("<html><B><font size=\"3\"color=\"black\">Booked by: "+obj.TxtName.getText()+"</font> </B></html>");
       
        
        
        JButton BBook=new JButton("\uf02f");
        BBook.setFont(f.m);
        BBook.setForeground(Color.decode("#2c3e50"));
        
        JLabel LDummy=new JLabel("<html><B> </B></html>");
        JLabel LDate=new JLabel("<html><B><font size=\"4\"color=\"white\">Successfully Booked Flight!"+"\t\t\t"+obj.objsearch.CBDated.getSelectedItem().toString()+" Oct 2014</font></B></html>");
        JLabel LPerson=new JLabel("<html><B><font size=\"3\"color=\"black\">Boarding pass for "+obj.objsearch.SlidePerson.getValue()+ ".</font></B></html>");
        JLabel LFlight1=new JLabel("<html><B><font size=\"3\"color=\"black\">"+obj.flight1+" Flight from "+obj.objsearch.CBPlace.getSelectedItem().toString()+" to "+obj.inter+".</font></B></html>");
        JLabel LFlight2=new JLabel("<html><B><font size=\"3\"color=\"black\">"+obj.flight2+" Flight from "+obj.inter+" to SINGAPORE.</font></B></html>");
        JLabel LTime=new JLabel("<html><B><font size=\"2\"color=\"blue\">Time</font></B></html>");   
        
        JLabel barcode=new JLabel("\uf02a");
        barcode.setFont(f.lg);
       
        LDate.setBounds(170, 20, 270, 30);
        LPerson.setBounds(125, 100, 200, 30); 
        
        LFlight1.setBounds(125,130,350,30);
        LFlight2.setBounds(125,160,350,30);
        LTime.setBounds(30,120,70,30);
        BBook.setBounds(30,230,50,30);
        barcode.setBounds(430,230,500,30);
        
        LName.setBounds(125, 195, 240, 30);
         
        
        JPanel pane=new JPanel();
        pane.add(LDate);
        pane.setBackground(Color.decode("#2c3e50"));
        pane.setBounds(0, 0, 500, 80);
        
        Pframe.getContentPane().setBackground(Color.white);
       // Pframe.add(LHeading);
        Pframe.add(LLogo);
        Pframe.add(barcode);
        //Pframe.add(LTime);
        Pframe.add(LName);
        Pframe.add(BBook);
        Pframe.add(LFlight1);
        Pframe.add(LFlight2);
        Pframe.add(LDate);
        Pframe.add(LPerson);
        Pframe.add(pane);
        Pframe.setResizable(false);
        Pframe.add(LDummy);
        Pframe.setVisible(true);
        
        
        
        BBook.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "Thank You!\nYour booking has been confirmed.\nHave a safe journey.");
               Pframe.dispose();               
           }           
        });
    }
}

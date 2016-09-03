package display;

/**
 *
 * @author AirJ
 */

import util.*;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

import persistence.FileRead;

import data.CombinedFlight;

public class DisplayPage extends JFrame {
    
    final String[] col ={"Departure","SpiceJet","Arrival","Intermediate","Via","Departure","SilkAir","Arrival (SST)" ,"Duration"};
    final String[][] row;
    JFrame frame;
    JTextField TxtFlight,TxtTime;
    JTable TFlight;
    SearchPage objsearch;
    JLabel search,LOne;
    int selRow;
    public int month,day,passCount;
    private CombinedFlight  ff;
    CombinedFlight filteredFlight[];
    
    public DisplayPage(SearchPage objsearch,String file1,String file2) {

        String source = objsearch.CBPlace.getSelectedItem().toString();
        ValidateDate vd = new ValidateDate(objsearch.CBDated.getSelectedItem().toString(), objsearch.CBDatem.getSelectedItem().toString(), objsearch.CBDatey.getSelectedItem().toString(), file1, file2);
    	
        month = vd.checkMonth();
        day=vd.checkDay(Integer.parseInt(objsearch.CBDated.getSelectedItem().toString()), month);
        
        String date=objsearch.CBDated.getSelectedItem().toString() +"/" + month ;
        passCount = objsearch.SlidePerson.getValue(); 
        
        CombinedFlight cf = new CombinedFlight(source, day, date, passCount, file1, file2);
        filteredFlight=new CombinedFlight[cf.flightCount];
        filteredFlight=cf.combine(source, day, passCount);
        
        row=new String[cf.flightCount][9];
        
        int i;
        for(i=0;i<cf.flightCount;i++) {
            row[i][0]=filteredFlight[i].getDeptSpice();
            row[i][1]=filteredFlight[i].getSpiceFlightNo();
            row[i][2]=filteredFlight[i].getArrSpice();
            row[i][3]=filteredFlight[i].getIntermediate();
            row[i][4]=filteredFlight[i].getVia();
            row[i][5]=filteredFlight[i].getDeptSilk();
            row[i][6]=filteredFlight[i].getSilkFlightNo();
            row[i][7]=filteredFlight[i].getArrSilk();
            row[i][8]=filteredFlight[i].getDuration();
                        
        }      
        
        frame=new JFrame("Display");
        frame.setSize(650, 660);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
       
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon img = new ImageIcon("Images/jet-icon1.png");
        frame.setIconImage(img.getImage());
        
        this.objsearch=objsearch;
        
        FontAwesome f=new FontAwesome();
        
        JButton BConfirm=new JButton("Confirm");
        BConfirm.setBounds(460,520,140,40);
        
        JLabel LDummy=new JLabel("<html><B> </B></html>");
        JLabel LFlight1=new JLabel("<html><B>First Flight  </B></html>");
        LFlight1.setForeground(Color.white);
        JLabel LFlight2=new JLabel("<html><B> Second Flight </B></html>");
        LFlight2.setForeground(Color.white);
        
        TxtFlight=new JTextField();
        TxtTime=new JTextField();
        TxtFlight.setBounds(120,500,100,30);
        TxtTime.setBounds(120,540,100,30);
        LFlight1.setBounds(30,500,120,30);
        LFlight2.setBounds(30,540,120,30);
        TxtFlight.setEditable(false);
        TxtTime.setEditable(false);
        
        Icon img1 = new ImageIcon("Images/logo-without-bg1.png");
        Icon imglinew=new ImageIcon("Images\\linew.png");
        JLabel LLogo = new JLabel("", img1, SwingConstants.LEFT);
        LLogo.setBounds(0, 0, 700, 70);
        JLabel Ltag=new JLabel("<html><i><font face=\"verdana\" size=\"3\" color=\"white\">The smarter, easier and faster way to fly.</font></i></html>");
        Ltag.setBounds(130, 50, 350, 20);
        Ltag.setForeground(Color.white);
     
        
        JLabel LResult=new JLabel("Total no.of available flights : "+cf.flightCount+"");
        LResult.setBounds(30,230,650,30);
        LResult.setForeground(Color.white);
        
        TFlight = new JTable(row, col);
        TFlight.getColumnModel().getColumn(0).setPreferredWidth(45);
        TFlight.getColumnModel().getColumn(1).setPreferredWidth(35);
        TFlight.getColumnModel().getColumn(2).setPreferredWidth(40);
        TFlight.getColumnModel().getColumn(3).setPreferredWidth(65);
        TFlight.getColumnModel().getColumn(4).setPreferredWidth(65);
        TFlight.getColumnModel().getColumn(5).setPreferredWidth(45);
        TFlight.getColumnModel().getColumn(6).setPreferredWidth(35);
        //TFlight.getColumnModel().getColumn(7).setPreferredWidth(60);
        TFlight.getColumnModel().getColumn(8).setPreferredWidth(65);
        
        // #2c3e50			#34495e
        TFlight.getTableHeader().setBackground(Color.decode("#2c3e50"));
        TFlight.getTableHeader().setForeground(Color.white);
        TFlight.getTableHeader().setAlignmentX(LEFT_ALIGNMENT);
        TFlight.setAlignmentX(LEFT_ALIGNMENT);
           
        for (int c = 0; c < TFlight.getColumnCount(); c++) {
            Class<?> col_class = TFlight.getColumnClass(c);
            TFlight.setDefaultEditor(col_class, null);
        }
      
        TFlight.setFillsViewportHeight(true);
        
        JScrollPane JSP1 = new JScrollPane(TFlight, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JSP1.setBounds(2, 270,630, 200);
        TFlight.setBackground(Color.decode("#B0D4E6"));
        TFlight.setForeground(Color.black);
        JSP1.setBackground(Color.decode("#34495e"));
        
        JSP1.setVisible(true);
        
        JLabel selection=new JLabel();
        String str1="Flight result for: To SINGAPORE from "+objsearch.CBPlace.getSelectedItem().toString()+ " on " +objsearch.CBDated.getSelectedItem().toString() +" "+objsearch.CBDatem.getSelectedItem().toString()+" "+objsearch.CBDatey.getSelectedItem().toString()+"  for " + objsearch.SlidePerson.getValue()+" passengers";
        selection.setText(str1);
        selection.setBounds(30,190,570,50);
        selection.setForeground(Color.white);
        
        JPanel pane=new JPanel();
        pane.add(selection);
        pane.add(LResult);
        pane.setBackground(Color.decode("#2c3e50"));
        pane.setBounds(0,180,650,85);
       
        
        
        Icon imgedit=new ImageIcon("Images\\Edit.gif");
        
        JLabel LOne=new JLabel("\uf002");
        LOne.setFont(f.sm);
        LOne.setForeground(Color.decode("#B0D4E6"));
        Icon imgline=new ImageIcon("Images\\line.png");
        JLabel Lline1=new JLabel("", imglinew, SwingConstants.LEFT);
        JLabel Lline2=new JLabel("", imglinew, SwingConstants.LEFT);
        
        
        JLabel LThree=new JLabel("\uf00c");
        LThree.setFont(f.sm);
        LThree.setForeground(Color.white);
        
        JLabel LTwo=new JLabel("\uf0ca");
        LTwo.setFont(f.med);
        LTwo.setForeground(Color.white);
        //search=new JLabel("<html><B><font color=\"white\"size=\"4\">Search Flight</font></B></html>");
        search=new JLabel("\uf044");
        search.setFont(f.sm2);
        search.setForeground(Color.white);
        
        JLabel display=new JLabel("<html><B><font color=\"white\" size=\"3\">Select Flight</font></B></html>");
        JLabel book=new JLabel("<html><B><font color=\"white\" size=\"3\">Book Flight</font></B></html>");
        
        LOne.setBounds(40, 75, 70, 100);
        LTwo.setBounds(297, 100, 50, 50);
        LThree.setBounds(550, 100, 50, 50);
        search.setBounds(50, 145, 50, 30);
        display.setBounds(280, 145, 150, 30);
        book.setBounds(535, 145, 150, 30);        
       
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Lline1.setBounds(90,120,200,20);
        Lline2.setBounds(340,120,200,20);
        TFlight.setShowVerticalLines(false);
        TFlight.setRowHeight(30);
        
        frame.add(Lline1);
        frame.add(Lline2);
        frame.add(LOne);
        frame.add(Ltag);
        
        frame.add(LTwo);
        frame.add(LThree);
        frame.add(search);
        frame.add(display);
        frame.add(book);      
        frame.add(JSP1);
        frame.add(TxtFlight);
        frame.add(TxtTime);
        frame.add(LFlight2);
        frame.add(LFlight1);
        frame.add(BConfirm);
        frame.add(LLogo);
        frame.add(selection);
        frame.add(LResult);
        frame.add(pane);
        
        frame.add(LDummy);
        frame.getContentPane().setBackground(Color.decode("#2c3e50"));
        frame.setVisible(true);
       
        TFlight.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent evt) {  
                try {
                	
                	selRow = TFlight.getSelectedRow();                     
                    TxtFlight.setText((String)TFlight.getModel().getValueAt(selRow, 1)); 
                    TxtTime.setText((String)TFlight.getModel().getValueAt(selRow, 6));
                    
                    
                }
                catch(Exception e) {

                }
            }  
        });
        
        BConfirm.addActionListener(new CButton(this));
        search.addMouseListener(new EMouse(this,true));
    }
 public CombinedFlight getSelectedFlight()
 {
	
         ff = filteredFlight[selRow];
	 return ff;
 }
   
    
    
}

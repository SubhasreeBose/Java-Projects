package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import persistence.FileRead;

import data.CombinedFlight;

public class BookButton implements ActionListener {
    BookingPage obj;
    CombinedFlight cf;
    BookButton(BookingPage obj) {
        this.obj=obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(obj.TxtName.getText().equals(""))
           JOptionPane.showMessageDialog(null,"Sorry! Please, fill up the fields.");
        else if(obj.Txtemail.getText().contains("@")==false)
            JOptionPane.showMessageDialog(null,"Please,Enter a valid Email id.");
        else if(!obj.licenseBox.isSelected())
            JOptionPane.showMessageDialog(null, " You need to accept to proceed further!");
        
        else { 
            int iChoice = JOptionPane.showConfirmDialog(null," Continue booking?");
            if(iChoice == JOptionPane.YES_OPTION) { 
            	cf=obj.objdisplay.getSelectedFlight();
            	FileRead fr = new FileRead();
            	String date = obj.objsearch.CBDated.getSelectedItem().toString() + "/" + obj.objdisplay.month;
            	//System.out.println(cf.getSilkFlightNo());
            	fr.saveBooking(cf, date, obj.objdisplay.passCount);
                new PrintTicket(obj);
                //obj.Bframe.dispose();
            }
            else {
            }
        }
    }
}
package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import util.ValidateDate;

public class SButton implements ActionListener {
    SearchPage obj;    
    SButton(SearchPage obj) {
        this.obj=obj;
    }
    
    public void updateBar(int percent) {
        obj.pbar.setValue(percent);
        obj.pbar.setStringPainted(true);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	String day,mon,yr;
    	day = obj.CBDated.getSelectedItem().toString();
        mon = obj.CBDatem.getSelectedItem().toString();
        yr = obj.CBDatey.getSelectedItem().toString();
    	ValidateDate vd=new ValidateDate(day.trim(), mon.trim(), yr.trim(), obj.file1, obj.file2);
    	int check = vd.validateDate();
    
    	if(check==1) {
    		if(obj.licenseBox.isSelected()) {
    			obj.BSearch.setEnabled(false);
    			obj.pbar.setValue(0);
    			obj.pbar.setBounds(30,590,570,20);
    			obj.pbar.setStringPainted(true);
    			obj.timer.start();
    		}
    		else {
    			  JOptionPane.showMessageDialog(null, " You need to accept to proceed further!");
    		}
    	}
    	else
    		JOptionPane.showMessageDialog(null, " Enter a valid Date");
    		
    }
}
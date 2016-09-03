package display;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class EDMouse extends MouseAdapter {
    BookingPage obj;
    Boolean check;
    
    EDMouse(BookingPage obj,Boolean check) {
        this.obj=obj;
        this.check=check;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	obj.display.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(check==true)
        {
            obj.objdisplay.frame.show();
        obj.Bframe.dispose();
        }
    }
}
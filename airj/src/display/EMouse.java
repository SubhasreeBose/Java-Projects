package display;

import java.awt.*;
import java.awt.event.*;

class EMouse extends MouseAdapter {
    DisplayPage obj;
    Boolean check;
    
    EMouse(DisplayPage obj,Boolean check) {
        this.obj=obj;
        this.check=check;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        obj.search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
        if(check==true)
            obj.objsearch.frame.show();
        obj.frame.dispose();
    }
}
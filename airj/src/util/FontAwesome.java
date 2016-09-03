package util;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontAwesome {
	public Font lg;
	public Font sm, m, med, sm2, med2;
	
	public FontAwesome(){
		
		try (InputStream is = TestFontAwsome.class.getResourceAsStream("fontawesome-webfont.ttf")) {
        	lg = Font.createFont(Font.TRUETYPE_FONT, is);
            lg = lg.deriveFont(Font.PLAIN, 48f);
            med =lg.deriveFont(Font.PLAIN, 34f);
            med2 = lg.deriveFont(Font.PLAIN, 29f);
            sm = lg.deriveFont(Font.PLAIN, 24f);
            sm2 = lg.deriveFont(Font.PLAIN, 18f);
            m = lg.deriveFont(Font.PLAIN, 12f);
            
        } catch (IOException | FontFormatException exp) {
            exp.printStackTrace();
        }
	}
	
}

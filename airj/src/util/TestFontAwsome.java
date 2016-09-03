package util;

import java.awt.*;
import javax.swing.*;

public class TestFontAwsome {

    public static void main(String[] args) {
        new TestFontAwsome();
    }

    public TestFontAwsome() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
           
                FontAwesome font=new FontAwesome();
				

				JLabel label = new JLabel("\uf004");
				label.setFont(font.lg);
				label.setForeground(Color.BLACK);

				JFrame frame = new JFrame("Testing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridBagLayout());
				frame.add(label);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
            }
        });
    }

}
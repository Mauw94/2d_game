package GameState;

import java.awt.*;

public class DrawTextToScreen {

    public static void drawOptions(int currentChoice, String[] options, Graphics2D g) {
        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 135, 140 + i * 15);
        }
    }
    public static void drawHelp(String[] options, Graphics2D g) {
        Font font = new Font("Arial", Font.PLAIN, 10);
        g.setFont(font);
        g.setColor(Color.BLACK);
        for (int i = 0; i < options.length; i++) {
            g.drawString(options[i], 100, 80 + i * 15);
        }
    }
}

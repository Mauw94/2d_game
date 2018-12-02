package Entity;

import java.awt.*;

public class Inventory {

    private Player player;
    private final int MAXSLOTS = 5;
    public String[] inventory;
    private Font font;

    public Inventory(Player p) {
        this.player = p;
        inventory = new String[MAXSLOTS];
        font = new Font("Arial", Font.PLAIN, 14);
        // test
        for (int i = 0; i < MAXSLOTS; i++) {
            inventory[i] = i + "";
        }
    }

    public void draw(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        for (int i = 0; i < MAXSLOTS; i++) {
            g.drawString(inventory[i], 80 + i * 15, 25);
        }
    }
}

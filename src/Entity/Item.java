package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item extends MapObject {

    private String name;
    private int type;
    private BufferedImage image;

    public static final int HEALTH_POTION = 0;
    public static final int SPEED_POTION = 1;
    public static final int DAMAGE_POTION = 2;
    public static final int FIRE_POTION = 3;

    public Item(TileMap tm, int type) {
        super(tm);
        this.type = type;
        try {
            createItem(this.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createItem(int type) throws IOException  {
        switch (type) {
            case 0:
                this.name = "Health Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/health_potion.gif"));
                break;
            case 1:
                this.name = "Boost Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/boost_potion.gif"));
                break;
            case 2:
                this.name = "Damage Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/damage_potion.gif"));
                break;
            case 3:
                this.name = "Fire Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/fire_potion.gif"));
                break;
        }
    }

    public BufferedImage getImage() { return this.image; }

    public String getItemName() { return this.name; }
    public String getItemNameShort() { return this.name.substring(0, 1); }
}

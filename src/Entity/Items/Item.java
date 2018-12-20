package Entity.Items;

import Entity.MapObject;
import Entity.Player;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item extends MapObject {

    private String name;
    private int type;
    private BufferedImage image;

    public Player player;

    public boolean remove;
    public static final int HEALTH_POTION = 0;
    public static final int BOOST_POTION = 1;
    public static final int DAMAGE_POTION = 2;
    public static final int FIRE_POTION = 3;

    public static final int MAX_ITEMS = 3;

    public Item(TileMap tm, int type, Player player) {
        super(tm);

        width = 15;
        height = 15;
        cwidth = 10;
        cheight = 10;

        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        remove = false;

        this.type = type;
        this.player = player;

        try {
            createItem(this.type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createItem(int type) throws IOException  {
        switch (type) {
            case HEALTH_POTION:
                this.name = "Health Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/health_potion.gif"));
                break;
            case BOOST_POTION:
                this.name = "Boost Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/boost_potion.gif"));
                break;
            case DAMAGE_POTION:
                this.name = "Damage Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/damage_potion.gif"));
                break;
            case FIRE_POTION:
                this.name = "Fire Potion";
                image = ImageIO.read(getClass().getResourceAsStream("/Items/fire_potion.gif"));
                break;
        }
    }

    public static Item dropItemFromEnemy(TileMap tm, int type, Player player) {
        Item item = null;
        switch (type) {
            case HEALTH_POTION :
                item = new HealthPotion(tm, type, player);
                break;
            case DAMAGE_POTION :
                item = new DamagePotion(tm, type, player);
                break;
            case BOOST_POTION :
               item = new BoostPotion(tm, type, player);
                break;
        }
        return item;
    }

    public BufferedImage getImage() { return this.image; }

    public String getItemName() { return this.name; }
    public String getItemNameShort() { return this.name.substring(0, 1); }
    public boolean shouldRemove() { return remove; }
    public void removeItemFromWorld(boolean r) { this.remove = r; }

    public boolean executeItemEffect() {
        return false;
    }

    private void getNextPosition() {
        if (falling) {
            dy += fallSpeed;
        }
    }

    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
    }
    public void draw(Graphics2D g) {
        setMapPosition();
        g.drawImage(image,
                (int)(x + xmap - width / 2),
                (int)(y + ymap - height / 2),
                null);
    }
}

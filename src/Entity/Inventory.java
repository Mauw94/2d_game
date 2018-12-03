package Entity;

import Main.GamePanel;
import TileMap.TileMap;

import java.awt.*;
import java.util.ArrayList;

public class Inventory extends MapObject {

    private Player player;
    private Font font;
    public ArrayList<Item> inventory;
    private int currentItem = 0;

    public Inventory(TileMap tm, Player p) {
        super(tm);
        this.player = p;
        inventory = new ArrayList<Item>();
        font = new Font("Arial", Font.PLAIN, 12);
    }

    public ArrayList<Item> getInventory() { return inventory; }
    public void addInventoryItem(Item item) { inventory.add(item); }
    public String inventoryBelongsTo() { return this.player.getPlayerName(); }

    public void useItem() {
        if (this.inventory.size() == 0) return;
        // benefits of the item onto the player or world

        // remove the item
        this.inventory.remove(currentItem);

        // remove item from player
        this.player.removeInventoryItem(currentItem);
    }

    private Item getCurrentItem() {
        return this.inventory.get(currentItem);
    }

    private void loadImages() {

    }

    public void draw(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        for (int i = 0; i < inventory.size(); i++) {
            if (i == currentItem) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawRect((GamePanel.WIDTH - 25) - i * 18, 15, 15, 15);
            g.drawImage(inventory.get(i).getImage(),(GamePanel.WIDTH - 25) - i * 18, 15, null);
            g.drawString(inventory.get(i).getItemNameShort(), (GamePanel.WIDTH - 25) - i * 18, 25);
        }
    }

    public void cycleLeft() {
        currentItem++;
        if (currentItem > inventory.size() - 1) {
            currentItem = 0;
        }
    }

    public void cycleRight() {
        currentItem--;
        if (currentItem < 0) {
            currentItem = inventory.size() - 1;
        }
    }

}

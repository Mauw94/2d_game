package Entity.Items;

import Entity.Player;
import TileMap.TileMap;

import java.awt.*;

public class HealthPotion extends Item {

    public int hp;
    private Player player;

    public HealthPotion(TileMap tm, int type, Player player) {
        super(tm, type);

        this.player = player;
        hp = 1;
    }

    @Override
    public boolean executeItemEffect() {
        if (player.getHealth() == player.getMaxHealth()) return false;
        player.addHealth(hp);
        return true;
    }

    public void update() {
        super.update();
    }

    public void draw(Graphics2D g) { super.draw(g); }
}

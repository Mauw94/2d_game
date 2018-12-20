package Entity.Items;

import Entity.Player;
import TileMap.TileMap;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class DamagePotion extends Item {

    private int bonusDamage;
    private boolean bonusStarted;

    public DamagePotion(TileMap tm, int type, Player player) {
        super(tm, type, player);

        bonusStarted = false;

        this.bonusDamage = 3;
    }

    @Override
    public boolean executeItemEffect() {
        player.addBonusDamage(this.bonusDamage);
        bonusStarted = true;
        if (bonusStarted) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    player.resetDamageNumbers();
                    bonusStarted = false;
                }
            };
            timer.schedule(task, 3500);
        }
        return true;
    }

    public void update() { super.update(); }
    public void draw(Graphics2D g) { super.draw(g); }
}

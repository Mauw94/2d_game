package Entity.Items;

import Entity.Player;
import TileMap.TileMap;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class BoostPotion extends Item {

    public double boost;
    public double startBoost;
    private Player player;
    private boolean bonusStarted;

    public BoostPotion(TileMap tm, int type, Player p) {
        super(tm, type);

        this.player = p;

        startBoost = 0.5;
        boost = 1.5;

        bonusStarted = false;
    }

    @Override
    public boolean addBonus() {
        player.setMaxSpeed(2.2);
        bonusStarted = true;
        if (bonusStarted) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    player.setMaxSpeed(1.6);
                    bonusStarted = false;
                }
            };
            timer.schedule(task, 4000);
        }
        return true;
   }

   public void update() {
       super.update();
   }

   public void draw(Graphics2D g) {
        super.draw(g);
   }

}

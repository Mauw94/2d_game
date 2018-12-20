package Entity.Items;

import Entity.Player;
import TileMap.TileMap;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class BoostPotion extends Item {

    private double boost;
    private boolean bonusStarted;

    public BoostPotion(TileMap tm, int type, Player player) {
        super(tm, type, player);

        boost = 2.2;

        bonusStarted = false;
    }

    @Override
    public boolean executeItemEffect() {
        player.setMaxSpeed(this.boost);
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
        if (bonusStarted) {
            g.setColor(Color.RED);
            g.drawString("SPEED BOOST ACTIVATED!", 50, 50);
        }
       super.draw(g);
   }

}

package WallGame;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet implements Collideable{
    private int x;
    private int y;
    private int dy = Settings.BULLETSPEED;
    private int bulletSize;
    
    public Bullet(int x){
        this.x = x;
        this.y = Settings.HEIGHT - 70;
        this.bulletSize = Settings.BULLETSIZE;
    }
    
    public void move(){
        this.y -= dy;
    }

    public int getY() {
        return y;
    }
    
    
    public void paint(Graphics2D g){
        g.setColor(Color.red);
        g.fillRect(x, y, bulletSize, bulletSize);
        g.setColor(Color.black);
    }
    
    public Rectangle getRect(){
        return new Rectangle(x, y, bulletSize, bulletSize);
    }
}

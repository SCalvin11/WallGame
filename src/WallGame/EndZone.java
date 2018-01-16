package WallGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EndZone implements Paintable, Collideable{
    
    public void paint(Graphics2D g){
        g.setColor(Color.red);
        g.drawLine(Settings.WIDTH - 50, 0, Settings.WIDTH - 50, Settings.HEIGHT);
        g.setColor(Color.black);
    }
    
    public Rectangle getRect(){
        return new Rectangle(Settings.WIDTH - 50, 0, 50, Settings.HEIGHT);
    }
}

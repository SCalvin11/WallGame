package WallGame;

//fix walls speeding up and shit

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Wall implements Moveable, Paintable{
    private int x;
    private int speed;
    public boolean toDelete;
    private Random rand;
    private int gapStart;
    private int gapEnd;
    
    public Wall(int speed){
        this.x = Settings.WIDTH;
        this.speed = speed;
        this.toDelete = false;
        this.rand = new Random();
        this.gapStart = rand.nextInt(Settings.HEIGHT - 150) + 40;
        this.gapEnd = gapStart + Settings.WALLGAPSIZE;
    }
    
    public void move(){
        this.x -= speed;
    }
    
    public void paint(Graphics2D g){
        boundsCheck();
        g.fillRect(x, 0, Settings.WALLWIDTH, gapStart);
        g.fillRect(x, gapEnd, Settings.WALLWIDTH, (Settings.HEIGHT - gapEnd));
    }
    
    public List<Rectangle> getRect(){
        List<Rectangle> toReturn = new ArrayList<Rectangle>();
        toReturn.add( new Rectangle(x, 0, Settings.WALLWIDTH, gapStart));
        toReturn.add(new Rectangle(x, gapEnd, Settings.WALLWIDTH, (Settings.HEIGHT - gapEnd)));
        return toReturn;
    }
    
    public void boundsCheck(){
        if (x < (120 - Settings.WALLWIDTH)){
            toDelete = true;
        }
    }
}

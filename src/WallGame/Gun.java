package WallGame;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;

public class Gun implements Paintable, Moveable{
    private int x;
    private int y;
    private double dx;
    private int dy;
    private double speed;
    private Random rand;
    private int fireRate;
    private int fireTracker;
    private List<Bullet> bullets;
    private Player player;
    
    public Gun(Player player){
        rand = new Random();
        this.bullets = new ArrayList<Bullet>();
        this.speed = Settings.GUNSPEED 
                + (rand.nextDouble() / (1.0 / Settings.GUNSPEEDMODIFIER));
        this.dx = speed * (rand.nextBoolean() ? 1 : -1);;
        
        
        this.x = rand.nextInt(Settings.WIDTH - 40) + 20;
        this.y = Settings.HEIGHT - 62;
        this.fireRate = Settings.GUNFIRERATEBASE + 
                rand.nextInt(Settings.GUNFIRERATEMODIFIER);
        
        this.fireTracker = 0;
        this.player = player;
    }
    
    public void move(){
        if (this.fireTracker >= 360){
            this.fireTracker = 0;
        } else {
            this.fireTracker += 1;
        }
            
        if (this.x < (Settings.WIDTH / 10)){
            this.dx = speed;
        } else if (this.x > Settings.WIDTH - 30){
            this.dx = -1 * speed;
        }
        
        if ((player.getX() > (this.x - Settings.DISTANCETOFIREONPLAYER) && 
                player.getX() < (this.x + Settings.DISTANCETOFIREONPLAYER)) 
                && this.fireTracker % this.fireRate == 0){
            bullets.add(new Bullet(x));
        }
        
        x += dx;
    }
    
    public void cullBullets(){
        List<Bullet> toDelete = new ArrayList<Bullet>();
        for (Bullet bullet : bullets){
            if (bullet.getY() < 0){
                toDelete.add(bullet);
            }
        }
        
        for (Bullet bullet : toDelete){
            bullets.remove(bullet);
        }
    }
    
    public List<Bullet> getBullets(){
        return bullets;
    }
    
    public void paint(Graphics2D g){
        g.setColor(Color.orange);
        g.fillRect(x, y, 8, 15);
        g.setColor(Color.black);
    }
}

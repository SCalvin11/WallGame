package WallGame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player implements Paintable, Moveable, Collideable{
    private int x;
    private int y;
    private int speed;
    private int dx;
    private int dy;
    private int size;
    private int health;
    private HealthLabel healthLabel;
    public boolean lostHealth;

    public Player() {
        resetLocation();
        this.speed = Settings.PLAYERSPEED;
        this.dy = 0;
        this.dx = 0;
        this.size = Settings.PLAYERSIZE;
        this.health = Settings.PLAYERSTARTINGHEALTH;
        this.healthLabel = new HealthLabel(this);
        this.lostHealth = false;
    }
    
    public void resetLocation(){
        this.x = Settings.PLAYERSTARTINGLOCATION[0];
        this.y = Settings.PLAYERSTARTINGLOCATION[1];
    }

    public int getHealth() {
        return health;
    }

    public HealthLabel getHealthLabel() {
        return healthLabel;
    }
 
    
    public void move(){
        if (x < 15) {
            x = 16;
            dx = 0;
        } else if (x > Settings.WIDTH - 30){
            x = Settings.WIDTH - 30;
            dx = 0;
        }
        
        if (y < 5){
            y = 5;
            dy = 0;
        } else if (y > Settings.HEIGHT - 60){
            y = Settings.HEIGHT - 60;
            dy = 0;
        }
        x -= dx;
        y -= dy;
    }
    
    public void paint(Graphics2D g){
        g.fillRect(x, y, size, size);
    }
    
    public Rectangle getRect(){
        return new Rectangle(x, y, size, size);
    }
    
    public void loseOneHealth(){
        lostHealth = true;
        health--;
        
        if (health <= 0){
            System.out.println("Game Over");
            System.exit(0);
        }
    }

    public int getX() {
        return x;
    }
    
    
    
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_D ||
                e.getKeyCode() == KeyEvent.VK_RIGHT){
            dx = -1 * speed;
        }
        if (e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_LEFT){
            dx = speed;
        }
        if (e.getKeyCode() == KeyEvent.VK_W ||
                e.getKeyCode() == KeyEvent.VK_UP){
            dy = speed;
        }
        if (e.getKeyCode() == KeyEvent.VK_S ||
                e.getKeyCode() == KeyEvent.VK_DOWN){
            dy = -1 * speed;
        }
    }
    
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_D ||
                e.getKeyCode() == KeyEvent.VK_LEFT ||
                e.getKeyCode() == KeyEvent.VK_RIGHT){
            dx = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_S ||
                e.getKeyCode() == KeyEvent.VK_W ||
                e.getKeyCode() == KeyEvent.VK_UP ||
                e.getKeyCode() == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
}

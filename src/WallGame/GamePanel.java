package WallGame;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;


public class GamePanel extends JPanel{
    private static int wallTimer = 0;
    private static int difficulty = 0;
    
    private Player player;
    private EndZone end;
    private Wall wall;
    private PointLabel pointLabel;
    private Collision collision;
    private int score = 0;
    
    private List<Paintable> toPaint;
    private List<Moveable> toMove;
    private List<Collideable> toCollide;
    private List<Gun> guns;
    private List<Wall> walls;

    public GamePanel() {
        KeyListener listener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){  
            }
            
            @Override
            public void keyPressed(KeyEvent e){
               player.keyPressed(e);
            }
            
            @Override
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
//                System.out.println("keyReleased " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        };
        setFocusable(true);
        addKeyListener(listener);
        
        this.toMove = new ArrayList<Moveable>();
        this.toPaint = new ArrayList<Paintable>();
        this.guns = new ArrayList<Gun>();
        this.walls = new ArrayList<Wall>();
        this.toCollide = new ArrayList<Collideable>();
        
        this.player = new Player();
        this.end = new EndZone();
        this.pointLabel = new PointLabel(this);
        
        toPaint.add(player);
        toPaint.add(end);
        
        toMove.add(player);
        
        this.collision = new Collision(toCollide, player, end, walls, this);
    }

    public int getScore() {
        return score;
    }
    
    public void incrementScore(){
        score++;
    }

    public PointLabel getPointLabel() {
        return pointLabel;
    }
    
    
    public void maintainWalls(){
        
        List<Wall> toDelete = new ArrayList<Wall>();
        for (Wall wall : walls){
            if (wall.toDelete){
                toDelete.add(wall);
            }
        }
        for (Wall wall : toDelete){
            walls.remove(wall);
            toPaint.remove(wall);
            toMove.remove(wall);
            toCollide.remove(wall);
        }
        
        if (walls.size() < Settings.NUMBEROFWALLS){
            if ((wallTimer % Settings.TIMEBETWEENWALLS) == 0){
                Wall wallo = new Wall(Settings.WALLSTARTINGSPEED /*+ difficulty*/);
                walls.add(wallo);
                toPaint.add(wallo);
                toMove.add(wallo);
                wallTimer = 0;
            }
        }

        wallTimer++;
    }
    
    public void maintainGuns(){
        while (guns.size() < Settings.NUMBEROFGUNS){
            Gun gunny = new Gun(player);
            guns.add(gunny);
            toPaint.add(gunny);
            toMove.add(gunny);
        }
    }
    
    public void moveEverything(){
        for (Moveable thing : toMove){
            thing.move();
        }
        
        int i = 0;
        for (Gun gun : guns){
            gun.cullBullets();
            for (Bullet bill : gun.getBullets()){
                i++;
                bill.move();
            }
        }
    }
    
    public void paintEverything(Graphics2D g){
        for (Paintable thing : toPaint){
            thing.paint(g);
        }
        
        for (Gun gun : guns){
            for (Bullet bill : gun.getBullets()){
                bill.paint(g);
            }
        }
    }
    
    public HealthLabel getPlayerHealthLabel(){
        return player.getHealthLabel();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
//        if (score > 5 && score <= 10){
//            difficulty = 1;
//        } else if (score > 10 && score <= 15){
//            difficulty = 2;
//        }
        
        maintainGuns();
        maintainWalls();
        
        moveEverything();
        paintEverything(g2d);
        
        if (player.lostHealth){
            g2d.setColor(Color.red);
            g2d.fillRect(0, 0, Settings.WIDTH, Settings.HEIGHT);
            g2d.setColor(Color.black);
            player.lostHealth = false;
        }
        
        collision.detectCollisions(guns);
    }
}

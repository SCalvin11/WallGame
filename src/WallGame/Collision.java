package WallGame;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class Collision {
    public List<Collideable> colliders;
    public List<Wall> walls;
    public Player player;
    public EndZone end;
    public GamePanel game;

    public Collision(List<Collideable> colliders, Player player, EndZone end,
            List<Wall> walls, GamePanel game){
        this.colliders = colliders;
        this.player = player;
        this.end = end;
        this.game = game;
        this.walls = walls;
    }
    
    public void detectCollisions(List<Gun> guns){
        for (Gun gun : guns){
            for (Bullet bill : gun.getBullets())
                if (player.getRect().intersects(bill.getRect())){
                    player.loseOneHealth();
                    player.resetLocation();
                }
        }
        
        for (Collideable collider : colliders){
            if (player.getRect().intersects(collider.getRect())){
                player.loseOneHealth();
                player.resetLocation();
            }
        }
        
        for (Wall wall : walls){
            for (Rectangle rect : wall.getRect()){
                if (player.getRect().intersects(rect)){
                    player.loseOneHealth();
                    player.resetLocation();
                }
            }
        }
        
        if (player.getRect().intersects(end.getRect())){
            game.incrementScore();
            player.resetLocation();
        }
    }
}

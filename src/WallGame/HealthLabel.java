package WallGame;

import javax.swing.JLabel;

public class HealthLabel extends JLabel {
    private int health;
    private Player player;
    
    public HealthLabel(Player player){
        this.player = player;
    }
    
    public void setLabel(){
        this.health = player.getHealth();
        this.setText("Health: " + health);
    }
}
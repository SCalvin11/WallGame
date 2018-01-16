package WallGame;

import javax.swing.JLabel;

public class PointLabel extends JLabel {
    private int score;
    private GamePanel panel;
    
    public PointLabel(GamePanel panel){
        this.panel = panel;
    }
    
    public void setLabel(){
        this.score = panel.getScore();
        this.setText("Points: " + score);
    }
}

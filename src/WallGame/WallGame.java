package WallGame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WallGame {

    public static void main(String[] args) {
       JFrame frame = new JFrame("Wall Game");
       GamePanel game = new GamePanel();
       frame.add(game);
       game.add(game.getPointLabel());
       game.add(game.getPlayerHealthLabel());
       frame.setSize(Settings.WIDTH, Settings.HEIGHT);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       while (true){
           game.getPointLabel().setLabel();
           game.getPlayerHealthLabel().setLabel();
           game.repaint();
           
           try {
               Thread.sleep(10);
           } catch (Exception e) {
               System.out.println(e);
           }
       }
    }
    
}

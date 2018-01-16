package WallGame;

public class Settings {
    // height and width of game window
    public static final int HEIGHT = 500;
    public static final int WIDTH = 1000;
    
    public static final int PLAYERSPEED = 3;
    public static final int PLAYERSIZE = 10;
    public static final int PLAYERSTARTINGHEALTH = 5;
    public static final int[] PLAYERSTARTINGLOCATION = {50, (HEIGHT / 2) - 40};
    
    /*  - distance to fire on player is how far from the player, horizontally,
            the guns will begin to fire, 
        - fire rate modifier is the maximum of a range (0 to GUNFIRERATEMODIFIER),
            a random integer in this range is added to each guns fire rate
        - higher GUNSPEEDMODIFIER will make guns have a larger range of speeds
    */
    public static final int NUMBEROFGUNS = 3;
    public static final double GUNSPEED = 1.0;
    public static final double GUNSPEEDMODIFIER = 8.0;
    public static final int BULLETSPEED = 1;
    public static final int BULLETSIZE = 3;
    public static final int DISTANCETOFIREONPLAYER = 800;
    public static final int GUNFIRERATEBASE = 10;
    public static final int GUNFIRERATEMODIFIER = 80;
    
    // WALLGAPSIZE is the size of vertical holes in walls
    public static final int NUMBEROFWALLS = 100;
    public static final int TIMEBETWEENWALLS = 200;
    public static final int WALLWIDTH = 20;
    public static final int WALLSTARTINGSPEED = 1;
    public static final int WALLGAPSIZE = 80;
}

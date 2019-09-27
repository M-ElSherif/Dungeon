package dungeon;

/**
 *
 * @author thesh Player class.
 */
import java.util.*;

public class Player extends Character {

    private int x;      //player's x coord on dungeon map
    private int y;      // player's y coord on dungeon map

    public Player(int x, int y) {
        super(x,y);
    }

    // GETTER. Retutns the player's current x position on the map
    public int getX() {
        return this.x;
    }

    // GETTER. Retutns the player's current y position on the map
    public int getY() {
        return this.y;
    }
    
    public void moveLeft() {
        this.x--;
    }
    
    public void moveRight() {
        this.x++;
    }
    
    public void moveUp() {
        this.y--;
    }
    
    public void moveDown() {
        this.y++;
    }
    
    // ABSTRACT METHOD
    public void move(int height, int length) {
        
    }

}

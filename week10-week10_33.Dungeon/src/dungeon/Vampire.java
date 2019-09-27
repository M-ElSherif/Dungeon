package dungeon;

/**
 *
 * @author thesh Vampire class. Creates a new vampire for the dungeon game, with
 * its own x and y coords on the dungeon map. The vampires position on the map
 * are randomized starting out. The vampires move randomly in the game, and they
 * take one step for each step the player takes. If the player and a vampire run
 * into each other (even momentarily) the vampire is destroyed. If a vampire
 * tries to step outside the board, or into a place already occupied by another
 * vampire, the move is not executed
 *
 */
import java.util.*;

public class Vampire extends Character {

    private int x;
    private int y;

    // INITIAL CONSTRUCTOR. Vampires start with randomized positions with dungeon
    public Vampire(int x, int y) {
        super(x,y);
    }

    public void randomizeStart(int height, int length) {
        Random r = new Random();
        this.setX(r.nextInt(length));
        this.setY(r.nextInt(height));
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    // ABSTRACT METHOD. Random movement of vampire position within dungeon map. Vampires 
    // take 1 step for each 1 step player takes
    public void move(int height, int length) {
        Random r = new Random();
        int rnd = r.nextInt(4) + 1;     // Four directions to randomly move 1 step in
        if (rnd == 1 && this.x > 0 && this.x < length - 1) {
            this.moveLeft();
        } else if (rnd == 2 && this.y > 0 && this.y < height - 1) {
            this.moveUp();
        } else if (rnd == 3 && this.x < length - 1) {
            this.moveRight();
        } else if (rnd == 4 && this.y < height - 1) {
            this.moveDown();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vampire other = (Vampire) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }


    
    
}

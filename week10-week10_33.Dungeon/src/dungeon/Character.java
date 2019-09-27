/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

/**
 *
 * @author thesh
 */
public abstract class Character {

    private int x;      //character's x coord on dungeon map
    private int y;      // character's y coord on dungeon map

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
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
    public abstract void move(int height, int length);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
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
        final Character other = (Character) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
}

package dungeon;

/**
 *
 * @author thesh The game dungeon, shaped in a rectangle
 */
import java.util.*;

public class Dungeon {

    private int height;     // height of the dungeon vertical
    private int length;     // length of the dungeon horizontal
    private int vampires;   // number of vampires in the dungeon
    private int moves;      // initial number of moves player has before lamp runs out
    private boolean vampiresMove;   // false if vampires do not move
    private Player player;      // player character
    private Vampire vampire;
    private List<Character> vampireList;  // vampires in the dungeon

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.vampires = vampires;
        this.length = length;
        this.height = height;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.player = new Player(0, 0);     // player character starts a 0,0 pos
        this.vampire = new Vampire(this.height, this.length);
        this.vampireList = new ArrayList<Character>();
    }

    // METHOD. Check no 2 vampires have the same position
    public boolean checkVampireCollision(Character v) {
        int i = 0;  // number of collision occurences
        for (Character vampire : this.vampireList) {
            if (v.equals(vampire)) {
                i++;
            }
        }
        // if 2 or more vampires have the same positions, return true
        if (i > 1) {
            return true;
        }
        // else no collision
        return false;
    }

    // METHOD. Create new vampires and add them to the vampires list. 
    // Calls the Dungeon class checkVampireCollision to ensure no 2 vampires
    // occupy same position on dungeon map
    public void addVampires() {
        for (int i = 1; i <= vampires; i++) {
            while (true) {
                Vampire v = new Vampire(0, 0);
                v.randomizeStart(height, length);
                // Adds vampire only if it has a unique position
                if (!this.vampireList.contains(v)) {
                    this.vampireList.add(v);
                    break;
                }
            }
        }
    }

    // METHOD. Checks if player collides with a vampire and deletes said vampire
    public void deleteVampire() {
        int playX = this.player.getX();
        int playY = this.player.getY();
        List<Character> deletedVampires = new ArrayList<Character>();
        for (Character vampire : this.vampireList) {
            if (playX == vampire.getX() && playY == vampire.getY()) {
                deletedVampires.add(vampire);
            }
        }
        this.vampireList.removeAll(deletedVampires);
    }

    // METHOD. Move the vampires around the map randomly in 1 step. If a vampire
    // will coincide with another, don't move the vampire
    public void moveVampires() {
        if (this.vampiresMove) {
            for (Character v : this.vampireList) {
                while (true) {
                    v.move(height, length);
                    if (!this.checkVampireCollision(v)) {
                        break;
                    }
                }
            }
        }
    }

    // METHOD. Takes the user input directions "wasd", and loops through the input
    // to determine the new position of the player on the dungeon map
    // We add functionality so each move player makes the vampires move
    public void playerMove() {
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            // the conditions such as > 0 and > height - 1 ensure the player cannot
            // move beyond the boundaries of the dungeon map
            if (ch == 'w' && this.player.getY() > 0) {
                this.player.moveUp();
                this.moveVampires();
            } else if (ch == 's' && this.player.getY() < height - 1) {
                this.player.moveDown();
                this.moveVampires();
            } else if (ch == 'a' && this.player.getX() > 0) {
                this.player.moveLeft();
                this.moveVampires();
            } else if (ch == 'd' && this.player.getX() < length - 1) {
                this.player.moveRight();
                this.moveVampires();
            }
        }
        this.moves--;
    }

    // METHOD. Prints out the positions of player and vampires
    public void printCharacterCoords() {
        System.out.println("@ " + this.player.getX() + " " + this.player.getY());
        for (Character vampire : this.vampireList) {
            System.out.println("v " + vampire.getX() + " " + vampire.getY());
        }
    }

    // METHOD. Prints the remaining number of moves
    public void printNumberOfMoves() {
        System.out.println(this.moves);
    }

    // METHOD. Print the dungeon map with characters on it based on their coords
    public void printDungeonMap() {
        System.out.println("");     // empty line
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                Boolean charPrinted = false;    // boolean to check if a char is printed
                // Printing the player char
                if (this.player.getX() == x && this.player.getY() == y) {
                    System.out.print("@");
                    charPrinted = true;
                }
                // Printing the vampire chars
                for (Character v : this.vampireList) {
                    if (v.getX() == x && v.getY() == y) {
                        System.out.print("v");
                        charPrinted = true;
                        break;
                    }
                }
                if (!charPrinted) {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    // METHOD. Check if win state has been achieved
    public boolean checkWinState() {
        if (this.moves > 0) {
            if (this.vampireList.size() == 0) {
                System.out.println("YOU WIN");
                return true;
            }
        } else if (this.moves == 0) {
            if (this.vampireList.size() > 0) {
                System.out.println("YOU LOSE");
                return true;
            }
        }
        return false;
    }

// METHOD. Runs the game to start it
    public void run() {
        // Add vampires and start the game interface
        this.addVampires();
        this.printNumberOfMoves();
        System.out.println("");     // empty line
        this.printCharacterCoords();
        this.printDungeonMap();
        // Execute player interaction 
        while (this.moves > 0) {
            System.out.println(""); // empty line
            this.playerMove();
            this.deleteVampire();
            if (this.checkWinState()) {
                break;
            }
            this.printNumberOfMoves();
            System.out.println(""); // empty line
            this.printCharacterCoords();
            this.printDungeonMap();
        }
    }
}

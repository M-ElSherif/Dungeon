/**
 * 
 * NEXT
 * 3. If vampire tries to move into place occupied by another vampire, 
 *    vampire movement does not execute
 * 4. Win state when no vampires remaining
 * 5. Fail state when at least 1 vampire remaining
 * 
 * BUGS
 * 1. Player moves 1 step, vampire takes 2 steps. Potentially issue
 *    with printDungeon() method
 */
package dungeon;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(5,5,5,5,false);
        dungeon.run();
    }
}

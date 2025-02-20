package Game;

import java.io.Serializable;

public class SpellList implements Serializable {
    
    //spells
    //Spells have a high range of damage, but be sparingly used
    static Spell firebolt = new Spell("Firebolt", "fire", 1, 0, 4, 0, 4);
    static Spell icebolt = new Spell("Icebolt", "ice", 1, 0, 4, 0, 4);
    static Spell earthbolt = new Spell("Earthbolt", "earth", 2, 0, 4, 1, 5);
    static Spell waterbolt = new Spell("Waterbolt", "water", 1, 1, 3, 1, 3);
    static Spell windstrike = new Spell("Wind Strike", "Wind", 1, 1, 3, 1, 3);
}

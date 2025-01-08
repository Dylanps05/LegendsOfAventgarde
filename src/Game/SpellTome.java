package Game;

public class SpellTome extends Item {

    //fields
    private String name;
    private int value;
    private String type;
    private Spell spellLearned;

    //constructor
    public SpellTome (String name, int value, String type, Spell spellLearned) {
        super(name, value);
        this.type = type;
        this.spellLearned = spellLearned;
    }

    //methods
    @Override
    public String toString() {
        return (getName() + "\n"
            + "Teaches " + getSpellLearned().toString());
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spell getSpellLearned() {
        return spellLearned;
    }

    public void setSpellLearned(Spell spellLearned) {
        this.spellLearned = spellLearned;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    
    
}
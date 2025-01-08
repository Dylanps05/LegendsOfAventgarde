package Game;

public class Ammo extends Item {
    //fields
    private int damage;
    private int count;

    public Ammo (String name, int value, int damage, int count) {
        super(name, value);
        this.damage = damage;
        this.count = count;
    }

    //methods
    @Override
    public String toString() {
        return(getName() + "\n" + getValue() + " Gold each\n" + getDamage() + " Damage\nCount: " + getCount());
    }

    public String getType() {
        return "Ammo";
    }

    //getters and setters
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    
    
}

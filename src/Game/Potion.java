package Game;

public class Potion extends Item {
    
    //fields
    public int heal = 0;
    public int strIncrease;
    public int attIncrease;
    public int defIncrease;
    public int rangeIncrease;
    public int magicIncrease;
    public int manaRestore;
    public boolean antipoison;

    //consturctor
    public Potion(String name, int value, int heal, int strIncrease, int attIncrease, int defIncrease, int manaRestore) {
        super(name, value);
        this.heal = heal;
        this.strIncrease = strIncrease;
        this.attIncrease = attIncrease;
        this.defIncrease = defIncrease;
        this.manaRestore = manaRestore;
    }

    //methods
    @Override
    public String toString() {
        String string = (getName() + "\n" + getValue() + " Gold");
        if (this.heal > 0) 
            string += ("\nHeals " + getHeal() + " HP");
        if (this.manaRestore > 0)
            string += ("\nRestores " + getManaRestore() + " mana");
        if (this.strIncrease > 0)
            string += ("\nStrength Increase: " + getStrIncrease());
        if (this.attIncrease > 0)
            string += ("\nAttack Increase: " + getAttIncrease());
        if (this.defIncrease > 0)
            string += ("\nDefense Increase: " + getDefIncrease());

        string += "\nCan only be used in combat";
        return string;
    }

    // public void drinkPotion() {     
        
    // }

    public String getType() {
        return "Potion";
    }

    //getters and setters
    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getStrIncrease() {
        return strIncrease;
    }

    public void setStrIncrease(int strIncrease) {
        this.strIncrease = strIncrease;
    }

    public int getAttIncrease() {
        return attIncrease;
    }

    public void setAttIncrease(int attIncrease) {
        this.attIncrease = attIncrease;
    }

    public int getDefIncrease() {
        return defIncrease;
    }

    public void setDefIncrease(int defIncrease) {
        this.defIncrease = defIncrease;
    }

    public int getManaRestore() {
        return manaRestore;
    }

    public void setManaRestore(int manaRestore) {
        this.manaRestore = manaRestore;
        
    }

    public int getRangeIncrease() {
        return rangeIncrease;
    }

    public void setRangeIncrease(int rangeIncrease) {
        this.rangeIncrease = rangeIncrease;
    }

    public int getMagicIncrease() {
        return magicIncrease;
    }

    public void setMagicIncrease(int magicIncrease) {
        this.magicIncrease = magicIncrease;
    }

    public boolean isAntipoison() {
        return antipoison;
    }

    public void setAntipoison(boolean antipoison) {
        this.antipoison = antipoison;
    }

    

    
    
}

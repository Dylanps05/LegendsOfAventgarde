package Game;

public class Armor extends Item {
    /* ARMOR COMPONENTS
    * All can give attack/strength/mana/health/etc.
    * Head
    * Chestpiece
    * Legs
    * Boots
    * Gloves
    * Shield is also considered armor, however takes an inventory slot
    *      NOT armor slot.
    * Armor takes a backpack slot until equipped
    * If you replace a helmet with another helmet, old helmet stays in backpack unless dropped or sold.
    */
    
    //fields
    public int giveDef;
    private String armorType;

    //optional
    private int giveHealth;
    private int giveRanged;
    private int giveMagic;
    private int giveMana;
    private int giveStrength;
    private int giveAttack;

    //constructor
    //general armor
    public Armor(String name, String type, int value, int giveDef) {
        super(name, value);
        this.armorType = type;
        this.giveDef = giveDef;
    }
    //melee armor
    public Armor(String name, String type, int value, int giveDef, int giveStrength, int giveAttack) {
        super(name, value);
        this.armorType = type;
        this.giveDef = giveDef;
        this.giveStrength = giveStrength;
        this.giveAttack = giveAttack;
    }
    //magic armor
    public Armor(String name, int value, String type, int giveDef, int giveMagic, int giveMana) {
        super(name, value);
        this.armorType = type;
        this.giveDef = giveDef;
        this.giveStrength = giveMagic;
        this.giveAttack = giveMana;
    }
    //ranged armor
    public Armor(String name, String type, int value, int giveDef, int giveRanged) {
        super(name, value);
        this.armorType = type;
        this.giveDef = giveDef;
        this.giveRanged = giveRanged;
    }
    
    //methods
    @Override
    public String toString() {
        String string = getName() + "\n" + getValue() + " Gold\n+" + getGiveDef() + " defense\n" + getType();
        
        if(this.giveHealth > 0)
            string += "\n+" + getGiveHealth() + " Health";
        if(this.giveAttack > 0)
            string += "\n+" + getGiveAttack() + " Attack";
        if(this.giveStrength > 0)
            string += "\n+" + getGiveStrength() + " Strength";
        if(this.giveRanged > 0)
            string += "\n+" + getGiveRanged() + " Ranged";
        if(this.giveMagic > 0)
            string += "\n+" + getGiveMagic() + " Magic";
        if(this.giveMana > 0)
            string += "\n+" + getGiveMana() + " Mana";

        return string;
    }

    public String getType() {
        String string = "";
        if(armorType == "Helmet")
            string = "Helmet";
        if(armorType == "Chestplate")
            string = "Chestplate";
        if(armorType == "Leggings")
            string = "Leggings";
        if(armorType == "Boots")
            string = "Boots";
        if(armorType == "Gloves")
            string = "Gloves";
        if(armorType == "Shield")
            string = "Shield";
        if(armorType == "Necklace")
            string = "Necklace";
        if(armorType == "Ring")
            string = "Ring";

        return string;
    }

    //getters and setters
    public int getGiveDef() {
        return giveDef;
    }

    public void setGiveDef(int giveDef) {
        this.giveDef = giveDef;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public int getGiveHealth() {
        return giveHealth;
    }

    public void setGiveHealth(int giveHealth) {
        this.giveHealth = giveHealth;
    }

    public int getGiveRanged() {
        return giveRanged;
    }

    public void setGiveRanged(int giveRanged) {
        this.giveRanged = giveRanged;
    }

    public int getGiveMagic() {
        return giveMagic;
    }

    public void setGiveMagic(int giveMagic) {
        this.giveMagic = giveMagic;
    }

    public int getGiveStrength() {
        return giveStrength;
    }

    public void setGiveStrength(int giveStrength) {
        this.giveStrength = giveStrength;
    }

    public int getGiveAttack() {
        return giveAttack;
    }

    public void setGiveAttack(int giveAttack) {
        this.giveAttack = giveAttack;
    }

    public int getGiveMana() {
        return giveMana;
    }

    public void setGiveMana(int giveMana) {
        this.giveMana = giveMana;
    }

    
    
}

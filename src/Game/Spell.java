package Game;

import java.io.Serializable;
import java.util.Random;

public class Spell implements Serializable{
    /* Have different types of spell
    * Raise attack, defense, etc.
    */

    //fields
    Random r = new Random();
    private String spellName;
    private String spellType;
    private int spellMinAttack;
    private int spellMaxAttack;
    private int spellMinDamage;
    private int spellMaxDamge;
    private int damage;
    private int spellCost;
    // private int raiseAttack;
    // private int raiseDef;
    // private int lowerAttack;
    // private int lowerDef;

    //constructor
    public Spell(String name, String type, int cost, int minAttack, int maxAttack, int minDamage, int maxDamage) {
        this.spellName = name;
        this.spellType = type;
        this.spellCost = cost;
        this.spellMinAttack = minAttack;
        this.spellMaxAttack = maxAttack;
        this.spellMinDamage = minDamage;
        this.spellMaxDamge = maxDamage;
    }

    //methods
    @Override
    public String toString() {
        return (getSpellName() + 
            "\n" + getSpellCost() + " mana" 
            + "\n" + getSpellMinAttack() + " - " + getSpellMaxAttack() + " Attack"
            + "\n" + getSpellMinDamage() + " - " + getSpellMaxDamge() + " " + getSpellType() + " Damage");
    }

    public int rollAttack() {
        return (r.nextInt(getSpellMinAttack(), getSpellMaxAttack()));
    }
    
    public int rollDamage() {
        return (r.nextInt(getSpellMinDamage(), getSpellMaxDamge()));
    }

    public String getType() {
        return "Spell";
    }
    //getters and setters
    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public int getSpellMinDamage() {
        return spellMinDamage;
    }

    public void setSpellMinDamage(int spellMinDamage) {
        this.spellMinDamage = spellMinDamage;
    }

    public int getSpellMaxDamge() {
        return spellMaxDamge;
    }

    public void setSpellMaxDamge(int spellMaxDamge) {
        this.spellMaxDamge = spellMaxDamge;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpellCost() {
        return spellCost;
    }

    public void setSpellCost(int spellCost) {
        this.spellCost = spellCost;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public int getSpellMinAttack() {
        return spellMinAttack;
    }

    public void setSpellMinAttack(int spellMinAttack) {
        this.spellMinAttack = spellMinAttack;
    }

    public int getSpellMaxAttack() {
        return spellMaxAttack;
    }

    public void setSpellMaxAttack(int spellMaxAttack) {
        this.spellMaxAttack = spellMaxAttack;
    }

    public String getSpellType() {
        return spellType;
    }

    public void setSpellType(String spellType) {
        this.spellType = spellType;
    }
    
    
    
    
    
}

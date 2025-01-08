package Game;

import java.util.Random;

public class Weapon extends Item {
    //fields
    Random r = new Random();

    //basics
    private String weaponType;
    //private boolean ranged;
    private int minDamage;
    private int maxDamage;
    private int minAttack;
    private int maxAttack;
    private int damage;

    //optional
    boolean poisonous;
    int poisonDamage;


    //constructor
    public Weapon(String name, int value, String type, int minDamage, int maxDamage, int minAttack, int maxAttack) {
        super(name, value);
        this.weaponType = type;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
    }

    //methods
    @Override
    public String toString() {
        return(getName() + 
        "\n" + getMinAttack() + " - " + getMaxAttack() + " attack" +
        "\n" + getMinDamage() + " - " + getMaxDamage() + " " + getWeaponType() +  " damage" + 
        "\nValue: " + getValue() + " Gold");
    }

    public int rollAttack() {
        return (r.nextInt(minAttack, maxAttack));
    }

    public int rollDamage() {
        return (r.nextInt(minDamage, maxDamage));
    }

    public String getType() {
        return "Weapon";
    }

    //getters and setters
    // public boolean isRanged() {
    //     return ranged;
    // }

    // public void setRanged(boolean ranged) {
    //     this.ranged = ranged;
    //}

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(int minAttack) {
        this.minAttack = minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int maxAttack) {
        this.maxAttack = maxAttack;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    public int getPoisonDamage() {
        return poisonDamage;
    }

    public void setPoisonDamage(int poisonDamage) {
        this.poisonDamage = poisonDamage;
    }    
    
}

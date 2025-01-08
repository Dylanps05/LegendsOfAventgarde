package Game;

import java.io.Serializable;
import java.util.Random;

public class Enemy implements Serializable{
    //Initalizers
    Random r = new Random();

    //constructor fields
    //things that are public can be changed by spells and such
    private String name;
    private int level;
    public int maxHealth;
    public int curHealth = maxHealth;
    private int npcStr;
    private int npcAtt;
    public int minAttack;
    public int maxAttack;
    public int minDamage;
    public int maxDamage;
    public int defence;
    private int awardXP;
    public boolean alive = true;
    private Item[] loot;

    //optional fields
    private String resistance;
    private String immunity;
    private String vulnerable;
    // private boolean rangedResist;
    // private boolean slashResist;
    // private boolean crushResist;
    // private boolean pierceResist;
    // private boolean fireResist;
    // private boolean frostResist;
    // private boolean arcaneResist;
    private boolean isPoisonous;
    private int poisonDamage;
    private boolean posionImmunity;

    //constructor
    public Enemy(String name, int level, int health, int str, int att, int minAttack, int maxAttack, 
                int minDamage, int maxDamage, int def, int awardXP, Item[] loot) {
        this.name = name;
        this.level = level;
        this.maxHealth = health;
        this.curHealth = health;
        this.npcStr = str;
        this.npcAtt = att;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.defence = def;
        this.awardXP = awardXP;
        this.loot = loot;
        // this.immunity = immunity;
        // this.resistance = resistance;
        // this.posionImmunity = posionImmunity;
    }

    //methods
    public int npcRollAttack() {
        return (r.nextInt(minAttack + maxAttack) + npcAtt);
    }

    public int npcRollDamage() {
        return (r.nextInt(minDamage, maxDamage) + npcStr);
    }

    //getters and setters
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int npcHealth) {
        this.maxHealth = npcHealth;
    }

    public int getNpcStr() {
        return npcStr;
    }

    public void setNpcStr(int npcStr) {
        this.npcStr = npcStr;
    }

    public int getNpcAtt() {
        return npcAtt;
    }

    public void setNpcAtt(int npcAtt) {
        this.npcAtt = npcAtt;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int npcDef) {
        this.defence = npcDef;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int npcLevel) {
        this.level = npcLevel;
    }

    public int getAwardXP() {
        return awardXP;
    }

    public void setAwardXP(int awardXP) {
        this.awardXP = awardXP;
    }

    public String getName() {
        return name;
    }

    public void setName(String npcName) {
        this.name = npcName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int npcMinDamage) {
        this.minDamage = npcMinDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int npcMaxDamage) {
        this.maxDamage = npcMaxDamage;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(int npcMinAttack) {
        this.minAttack = npcMinAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int npcMaxAttack) {
        this.maxAttack = npcMaxAttack;
    }

    public int getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(int npcCurHealth) {
        this.curHealth = npcCurHealth;
    }

    public Item[] getLoot() {
        return loot;
    }

    public void setLoot(Item[] loot) {
        this.loot = loot;
    }

    // public boolean isRangedResist() {
    //     return rangedResist;
    // }

    // public void setRangedResist(boolean rangedResist) {
    //     this.rangedResist = rangedResist;
    // }

    // public boolean isSlashResist() {
    //     return slashResist;
    // }

    // public void setSlashResist(boolean slashResist) {
    //     this.slashResist = slashResist;
    // }

    // public boolean isCrushResist() {
    //     return crushResist;
    // }

    // public void setCrushResist(boolean crushResist) {
    //     this.crushResist = crushResist;
    // }

    // public boolean isPierceResist() {
    //     return pierceResist;
    // }

    // public void setPierceResist(boolean pierceResist) {
    //     this.pierceResist = pierceResist;
    // }

    // public boolean isFireResist() {
    //     return fireResist;
    // }

    // public void setFireResist(boolean fireResist) {
    //     this.fireResist = fireResist;
    // }

    // public boolean isFrostResist() {
    //     return frostResist;
    // }

    // public void setFrostResist(boolean frostResist) {
    //     this.frostResist = frostResist;
    // }

    // public boolean isArcaneResist() {
    //     return arcaneResist;
    // }

    // public void setArcaneResist(boolean arcaneResist) {
    //     this.arcaneResist = arcaneResist;
    // }

    public boolean isPosionImmunity() {
        return posionImmunity;
    }

    public void setPosionImmunity(boolean posionImmunity) {
        this.posionImmunity = posionImmunity;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean isPoisonous) {
        this.isPoisonous = isPoisonous;
    }

    public int getPoisonDamage() {
        return poisonDamage;
    }

    public void setPoisonDamage(int poisonDamage) {
        this.poisonDamage = poisonDamage;
    }

    public String getImmunity() {
        return immunity;
    }

    public void setImmunity(String immunity) {
        this.immunity = immunity;
    }

    public String getVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(String vulnerable) {
        this.vulnerable = vulnerable;
    }
    
    
    
    
    
    
    
}

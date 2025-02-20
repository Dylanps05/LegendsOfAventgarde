package Game;

import java.io.Serializable;
import java.util.Random;

public class Loot implements Serializable {
    /*
    * make a list of loot items here and add them to an array for each monster type
    * make a random that'll randomly choose loot
    * promopt player if they want to pick it up
    * if yes, add it to backback
    *   if backpack is full, ask if they want to replace anything
    *  if yes, show player backpack and ask what to replace.

    * POSSIBLE ENEMIES
    * Goblin, skeleton, spider, giant rat, wolf, etc.

    * Tiers
    * Bronze - Iron - Steel - Mythril - Adamantite - Runite
    Daggers do less damage but are more accurate
    Swords do more damage but are less accurate
    Axes are in between
    */
    
    //widlife drops
    public static Item getWolfFur() {
        return new Item("Wolf Fur", 2);
    }
    public static Item getWolfSkull() {
        return new Item("Wolf Skull", 5);
    }
    public static Item getWolfTail() {
        return new Item("Wolf Tail", 3);
    }
    public static Item getWolfFang() {
        return new Item("Wolf Fang", 4);
    }
    
    public static Item getSpiderEye() {
        return new Item("Spider Eye", 3);
    }
    public static Item getSpiderSac() {
        return new Item("Venom Sac", 3);
    }

    public static Item getRatTail() {
        return new Item("Rat Tail", 2);
    }

    public static Item getBearPaw() {
        return new Item("Bear Paw", 5);
    }
    public static Item getBearFur() {
        return new Item("Bear Fur", 3);
    }
    public static Item getBearTooth() {
        return new Item("Bear Tooth", 4);
    }


    //weapons
    public static Weapon getBronzeSword() {
        return new Weapon("Bronze Sword", 10, "slashing", 1, 2, 0, 4);
    }
    public static Weapon getTwoHandedAxe() {
        return new Weapon("Two-Handed Axe", 10, "slashing", 0, 3, 0, 3);
    }
    public static Weapon getShortBow() {
        return new Weapon("Shortbow", 5, "ranged", 1, 3, 0, 4);
    }
    public static Weapon getBronzeDagger() {
        return new Weapon("Bronze Dagger", 10, "piercing", 1, 2, 1, 3);
    }
    public static Weapon getBronzeAxe() {
        return new Weapon("Bronze Axe", 15, "slashing", 1, 3, 1, 4);
    }
    public static Weapon getIronDagger() {
        return new Weapon("Iron Dagger", 25, "piercing", 2, 3, 3, 4);
    }
    public static Weapon getIronSword() {
        return new Weapon("Iron Sword", 30, "slashing", 1, 4, 1, 4);
    }

    //ammo
    public static Ammo getArrow() {
        return new Ammo("Arrow", 2, 1, 1);
    }
    
    //armor
    public static Armor getWoodenShield() {
        return new Armor("Wooden Shield", "Shield", 5, 1);
    }
    public static Armor getBronzeShield() {
        return new Armor("Bronze Shield", "Shield", 20, 2);
    }
    public static Armor getIronShield() {
        return new Armor("Iron Shield", "Shield", 50, 3);
    }
    public static Armor getLeatherCoif() {
        return new Armor("Leather Coif", "Helmet", 25, 1);
    }
    public static Armor getLeatherTunic() {
        return new Armor("Leather Tunic", "Chestplate", 40, 2);
    }
    public static Armor getLeatherChaps() {
        return new Armor("Leather Chaps", "Leggings", 35, 2);
    }
    public static Armor getLeatherGloves() {
        return new Armor("Leather Gloves", "Gloves", 20, 1);
    }
    public static Armor getLeatherBoots() {
        return new Armor("Leather Boots", "Boots", 20, 1);
    }

    //spell tomes
    public static SpellTome getFireBolt() {
        return new SpellTome("Tome of Firebolt", 20, "Tome", SpellList.firebolt);
    }
    public static SpellTome getIceBolt() {
        return new SpellTome("Tome of Icebolt", 20, "Tome", SpellList.icebolt);
    }
    public static SpellTome getWaterBolt() {
        return new SpellTome("Tome of Waterbolt", 25, "Tome", SpellList.waterbolt);
    }
    public static SpellTome getWindStrike() {
        return new SpellTome("Tome of Wind Strike", 25, "Tome", SpellList.windstrike);
    }
    public static SpellTome getEarthbolt() {
        return new SpellTome("Tome of Earthbolt", 30, "Tome", SpellList.earthbolt);
    }

    //potions
    public static Potion getHealthPotion() {
        return new Potion("Health Potion", 5, 5, 0, 0, 0, 0);
    }
    public static Potion getManaPotion() {
        return new Potion("Mana Potion", 5, 0, 0, 0, 0, 3);
    }

    //miscellaneous
    public static Item getGold() {
        Random r = new Random();
        int roll = r.nextInt(1,5);
        return new Item("Gold", roll);
    }
    public static Item getRing() {
        return new Item("Ring", 15);
    }
    public static Item getNecklace() {
        return new Item("Necklace", 10);
    }
    public static Item getSapphire() {
        return new Item("Sapphire", 100);
    }
    public static Item getRuby() {
       return new Item("Ruby", 200);
    }
    public static Item getEmerald() {
        return new Item("Emerald", 500);
    }
    public static Item getDiamond() {
        return new Item("Diamond", 1000);
    }
    
    
}

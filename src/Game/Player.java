package Game;

import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable {
    //initalizers
    Methods method = new Methods();
    // Loot loot = new Loot();
    // SpellList spellList = new SpellList();
    //RedeemersWoods redeemersWoods = new RedeemersWoods();

    //general
    public String saveFile;
    public boolean alive = true;
   
    //location
    public String location;
    public int playerx;
    public int playery;

    //basics
    private String playerName;
    private String playerRace;
    private String playerClass; 

    //leveling
    public int level;
    public int xp = 0;
    public int levelUpXP;

    //Skills
    public int playerAtt = 1;
    public int playerStr = 1;
    public int playerMagic = 1;
    public int playerRanged = 1;

    //Attributes
    public int playerMaxHealth;
    public int playerCurHealth;
    public int playerDef = 1;
    public int playerMagicDef = 1;
    public int maxMana;
    public int curMana;

    //inventory
    public String backpackType;
    public int backpackSize = 4;
    public int spellbookSize = 4;
    public int playerGold = 0;

    //arrays
    public Item[] inventory = new Item[4];
    public Item[] backpack = new Item[backpackSize];
    public Spell[] spells = new Spell[spellbookSize];
    public Armor[] armor = new Armor[8];
    //0 - helmet, 1 - chestplate, 2 - legs, 3 - boots, 4 - gloves, 5 - shield, 6 - ring, 7 - necklace
    public Quest[] questLog = new Quest[5];


    //character creation
    public Player characterCreation(Scanner keyboard, Player player) {
        String inputString;
        boolean invalid = true;

        //tutorial
        System.out.println("Have you played before? (Y/N)");
        System.out.print("Input: ");
        inputString = keyboard.nextLine();
        do {
            if (inputString.equalsIgnoreCase("N")) {
                System.out.println("--------------------------------------------");
                System.out.println("Welcome to Legends of Aventgarde, a text based RPG. The game is" +
                                    "\nplayed by typing in inputs on the keyboard.");
                System.out.println("Let's explain the stats for your character.");
                System.out.println("Attack: This skill increases the accuracy of your attacks, making hitting enemies easier.");
                System.out.println("Strength: How hard you hit your foe. Increases damage of melee attacks.");
                System.out.println("Ranged: How deadly of a shot you are. Increases the damage of ranged attacks.");
                System.out.println("Magic: How adept you are at spell conjuring. Increases the damage of your spells.");
                System.out.println("Defense: How hard it is for enemies to hit you. Increased with armor.");
                System.out.println("Inventory: You will have up to 4 weapons you can attack with.");
                System.out.println("Backpack: To hold everything else. You begin with 4 slots, upgradable.");
                System.out.println("At mostly any point, you can type \"Menu\" to open your player menu!");
                System.out.println("Press any key to continue!");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();

                System.out.println("In addition, there are multiple types of shops: General Stores, Weapon Stores, and Leatherworkers");
                System.out.println("General stores buy things from your backpack.");
                System.out.println("Weapon stores buy things from your inventory.");
                System.out.println("Leatherworkers are able to upgrade your backpack to hold more stuff!");
                System.out.println("When you're ready to make your character, press any key.");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                invalid = false;
                }
            else if(inputString.equalsIgnoreCase("Y"))
                invalid = false;
        } while (invalid);
        invalid = true;

        //choosing a race
        System.out.println("-------------------------------------------");
        System.out.println("What race would you like to be?");
        System.out.println("Human");
        System.out.println("Humans are tough and resiliant beings, being able to thrive almost anywhere.");
        System.out.println("Starting Stats: +1 Defense, +1 Attack");
        System.out.println();

        System.out.println("Wood Elf");
        System.out.println("Wood Elves thrive in the woods, most becoming masters in hunting.");
        System.out.println("Starting Stats: +1 Ranged, +1 Attack");
        System.out.println();

        System.out.println("High Elf");
        System.out.println("High elves are naturally talented in the art of magic, with new spells coming with ease.");
        System.out.println("Starting Stats: +2 Magic");
        System.out.println();

        System.out.println("Orc");
        System.out.println("Hailing from the tough desert heat, Orcs are some of the toughest beings known.");
        System.out.println("Starting Stats: +2 Strength");
        System.out.println();

        System.out.println("Dwarf");
        System.out.println("Dwarves are hardy and strong, able to overcome almost any difficulty.");
        System.out.println("Starting Stats: +1 Attack, +1 Strength");
        System.out.println();
        System.out.println("What race do you choose?");
        System.out.print("Input: ");
        inputString = keyboard.nextLine();

        do {
            if (inputString.equalsIgnoreCase("human")) {
                player.playerAtt += 1;
                player.playerDef += 1;
                player.playerRace = "human";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("wood elf")) {
                player.playerRanged += 1;
                player.playerAtt += 1;
                player.playerRace = "wood elf";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("high elf")) {
                player.playerMagic += 2;
                player.playerRace = "high elf";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("orc")) {
                player.playerStr += 2;
                player.playerRace = "orc";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("dwarf")) {
                player.playerStr += 1;
                player.playerAtt += 1;
                player.playerRace = "dwarf";
                invalid = false;
            }
            else {
                System.out.println("Please choose a valid race!");
                inputString = keyboard.nextLine();
            }
        } while(invalid);

        System.out.println("-------------------------------------------");

        //Choosing a class
        System.out.println("What class are you?");
        System.out.println("Fighter");
        System.out.println("Fighers arm themselves with a weapon and shield, sacrificing some offense for some defense.");
        System.out.println("Starting Equipment: Broadsword and a Wooden Shield");
        System.out.println();

        System.out.println("Warrior");
        System.out.println("Warriors choose to win fights by overpowering their enemy, often putting themselves at risk");
        System.out.println("Starting Equipment: Two-Handed Axe");
        System.out.println();

        System.out.println("Ranger");
        System.out.println("Rangers choose to pick out their enemies from afar with bows and arrows");
        System.out.println("Starting Equipment: Shortbow and 15 arrows.");
        System.out.println();

        System.out.println("Wizard");
        System.out.println("Wizards like to use fancy spells to smite their foes.");
        System.out.println("Starting Equipment: Firebolt and 2 mana potions");

        System.out.println("What class do you choose?");
        System.out.print(("Input: "));
        invalid = false;
        inputString = keyboard.nextLine();
        do {
            if (inputString.equalsIgnoreCase("fighter")) {
                player.inventory[0] = Loot.getBronzeSword();
                player.armor[5] = Loot.getWoodenShield();
                player.playerClass = "fighter";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("warrior")) {
                player.inventory[0] = Loot.getTwoHandedAxe();
                player.playerClass = "warrior";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("ranger")) {
                player.inventory[0] = Loot.getShortBow();
                player.backpack[0] = new Ammo("Arrow", 2, 1, 15);
                player.playerClass = "ranger";
                invalid = false;
            }
            else if(inputString.equalsIgnoreCase("wizard")) {
                player.spells[0] = SpellList.firebolt;
                player.backpack[0] = Loot.getManaPotion();
                player.backpack[1] = Loot.getManaPotion();
                player.playerClass = "wizard";
                invalid = false;
            }
            else {
                System.out.println("Please choose a valid class!");
                inputString = keyboard.nextLine();
            }
        } while(invalid);
        
        System.out.println("-------------------------------------------");

        //Finishing creation
        System.out.println("What would you like your name to be?");
        System.out.print("Input: ");
        player.playerName = keyboard.nextLine();
        
        player.setLevel(1);
        player.setLevelUpXP(level * 10);
        player.setPlayerMaxHealth(10);
        player.setPlayerCurHealth(playerMaxHealth);
        player.setMaxMana((level + 1) *  2);
        player.setCurMana(maxMana);
        player.setPlayerGold(10);
        player.setBackpackType("Bag");
        player.setLocation("Redeemer's Woods");
        player.setPlayerx(3);
        player.setPlayery(3);
        //player.setSubLocation("Bearpoint");
        
        return player;
    }

    //level up
    public void levelUp(Scanner keyboard, Player player) {
        String inputString;

        if(player.xp >= player.levelUpXP) {
            int oldHealth = playerMaxHealth;
            int oldMana = maxMana;

            player.setLevel(player.level += 1);
            player.setXp(xp -= levelUpXP); //if doesn't work correctly, reset to 0
            player.setLevelUpXP(level * 10);
            player.setPlayerMaxHealth(10 + (1 * level));
            player.setPlayerCurHealth(playerMaxHealth);
            player.setMaxMana((level + 1) *  2);
            player.setCurMana(maxMana);

            System.out.println("You've leveled up!" +
                "\nYou are now level " + player.getLevel() + "!" +
                "\nYou've gained " + (player.getPlayerMaxHealth() - oldHealth) + " HP!" + 
                "\nYou've gained " + (player.getMaxMana() - oldMana) + " mana!" +
                "\nSelect a skill to level up");
            System.out.println("Attack" +
                "\nStrength" + 
                "\nRanged" +
                "\nMagic");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Attack")) {
                player.setPlayerAtt(player.playerAtt += 1);
                System.out.println("Your Attack is now level " + player.getPlayerAtt() + "!");
            }
            if(inputString.equalsIgnoreCase("Strength")) {
                player.setPlayerStr(player.playerStr += 1);
                System.out.println("Your Strength is now level " + player.getPlayerStr() + "!");
            }
            if(inputString.equalsIgnoreCase("Ranged")) {
                player.setPlayerRanged(player.playerRanged += 1);
                System.out.println("Your Ranged is now level " + player.getPlayerRanged() + "!");
            }
            if(inputString.equalsIgnoreCase("Magic")) {
                player.setPlayerMagic(player.playerMagic += 1);
                System.out.println("Your Magic is now level " + player.getPlayerMagic() + "!");
            }

        }
        
        System.out.println("-------------------------------------------");

    }

    //character view
    public void printPlayerMenu(Scanner keyboard, Player player) throws Exception {
        String inputString;

        System.out.println("--Menu--");
        do {
        System.out.println("What you like you like to do?");
        System.out.println("Stats, spellbook, inventory, backpack, armor, quests, map, save, load, return");
        System.out.print("Input: ");
        inputString = keyboard.nextLine();

        if(inputString.equalsIgnoreCase("stats"))
            player.printStats(player);
        else if(inputString.equalsIgnoreCase("spellbook"))
            player.printSpellsMenu(keyboard, player);
        else if(inputString.equalsIgnoreCase("inventory"))
            player.printInventoryMenu(keyboard, player);
        else if(inputString.equalsIgnoreCase("backpack"))
            player.printBackpackMenu(keyboard, player);
        else if(inputString.equalsIgnoreCase("armor"))
            player.printArmor(keyboard, player);
        else if(inputString.equalsIgnoreCase("quests"))
            player.printQuestMenu(keyboard, player);
        else if(inputString.equalsIgnoreCase("map"))
            player.showMap(player);
        else if(inputString.equalsIgnoreCase("save"))
            method.saveGame(keyboard, player);
        else if(inputString.equals("load"))
            method.loadGame(keyboard);
        } while(!inputString.equalsIgnoreCase("return"));
        System.out.println("---");
    }

    //print stats
    public void printStats(Player player) {
        System.out.println("--Stats--");
        System.out.println(player.getPlayerName());
        System.out.println("Level " + player.getLevel() + " " + player.getPlayerRace() + " " + player.getPlayerClass());
        System.out.println("XP: " + player.getXp() + " / " + player.getLevelUpXP());
        System.out.println("Health: " + player.getPlayerCurHealth() + " / " + player.getPlayerMaxHealth());
        System.out.println("Attack: " + player.getPlayerAtt());
        System.out.println("Strength: " + player.getPlayerStr());
        System.out.println("Magic: " + player.getPlayerMagic());
        System.out.println("Defense: " + player.getPlayerDef());
        //System.out.println("Magic Defense: " + fields.getPlayerMagicDef());
        System.out.println("---");

    }

    //print inventory
    public void printInventory(Player player) {
        for(int i = 0; i < player.inventory.length; i ++) {
            if (player.inventory[i] == null) {
                System.out.println("Empty");
            }
            else {
                System.out.println((i + 1) + ". " + player.inventory[i].getName());
            }
        }
    }

    //print inventory menu
    public void printInventoryMenu(Scanner keyboard, Player player) {
        String inputString;

        System.out.println("--Inventory--");
        printInventory(player);

        do {
            System.out.println("What would you like to do - return or examine an item?");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Examine")) {
                System.out.println("Which item?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for (int i = 0; i < player.inventory.length; i++) {
                    if(player.inventory[i] != null && (inputString.equalsIgnoreCase(player.inventory[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))) {
                        System.out.println(player.inventory[i].toString());
                    }
                }
            }

        } while (!inputString.equalsIgnoreCase("return"));
        System.out.println("---");

    }
    
    //print backpack
    public void printBackpack(Player player) {
        for(int i = 0; i < player.backpack.length; i ++) {
            if (player.backpack[i] == null)
                System.out.println((i + 1) + ". Empty");
            else {
                if (player.backpack[i].getType().equals("Ammo")) {
                    System.out.print((i + 1) + ". " + player.backpack[i].getName());
                    System.out.println("(" + ((Ammo)player.backpack[i]).getCount() + ")");
                }
                else {
                    System.out.println((i + 1) + ". " + player.backpack[i].getName());
                }
            }
        }
    }

    //print backpack menu
    public void printBackpackMenu(Scanner keyboard, Player player) {
        String inputString;

        System.out.println("--Backpack--");
        printBackpack(player);

        do {
            System.out.println("What would you like to do - return, examine, use, or equip an item?");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Examine")) {
                System.out.println("Which item?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for (int i = 0; i < player.backpack.length; i++) {
                    if(player.backpack[i] != null && (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))) {
                        System.out.println(player.backpack[i].toString());
                    }
                }
            }
            else if(inputString.equalsIgnoreCase("use")) {
                System.out.println("Which item?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for(int i = 0; i < player.backpack.length; i++) {
                    if(Integer.parseInt(inputString) == (i + 1)) {
                        if(player.backpack[i].getType().equals("Tome")) {
                            System.out.println("Where would you like to add your spell?");
                            player.printSpells(player);
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();

                            for(int x = 0; x < player.spellbookSize; x++) {
                                if(Integer.parseInt(inputString) == (x + 1)
                                    && (player.spells[x] != ((SpellTome)player.backpack[i]).getSpellLearned())) {
                                    System.out.println("You learned " +((SpellTome)player.backpack[i]).getSpellLearned().getSpellName() + "!");
                                    player.spells[x] = ((SpellTome)player.backpack[i]).getSpellLearned();
                                    player.backpack[i] = null;
                                }
                            }
                        }
                        else {
                            System.out.println("You can't use that item!");
                        }
                    }
                }
            }
            else if(inputString.equalsIgnoreCase("equip")) {
                System.out.println("Which item?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for(int i = 0; i < player.backpack.length; i ++) {
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Helmet")) {
                        if(player.armor[0] == null) {
                            player.armor[0] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[0].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[0].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[0];
                                player.armor[0] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[0].getName() + "!");
                            }
                        }
                    }
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Chestplate")) {
                        if(player.armor[1] == null) {
                            player.armor[1] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[1].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[1].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[1];
                                player.armor[1] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[1].getName() + "!");
                            }
                        }
                    }
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Leggings")) {
                        if(player.armor[2] == null) {
                            player.armor[2] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[0].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[0].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[2];
                                player.armor[2] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[2].getName() + "!");
                            }
                        }
                    }
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Boots")) {
                        if(player.armor[3] == null) {
                            player.armor[3] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[3].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[3].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[3];
                                player.armor[3] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[3].getName() + "!");
                            }
                        }
                    }
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Gloves")) {
                        if(player.armor[4] == null) {
                            player.armor[4] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[4].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[4].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[4];
                                player.armor[4] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[4].getName() + "!");
                            }
                        }
                    }
                    if(player.backpack[i] != null && 
                    (inputString.equalsIgnoreCase(player.backpack[i].getName())
                    || Integer.parseInt(inputString) == (i + 1))
                    && player.backpack[i].getType().equals("Shield")) {
                        if(player.armor[5] == null) {
                            player.armor[5] = (Armor)player.backpack[i];
                            player.backpack[i] = null;
                            System.out.println("You've equpped " + player.armor[5].getName() + "!");
                        }
                        else {
                            System.out.println("You already have " + player.armor[5].getName() + " equipped!" + 
                                                "\nWould you like to replace it? (Y/N)");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();
                            if(inputString.equalsIgnoreCase("Y")) {
                                Item item = player.armor[5];
                                player.armor[5] = (Armor)player.backpack[i];
                                player.backpack[i] = item;
                                System.out.println("You've equpped " + player.armor[5].getName() + "!");
                            }
                        }
                    }
                }
            }

        } while (!inputString.equalsIgnoreCase("return"));
        System.out.println("---");

    }

    //print spellbook
    public void printSpells(Player player) {
        for(int i = 0; i < player.spells.length; i ++) {
            if (player.spells[i] == null)
                System.out.println("Empty");
            else {
                System.out.println((i + 1) + ". " + player.spells[i].getSpellName());
            }
        }
    }

    //print spellbook menu
    public void printSpellsMenu(Scanner keyboard, Player player) {
        String inputString = "";
        
        System.out.println("--Spellbook--");
        printSpells(player);

        do {
            System.out.println("What would you like to do - return or examine a spell?");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Examine")) {
                System.out.println("Which spell?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for (Spell spell : player.spells) {
                    if (spell != null && inputString.equalsIgnoreCase(spell.getSpellName())) {
                        System.out.println(spell.toString());
                    }
                }
            }

        } while (!inputString.equalsIgnoreCase("return"));
        System.out.println("---");
    }

    //print armor
    public void printArmor(Scanner keyboard, Player player) {
        String inputString;

        System.out.println("--Armor--");

        if(player.armor[0] != null) 
            System.out.println("Helmet: " + player.armor[0].getName());
        else
            System.out.println("Helmet: Empty");

        if(player.armor[1] != null) 
            System.out.println("Chestplate: " + player.armor[1].getName());
        else
            System.out.println("Chestplate: Empty");

        if(player.armor[2] != null)
            System.out.println("Leggings: " + player.armor[3].getName());
        else
            System.out.println("Leggings: Empty");
        
        if(player.armor[3] != null)
            System.out.println("Boots: " + player.armor[3].getName());
        else    
            System.out.println("Boot: Empty");
        
        if(player.armor[4] != null)
            System.out.println("Gloves: " + player.armor[4].getName());
        else    
            System.out.println("Gloves: Empty");

        if(player.armor[5] != null)
            System.out.println("Shield: " + player.armor[5].getName());
        else
            System.out.println("Shield: Empty");
        
        if(player.armor[6] != null)
            System.out.println("Neck: " + player.armor[6].getName());
        else
            System.out.println("Neck: Empty");

        if(player.armor[7] != null)
            System.out.println("Ring: " + player.armor[7].getName());
        else
            System.out.println("Ring: Empty");
            
        do {
            System.out.println("What would you like to do - return, examine or remove a piece of armor?");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Examine")) {
                System.out.println("Which piece of armor?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for (int i = 0; i < player.armor.length; i++) {
                    if(player.armor[i] != null && inputString.equalsIgnoreCase(player.armor[i].getName()))
                        System.out.println(player.armor[i].toString());
                }
            }
            else if (inputString.equalsIgnoreCase("Remove")) {
                System.out.println("Which piece of armor would you like to remove? (Input type)");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                
                Armor item = new Armor("Placeholder", "Ring", 0, 0);

                for(int i = 0; i < player.armor.length; i++) {
                    System.out.println("In for loop!");
                    if(player.armor[i] != null && inputString.equalsIgnoreCase(player.armor[i].getArmorType())) {
                        System.out.println("At " + player.armor[i].getArmorType());
                        item = player.armor[i];
                        player.armor[i] = null;
                    }
                }

                System.out.println("Where in your backpack would you like to add it?");
                printBackpack(player);
                System.out.print("Input: ");
                inputString = keyboard.nextLine();

                for (int i = 0; i < player.backpack.length; i++) {
                    if(Integer.parseInt(inputString) == (i + 1)) {
                        player.backpack[i] = item;
                    }
                }

                System.out.println(item.getName() + " added to backpack!");

            }
            else {
                System.out.println("That's not a valid input!");
            }
        } while (!inputString.equalsIgnoreCase("return"));
    }

    //printing quest log
    public void printQuestLog (Player player) {
            for (int i = 0; i < player.questLog.length; i++) {
                if(player.questLog[i] == null) {
                    System.out.println((i + 1) + ". Empty");
                }

                else if(player.questLog[i].isQuestDone()) {
                    System.out.print((i + 1) + ". " + player.questLog[i].getName());
                    System.out.println(" - COMPLETE");
                }

                else {
                    System.out.println((i + 1) + ". " + player.questLog[i].getName());
                }
            }
        }

    //printing quest menu
    public void printQuestMenu (Scanner keyboard, Player player) {
        String inputString = "";
        System.out.println("--Quests--");
        player.printQuestLog(player);

        do {
            System.out.println("What would you like to do - return or examine a quest?");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("Examine")) {
                System.out.println("Which quest?");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
                for (int i = 0; i < player.questLog.length; i++) {
                    if(player.questLog[i] != null && (inputString.equalsIgnoreCase(player.questLog[i].getName()) ||
                    Integer.parseInt(inputString) == (i + 1)))
                        System.out.println(player.questLog[i].questInfo(player.questLog[i]));
                }
            }

        } while (!inputString.equalsIgnoreCase("return"));
    }

    //move
    public void playerMove(Scanner keyboard, Player player) {
        System.out.println("Where would you like to move? North, South, East, or West?");

        String inputString = "";
        boolean validInput = false;

        do {
            System.out.print("Input: ");
            inputString = keyboard.nextLine();
            
            if(inputString.equalsIgnoreCase("north")) {
                System.out.println("You move north.");
                player.setPlayerx(playerx -= 1);
                validInput = true;
            }

            else if(inputString.equalsIgnoreCase("south")) {
                System.out.println("You move south.");
                player.setPlayerx(playerx += 1);
                validInput = true;
            }

            else if(inputString.equalsIgnoreCase("east")) {
                System.out.println("You move east.");
                player.setPlayery(playery += 1);
                validInput = true;
            }

            else if(inputString.equalsIgnoreCase("west")) {
                System.out.println("You move west.");
                player.setPlayery(playery -= 1);
                validInput = true;
            }

            else
                System.out.println("Not a valid input!");

        } while (!validInput);
    }

    public void showMap(Player player) {
        //Redeemer's Woods
        if(player.getLocation().equals("RedeemersWoods")) {
            for(int x = 0; x < RedeemersWood.RedeemersWoods.squares.length; x++) {
                System.out.println();
                for (int y = 0; y < RedeemersWood.RedeemersWoods.squares[0].length; y++) {
                    // System.out.println("Square " + x + "," + y + " " + Maps.RedeemersWoods.squares[x][y].isFound());
                    if (player.getPlayerx() == x && player.getPlayery() == y)
                        System.out.print("V  ");
                    else if (!RedeemersWood.RedeemersWoods.squares[x][y].isFound())
                        System.out.print("x  ");
                    else if(RedeemersWood.RedeemersWoods.squares[x][y].isTown())
                        System.out.print("T  ");
                    else
                        System.out.print(RedeemersWood.RedeemersWoods.squares[x][y].getAbb());
                }
            }
        }
        System.out.println();
        System.out.println();    
    }

    public void lootItem (Player player, Scanner keyboard, Item[] table, int roll) {
        String inputString = "";
        boolean valid = false;
        
        System.out.println("You find a " + table[roll].getName() + "!");
        System.out.println("Would you like to take it? (Y/N)");
        System.out.print("Input: ");
        
        do {
            inputString = keyboard.nextLine();
            if(inputString.equalsIgnoreCase("N")) {
                return;
            }
            else if(inputString.equalsIgnoreCase("Y")) {
                if(table[roll].getType().equals("Weapon")) {
                    System.out.println("Where in your inventory would you like to add it?");
                    player.printInventory(player);
                    System.out.print("Input: ");

                    inputString = keyboard.nextLine();
                    int slot = (Integer.parseInt(inputString));

                    player.inventory[(slot - 1)] = table[roll];
                    System.out.println(table[roll].getName() + " added to inventory!");
                    valid = true;
                }

                else { 
                    System.out.println("Where in your backpack would you like to add it? (Enter slot number)");
                    player.printBackpack(player);
                    System.out.print("Input: ");

                    inputString = keyboard.nextLine();
                    int slot = Integer.parseInt(inputString);

                    if(player.backpack[slot].getType().equals("Ammo")) {
                        if(player.backpack[slot].getType().equals(table[roll].getType())) {
                            ((Ammo)player.backpack[slot]).setCount(((Ammo)player.backpack[slot]).getCount() - 1);
                        }
                    }

                    player.backpack[(slot - 1)] = table[roll];    
                    System.out.println(table[roll].getName() + " added to backpack!");
                    valid = true;
                }
            }
            else
                System.out.println("Not a valid input!");
        } while (!valid);
    }

    //getters and setters
    public String getSaveFile() {
        return saveFile;
    }
    public void setSaveFile(String saveFile) {
        this.saveFile = saveFile;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getPlayerRace() {
        return playerRace;
    }
    public void setPlayerRace(String playerRace) {
        this.playerRace = playerRace;
    }
    public String getPlayerClass() {
        return playerClass;
    }
    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getLevelUpXP() {
        return levelUpXP;
    }
    public void setLevelUpXP(int levelUpXP) {
        this.levelUpXP = levelUpXP;
    }
    public int getPlayerAtt() {
        return playerAtt;
    }
    public void setPlayerAtt(int playerAtt) {
        this.playerAtt = playerAtt;
    }
    public int getPlayerStr() {
        return playerStr;
    }
    public void setPlayerStr(int playerStr) {
        this.playerStr = playerStr;
    }
    public int getPlayerMagic() {
        return playerMagic;
    }
    public void setPlayerMagic(int playerMagic) {
        this.playerMagic = playerMagic;
    }
    public int getPlayerRanged() {
        return playerRanged;
    }
    public void setPlayerRanged(int playerRanged) {
        this.playerRanged = playerRanged;
    }
    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }
    public void setPlayerMaxHealth(int playerHealth) {
        this.playerMaxHealth = playerHealth;
    }
    public int getPlayerDef() {
        return playerDef;
    }
    public void setPlayerDef(int playerDef) {
        this.playerDef = playerDef;
    }
    public int getPlayerMagicDef() {
        return playerMagicDef;
    }
    public void setPlayerMagicDef(int playerMagicDef) {
        this.playerMagicDef = playerMagicDef;
    }
    public int getBackpackSize() {
        return backpackSize;
    }
    public void setBackpackSize(int backpackSize) {
        this.backpackSize = backpackSize;
    }
    public int getSpellbookSize() {
        return spellbookSize;
    }
    public void setSpellbookSize(int spellbookSize) {
        this.spellbookSize = spellbookSize;
    }
    public Item[] getInventory() {
        return inventory;
    }
    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }
    public Item[] getBackpack() {
        return backpack;
    }
    public void setBackpack(Item[] backpack) {
        this.backpack = backpack;
    }
    public Spell[] getSpells() {
        return spells;
    }
    public void setSpells(Spell[] spells) {
        this.spells = spells;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public int getPlayerCurHealth() {
        return playerCurHealth;
    }
    public void setPlayerCurHealth(int playerCurHealth) {
        this.playerCurHealth = playerCurHealth;
    }

    public int getPlayerGold() {
        return playerGold;
    }

    public void setPlayerGold(int playerGold) {
        this.playerGold = playerGold;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getCurMana() {
        return curMana;
    }

    public void setCurMana(int curMana) {
        this.curMana = curMana;
    }

    public Armor[] getArmor() {
        return armor;
    }

    public void setArmor(Armor[] armor) {
        this.armor = armor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBackpackType() {
        return backpackType;
    }

    public void setBackpackType(String backpackType) {
        this.backpackType = backpackType;
    }

    // public Methods getMethod() {
    //     return method;
    // }

    // public void setMethod(Methods method) {
    //     this.method = method;
    // }

    // public Loot getLoot() {
    //     return loot;
    // }

    // public void setLoot(Loot loot) {
    //     this.loot = loot;
    // }

    // public SpellList getSpellList() {
    //     return spellList;
    // }

    // public void setSpellList(SpellList spellList) {
    //     this.spellList = spellList;
    // }

    // public String getSubLocation() {
    //     return subLocation;
    // }

    // public void setSubLocation(String subLocation) {
    //     this.subLocation = subLocation;
    // }

    public Quest[] getQuestLog() {
        return questLog;
    }

    public void setQuestLog(Quest[] questLog) {
        this.questLog = questLog;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    // public Loot getLoot() {
    //     return loot;
    // }

    // public void setLoot(Loot loot) {
    //     this.loot = loot;
    // }

    // public SpellList getSpellList() {
    //     return spellList;
    // }

    // public void setSpellList(SpellList spellList) {
    //     this.spellList = spellList;
    // }

    public int getPlayerx() {
        return playerx;
    }

    public void setPlayerx(int playerx) {
        this.playerx = playerx;
    }

    public int getPlayery() {
        return playery;
    }

    public void setPlayery(int playery) {
        this.playery = playery;
    }
    
    
    
    
}

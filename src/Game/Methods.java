package Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


public class Methods implements Serializable {
    //initalizers
    Random r = new Random();
    //Loot loot = new Loot();

    //methods
    //saving
    public void saveGame(Scanner keyboard, Player player) throws Exception {
        System.out.println("What would you like to call your save?");
        System.out.print("Input: ");
        player.saveFile = keyboard.nextLine();
        
        FileOutputStream fileOut = new FileOutputStream(player.saveFile + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(player);
        out.writeObject(RedeemersWood.RedeemersWoods);
        out.close();
        fileOut.close();

        System.out.println("Game saved!" + 
            "\nFile saved as " + player.saveFile + ".ser!");
        System.out.println("---");
    }

    //loading
    public Player loadGame(Scanner keyboard) throws Exception {
        String saveFile;

        System.out.println("Which save would you like to load?");
        System.out.print("Input: ");
        saveFile = keyboard.nextLine();
      
        FileInputStream fileIn = new FileInputStream(saveFile + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Player player = (Player)in.readObject();
        RedeemersWood.RedeemersWoods = (Location)in.readObject();
        in.close();
        fileIn.close();

        System.out.println("Load complete!");
        System.out.println("---");
        return player;
    }

    //combat
    //UNDER CONSTRUCTION!!!!
    //PLEASE READ!!!!!
    public void combat(Scanner keyboard, Player player, Enemy enemy) throws Exception {
        System.out.println("You are attacked by level " + enemy.getLevel() + " " + enemy.getName() + "!");
        System.out.println("--------------------------------------------");

        //basics
        String inputString;

        int round = 1;
        boolean playerTurn = true;
        boolean enemyTurn = false;
        int swing;
        int damage;
        int poisonWeapon = -1;
        boolean playerPoisoned = false;
        boolean enemyPoisioned = false;

        //buffs
        boolean buff = false;
        boolean attBuff = false;
        int attInc = 0;
        boolean strBuff = false;
        int strInc = 0;
        boolean defBuff = false;
        int defInc = 0;
        boolean rangeBuff = false;
        int rangeInc = 0;
        boolean magicBuff = false;
        int magicInc = 0;

        //other
        enemy.curHealth = enemy.maxHealth;
        
        do {
            System.out.println("--Round " + round + "--");
            do {
                if(playerPoisoned)
                    player.playerCurHealth -= enemy.getPoisonDamage();

                if (player.playerCurHealth <= 0)
                    player.setAlive(false);

                else {
                    int weapon = -1;

                    System.out.println("What would you like to do?");
                    System.out.println("Attack, cast spell, view backpack, view health, or flee?");
                    System.out.print("Input: ");
                    inputString = keyboard.nextLine();

                    //melee attack
                    if(inputString.equalsIgnoreCase("attack")) {
                        System.out.println("Which weapon would you like to attack with? (Type \"Back\" to go back)");

                        player.printInventory(player);
                        System.out.println((player.inventory.length + 1) + ". Back");
                        System.out.print("Input: ");
                        inputString = keyboard.nextLine();

                        if(inputString.equalsIgnoreCase("back"))
                            continue;

                        for(int i = 0; i < player.inventory.length; i ++) {
                            if(player.inventory[i] != null && (inputString.equalsIgnoreCase(player.inventory[i].getName())
                            || Integer.parseInt(inputString) == (i + 1))){
                                weapon = i;
                            }
                            
                        }
                        if(weapon == -1) {
                            System.out.println("That's not one of your weapons!");
                            continue;
                        }

                        //changing to int inputs -- BREAKS IF YOU TYPE A STRING
                        //try and catch InputMismatchException
                        // if(inputString.equalsIgnoreCase("attack")) {
                        //     System.out.println("Which weapon would you like to attack with?");
    
                        //     player.printInventory(player);
                        //     System.out.println((player.inventory.length + 1) + ". Back");
                        //     System.out.print("Input: ");
                        //     inputInt = keyboard.nextInt();
    
                        //     if(inputInt == (player.inventory.length + 1))
                        //         continue;
    
                        //     for(int i = 0; i < player.inventory.length; i ++) {
                        //         if(player.inventory[i] != null && (inputInt == (i + 1))){
                        //             weapon = i;
                        //         }
                                
                        // }
                        damage = ((Weapon)player.inventory[weapon]).rollDamage() + player.playerStr;
                        swing = ((Weapon)player.inventory[weapon]).rollAttack() + player.playerAtt;
                        if(((Weapon)player.inventory[weapon]).getWeaponType().equals("Ranged")){
                            if(player.inventory[weapon].getType().equals("Ammo")) {
                                damage = ((Weapon)player.inventory[weapon]).rollDamage() + player.playerRanged;
                                int ammoCount = ((Ammo)player.inventory[weapon]).getCount();
                                if(ammoCount <= 0) {//WILL STILL TAKE DAMAGE -- FIX THIS
                                    System.out.println("You have no ammo!");
                                    continue;
                                }
                                else
                                    ((Ammo)player.inventory[weapon]).setCount(ammoCount - 1);
                            }
                        }
                        if(swing > enemy.defence) {
                            if(((Weapon)player.inventory[weapon]).getWeaponType().equals(enemy.getResistance())) {
                                damage /= 2;
                                System.out.println(enemy.getName() + " seemed resistant to your attack!");
                            }
                            if(((Weapon)player.inventory[weapon]).getWeaponType().equals(enemy.getImmunity())) {
                                damage = 0;
                                System.out.println(enemy.getName() + " was immune to your attack!");
                            }
                            if(((Weapon)player.inventory[weapon]).getWeaponType().equals(enemy.getVulnerable())) {
                                damage *= 2;
                                System.out.println(enemy.getName() + " seemed vulnerable to your attack!");
                            }
                            if(((Weapon)player.inventory[weapon]).isPoisonous()) {
                                enemyPoisioned = true;
                                poisonWeapon = weapon;
                                System.out.println("You poisoned " + enemy.getName() + "!");
                            }
                            System.out.println("You hit " + enemy.getName() + " for " + damage 
                                + " " + ((Weapon)player.inventory[weapon]).getWeaponType() + " damage!");
                            enemy.curHealth -= damage;
                            playerTurn = false;
                            enemyTurn = true;
                        }
                        else {
                            System.out.println("You missed!");
                            playerTurn = false;
                            enemyTurn = true;
                        }
                
                    }

                    //spell casting
                    else if(inputString.equalsIgnoreCase("cast spell")
                        ||inputString.equalsIgnoreCase("spell")
                        ||inputString.equalsIgnoreCase("cast")) {
                        player.printSpells(player);

                        System.out.println("Which spell would you like to cast? (Type \"Back\" to go back.)");
                        System.out.print("Input: ");
                        inputString = keyboard.nextLine();

                        if(inputString.equalsIgnoreCase("back"))
                            continue;

                        for (int i = 0; i < player.spells.length; i++) {
                            if(player.spells[i] != null && (inputString.equalsIgnoreCase(player.spells[i].getSpellName())
                            || Integer.parseInt(inputString) == (i + 1))) {
                                weapon = i;
                            }
                        }

                        if(weapon == -1) {
                            System.out.println("That's not one of your spells!");
                            continue;
                        }

                        if(player.curMana <= 0)
                            System.out.println("You're out of mana!");
                        else {
                            player.curMana -= 1;
                            swing = player.spells[weapon].rollAttack() + player.playerAtt;
                            damage = player.spells[weapon].rollDamage() + player.playerMagic;
                            if(swing > enemy.defence) {
                                if(player.spells[weapon].getSpellType().equals(enemy.getResistance())) {
                                    damage /= 2;
                                    System.out.println(enemy.getName() + " seemed resistant to your spell!");
                                }
                                if(player.spells[weapon].getSpellType().equals(enemy.getImmunity())) {
                                    damage = 0;
                                    System.out.println(enemy.getName() + " was immune to your spell!");
                                }
                                System.out.println("You hit " + enemy.getName() + " for " + damage + " damage!");
                                enemy.curHealth -= damage;
                                playerTurn = false;
                                enemyTurn = true;
                            }
                            else {
                                System.out.println("You missed!");
                                playerTurn = false;
                                enemyTurn = true;
                            }
                        }
                    }
                      
                    //backpack
                    else if (inputString.equalsIgnoreCase("backpack")
                        || inputString.equalsIgnoreCase("view backpack")) {
                        boolean done = false;
                        System.out.println("--Backpack--");
                        player.printBackpack(player);

                        do {
                            System.out.println("What would you like to do - return, drink potion, or examine an item?");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();

                            if(inputString.equalsIgnoreCase("return")) {
                                System.out.println("--");
                                done = true;
                            }
                            else if(inputString.equalsIgnoreCase("Examine")) {
                                System.out.println("Which item?");
                                System.out.print("Input: ");
                                inputString = keyboard.nextLine();
                                for (int i = 0; i < player.backpack.length; i++) {
                                    if(player.backpack[i] != null && (inputString.equalsIgnoreCase(player.backpack[i].getName())
                                    || Integer.parseInt(inputString) == (i + 1)))
                                        System.out.println(player.backpack[i].toString());
                                }
                            }

                            else if(inputString.equalsIgnoreCase("Drink Potion")
                            ||inputString.equalsIgnoreCase("potion")
                            ||inputString.equalsIgnoreCase("drink")) {
                                System.out.println("Which potion?");
                                System.out.print("Input: ");
                                inputString = keyboard.nextLine();
                                for(int i = 0; i < player.backpack.length; i++) {
                                    if(player.backpack[i] != null && (inputString.equalsIgnoreCase(player.backpack[i].getName())
                                    || Integer.parseInt(inputString) == (i + 1))) {
                                        Potion potion = (Potion)player.backpack[i];
                                        if (potion.heal > 0) {
                                            player.setPlayerCurHealth(player.playerCurHealth += potion.getHeal());
                                            if (player.playerCurHealth > player.playerMaxHealth)
                                                player.playerCurHealth = player.playerMaxHealth;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if (potion.manaRestore > 0) {
                                            player.curMana += potion.getManaRestore();
                                            if (player.curMana > player.maxMana)
                                                player.curMana = player.maxMana;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if (potion.getAttIncrease() > 0 && buff != true) {
                                            attInc = potion.getAttIncrease();
                                            player.playerAtt += attInc;
                                            buff = true;
                                            attBuff = true;
                                            done = true;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if(potion.getStrIncrease() > 0 && buff != true) {
                                            strInc = potion.getStrIncrease();
                                            player.playerStr += strInc;
                                            buff = true;
                                            strBuff = true;
                                            done = true;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if(potion.getDefIncrease() > 0 && buff != true) {
                                            defInc = potion.getDefIncrease();
                                            player.playerDef += player.playerDef;
                                            buff = true;
                                            defBuff = true;
                                            done = true;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if(potion.getRangeIncrease() > 0 && buff != true) {
                                            rangeInc = potion.getRangeIncrease();
                                            player.playerRanged += rangeInc;
                                            buff = true;
                                            rangeBuff = true;
                                            done = true;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else if(potion.getMagicIncrease() > 0 && buff != true) {
                                            magicInc = potion.getMagicIncrease();
                                            player.playerMagic += magicInc;
                                            buff = true;
                                            magicBuff = true;
                                            done = true;
                                            System.out.println("You drank " + player.backpack[i].getName() + "!");
                                            player.backpack[i] = null;
                                            break;
                                        }
                                        else {
                                            System.out.println("You already drank a buff potion this combat!");
                                        }

                                    }

                                }
                                    
                            }
                        } while (!done);
                    }

                    //view health
                    else if (inputString.equalsIgnoreCase("health")
                    ||inputString.equalsIgnoreCase("view health")) {
                        System.out.println("Your health: " + player.getPlayerCurHealth() + " / " + player.getPlayerMaxHealth());
                        System.out.println("Your mana: " + player.getCurMana() + " / " + player.getMaxMana());
                        System.out.println("Enemy health: " + enemy.getCurHealth() + " / " + enemy.getMaxHealth());
                    }

                    //THIS SHOULD WORK FINE??!!!!!
                    //IF NOT, FINISH THIS!!!!!
                    else if (inputString.equalsIgnoreCase("flee")) {
                        // double playerLevel = player.getLevel();
                        // double npcLevel = npc.getNpcLevel();
                        // double fleeChance = ((player.getLevel() / enemy.getLevel()) / 100); //TEST FORMULA
                        // //double fleeChance = ((playerLevel / npcLevel) * 100); ACTUAL FORMULA
                        // if(player.getLevel() == enemy.getLevel())
                        //     fleeChance = 60.0;
                        double fleeChance = 50.0;
                        double fleeNum = r.nextDouble(0, 100);

                        if (fleeNum < fleeChance) {
                            System.out.println("You successfully run away!");
                            return;
                        }
                        else {
                            System.out.println("The enemy caught up to you!");
                            playerTurn = false;
                            enemyTurn = true;
                        }
                    }

                    else {
                        System.out.println("Please make a valid action!");
                    }
                }   

            } while (playerTurn && player.isAlive());

            do {
                if(enemyPoisioned)
                    enemy.curHealth -= ((Weapon)player.inventory[poisonWeapon]).getPoisonDamage();

                if (enemy.curHealth <= 0) {
                    enemy.setAlive(false);
                    System.out.println("You killed " + enemy.getName() + "!");
                    System.out.println("You are awarded " + enemy.getAwardXP() + " XP!");
                    player.setXp(player.xp + enemy.getAwardXP());
                }

                else {
                    if (enemy.npcRollAttack() > player.getPlayerDef()) {
                        int npcDamage = enemy.npcRollDamage();
                        System.out.println(enemy.getName() + " hits you for " + npcDamage + " damage!");
                        player.setPlayerCurHealth(player.getPlayerCurHealth() - npcDamage);
                        enemyTurn = false;
                        playerTurn = true;
                    }
                    else {
                        System.out.println(enemy.getName() + " misses you!");
                        enemyTurn = false;
                        playerTurn = true;
                    }

                }
                round++;
            } while (enemyTurn && enemy.isAlive());
        } while (player.isAlive() && enemy.isAlive());

        if (buff) {
            if (attBuff) {
                player.playerAtt -= attInc;
            }
            else if(strBuff) {
                player.playerStr -= strInc;
            }
            else if(defBuff) {
                player.playerDef -= defInc;
            }
            else if(rangeBuff) {
                player.playerRanged -= rangeInc;
            }
            else if(magicBuff) {
              player.playerMagic -= magicInc;
            }
        }

        if (!player.isAlive()) {
            System.out.println("--------------------------------------------");
            System.out.println("You were killed by " + enemy.getName() + "!" 
            + "\nWould you like to load a game? (Y/N)");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();
            if (inputString.equalsIgnoreCase("y")) {
                player = loadGame(keyboard);
            }
            if (inputString.equalsIgnoreCase("n")) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
        else {
            System.out.println("--------------------------------------------");
            questProgress(player, enemy);
            rollLootTable(keyboard, player, enemy.getLoot());
            player.levelUp(keyboard, player);
        }

    }
    
    //generating combat encounters
    public void combatGenerator(Scanner keyboard, Player player, Enemy[] enemies) throws Exception {
        Random r = new Random();
        int roll = r.nextInt(0, enemies.length);
        Enemy enemy = enemies[roll];
        combat(keyboard, player, enemy);
    }

    //rolling loot tables
    public void rollLootTable(Scanner keyboard, Player player, Item[] table) {
        int roll;
        roll = r.nextInt(0, (table.length));
        
        if (table[roll] == null)
            System.out.println("You find nothing worth looting on the corpse.");
        else {
            player.lootItem(player, keyboard, table, roll);
        }
        System.out.println("--------------------------------------------");
    }

    //market menu
    public void enterMarket(Scanner keyboard, Player player, Store[] market) {
        Random r = new Random();
        int roll = r.nextInt(1, 4);
        String inputString = "0";

        System.out.println("You head to the town market.");

        switch(roll) {
            case 1:
                System.out.println("The streets are mostly empty as everybody is out at work.");
                break;
            case 2:
                System.out.println("Crickets chirp throughout the night as you walk the empty street.");
                break;
            case 3:
                System.out.println("The street is bustling with people as the midday sun shines overhead.");
                break;
            case 4:
                System.out.println("The street begins to fill as everyone begins to come home from a hard day's work.");
                break;
        }

        System.out.println("You take note of the signs around you. Where would you like to go?");
        do {
            for (int i = 0; i < market.length; i++) {
                System.out.println((i + 1) + ". " + market[i].getName());
            }
            System.out.println((market.length + 1) + ". Leave");
            System.out.print("Input: ");
                inputString = keyboard.nextLine();

            for (int i = 0; i < market.length; i++) {
                if(inputString.equalsIgnoreCase(market[i].getName())
                || Integer.parseInt(inputString) == (i + 1)) {
                    enterShop(keyboard, player, market[i]);
                    System.out.println("You walk back out into the market.");
                    System.out.println("Where would you like to go?");
                }
            }

        } while (Integer.parseInt(inputString) != (market.length + 1));
    }

    //shop menu
    public void enterShop(Scanner keyboard, Player player, Store store) {
        Random r = new Random();
        String inputString = "0";
        int roll = r. nextInt(1, 5);

        System.out.println("--------------------------------------------");
        System.out.println("You see a sign that says \"" + store.getName() + "\", and enter the store.");
        System.out.println("As you walk in, the shopkeeper smiles at you and says,");

        if (roll == 1)
            System.out.println("\"Welcome to my shop, adventurer!\"");
        else if(roll == 2)
            System.out.println("\"Looking to protect yourself, or deal some damage?\"");
        else if(roll == 3)
            System.out.println("\"I've got the wares if you've got the coin.\"");
        else if (roll == 4)
            System.out.println("\"Hello adventurer! My name is " + store.getClerk() + " if you need anything!\"");
        else if (roll == 5) {
            System.out.println("Let me know if anything catches your eye, " + player.getPlayerRace() + ".");
        }

        System.out.println("You have " + player.getPlayerGold() + " gold.");

        do {
            System.out.println("Are you buying or selling?");
            System.out.println("1. Buying");
            System.out.println("2. Selling");
            System.out.println("3. Exit");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            //buying
            if(inputString.equalsIgnoreCase(""))
                continue;

            else if(Integer.parseInt(inputString) == 1) {
                System.out.println("What would you like to buy?");
                store.printShop(store);

                if(store.getType().equals("Leatherworker")) {
                    System.out.println((store.items.length + 1) + ". Bag Upgrade");
                    System.out.println((store.items.length + 2) + ". Back");
                }
                else {
                    System.out.println((store.items.length + 1) + ". Back");
                }

                System.out.print("Input: ");
                inputString = keyboard.nextLine();

                boolean itemFound = false;

                //Upgrading bag if leatherworker
                if(store.type.equals("Leatherworker")) {
                    if(Integer.parseInt(inputString) == (store.items.length + 1)) {
                        itemFound = true;
                        System.out.println("\"I can upgrade your bag to a " + store.getUpgradeName() +
                            ", which will give you " + store.getUpgradeSize() + 
                            " extra slots in your backpack for " + store.getUpgradePrice() + " Gold\"");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        System.out.print("Input: ");
                        inputString = keyboard.nextLine();

                        if(Integer.parseInt(inputString) == 1) {
                            if(!player.getBackpackType().equals(store.getUpgradeFrom())) {
                                System.out.println("I'm afraid I only upgrade " + store.getUpgradeFrom() + ", traveler");
                            }
                            
                            else if (player.getPlayerGold() >= store.getUpgradePrice()) {
                                Item[] tempBack = new Item[player.getBackpackSize()];

                                for (int i = 0; i < tempBack.length; i++) {
                                    tempBack[i] = player.backpack[i];
                                }

                                player.setBackpackSize(player.backpackSize += store.getUpgradeSize());
                                player.setPlayerGold(player.playerGold -= 50);

                                player.backpack = new Item[player.getBackpackSize()];

                                for (int i = 0; i < tempBack.length; i++) {
                                    player.backpack[i] = tempBack[i];
                                }

                                System.out.println("\"Your bag is now upgraded adventurer! Anything else?\"");
                                continue;
                            }
                           
                            else {
                                System.out.println("You don't have enough gold!");
                            }
                        }
                        else if(Integer.parseInt(inputString) == 2) {
                            System.out.println("Anything else then, adventurer?");
                        }
                        else {

                        }
                    }
                    else if(inputString.equalsIgnoreCase("back") 
                    || Integer.parseInt(inputString) == (store.items.length + 2))
                    continue;
                }
                else if(inputString.equalsIgnoreCase("back") 
                    || Integer.parseInt(inputString) == (store.items.length + 1))
                    continue;

                for (int i = 0; i < store.items.length; i++) {
                    if(inputString.equalsIgnoreCase(store.items[i].getName())
                    || Integer.parseInt(inputString) == (i + 1)) {
                        itemFound = true;
                        if(player.playerGold < store.items[i].getValue() * 2) {
                            System.out.println("You don't have enough gold!");
                        }
                        else {
                            if(store.items[i].getType().equals("Weapon")) {
                                System.out.println("Where in your inventory would you like to add it? (Enter slot number)");
                                player.printInventory(player);
                                inputString = keyboard.nextLine();
                                
                                int slot = (Integer.parseInt(inputString));

                                player.inventory[(slot - 1)] = store.items[i];
                                player.playerGold -= store.items[i].getValue() * 2;
                                System.out.println("You've bought " + store.items[i].getName() + "!");
                                System.out.println("You now have " + player.playerGold + " Gold!");
                                break;
                            }
                            else {
                                int count = 0;
                                if(store.items[i].getType().equals("Ammo")) {
                                    System.out.println("How many would you like to buy?");
                                    System.out.print("Input: ");
                                    inputString = keyboard.nextLine();

                                    if(inputString.equalsIgnoreCase("back"))
                                        continue;
                                    else {
                                        count = Integer.parseInt(inputString);
                                        ((Ammo)store.items[i]).setCount(count);
                                        ((Ammo)store.items[i]).setValue(count * store.items[i].getValue());
                                    }
                                }
                                System.out.println("Where in your backpack would you like to add it?");
                                player.printBackpack(player);

                                System.out.print("Input: ");
                                inputString = keyboard.nextLine();
                                int slot = (Integer.parseInt(inputString));
                                
                                if(player.backpack[slot - 1] != null && player.backpack[slot - 1].getType().equals("Ammo")) {
                                    ((Ammo)player.backpack[slot - 1]).setCount(((Ammo)player.backpack[slot - 1]).getCount() + count);
                                    System.out.println("You've bought " + count + " " + store.items[i].getName() + "s!");
                                }
                                else {
                                    player.backpack[(slot - 1)] = store.items[i];
                                    System.out.println("You've bought " + store.items[i].getName() + "!");
                                }

                                player.playerGold -= store.items[i].getValue();
                                System.out.println("You now have " + player.playerGold + " Gold!");
                                break;
                            }
                        }
                    }
                }
                if(!itemFound)
                    System.out.println("The shop doesn't sell that!");
            }

            //selling
            else if(Integer.parseInt(inputString) == 2) {
                if(store.getType().equals("Smith")) {
                    System.out.println("What would you like to sell?");
                    player.printInventory(player);
                    System.out.println((player.inventory.length + 1) + ". Back");
                    System.out.print("Input: ");
                    inputString = keyboard.nextLine();

                    if(Integer.parseInt(inputString) == (player.inventory.length + 1))
                        continue;
    
                    for(int i = 0; i < player.inventory.length; i++) {
                        if(player.inventory[i] != null && 
                        (inputString.equalsIgnoreCase(player.inventory[i].getName())
                        || Integer.parseInt(inputString) == (i + 1))) {
                            player.playerGold += (player.inventory[i].getValue()); 
                            System.out.println("You sold " + player.inventory[i].getName() + "!");
                            System.out.println("You now have " + player.playerGold + " Gold!");
                            player.inventory[i] = null;
                            break;
                        }
                    }
                }

                else {
                    System.out.println("What would you like to sell?");
                    player.printBackpack(player);
                    System.out.println((player.backpack.length + 1) + ". Back");
                    System.out.print("Input: ");
                    inputString = keyboard.nextLine();

                    if(Integer.parseInt(inputString) == (player.backpack.length + 1))
                        continue;
    
                    for(int i = 0; i < player.backpack.length; i++) {
                        if(player.backpack[i] != null &&
                        (inputString.equalsIgnoreCase(player.backpack[i].getName())
                        || Integer.parseInt(inputString) == (i + 1))) {
                            if(player.backpack[i].getType().equals("Ammo")) {
                                System.out.println("How many would you like to sell?");
                                System.out.print("Input: ");
                                inputString = keyboard.nextLine();

                                if(inputString.equalsIgnoreCase("back"))
                                    continue;

                                else if(Integer.parseInt(inputString) > ((Ammo)player.backpack[i]).getCount()) {
                                    System.out.println("You don't have that many to sell!");
                                    continue;
                                }

                                else {
                                    int count = Integer.parseInt(inputString);
                                    int sellGold;
                                    sellGold = ((count * player.backpack[i].getValue()));
                                    ((Ammo)player.backpack[i]).setCount(((Ammo)player.backpack[i]).getCount() - count);
                                    System.out.println("You sold " + count + " " + player.backpack[i].getName() + "s!");
                                    player.playerGold += sellGold; 
                                    if(((Ammo)player.backpack[i]).getCount() <= 0) {
                                        player.backpack[i] = null;
                                    }
                                }
                            }
                            else {
                                System.out.println("You sold " + player.backpack[i].getName() + "!");
                                player.playerGold += (player.backpack[i].getValue() / 2); 
                                player.backpack[i] = null;

                            }
                            System.out.println("You now have " + player.playerGold + " Gold!");
                            break;
                        }
                    }
                }

                if(Integer.parseInt(inputString) == (player.backpack.length + 1))
                    continue;
            }

        } while (Integer.parseInt(inputString) != 3);

        System.out.println("\"Farewell adventurer!\"");
        System.out.println("--------------------------------------------");
    }

    //tavern interface
    public void enterTavern(Scanner keyboard, Player player, Location location) {
        Random r = new Random();
        String inputString = "";
        int drinksBought = 0;
        int roll = r. nextInt(1, 4);

        Quest[] availableQuests = new Quest[4];
        availableQuests[0] = generateQuest(location);
        availableQuests[1] = generateQuest(location);
        availableQuests[2] = generateQuest(location);
        availableQuests[3] = generateQuest(location);


        System.out.println("--------------------------------------------");
        System.out.println("You enter the tavern. A fire warms you, and a bard is playing a song" +
            "\nThe tavernkeep smiles as you walk in.");

        if (roll == 1)
            System.out.println("\"Looking for a place to rest, adventurer?\"");
        else if(roll == 2)
            System.out.println("\"Stay awhile, and listen.\"");
        else if(roll == 3)
            System.out.println("\"Let me know if you need anything. I have the comfiest beds around!\"");
        else if (roll == 4)
            System.out.println("\"Hello " + player.getPlayerRace() + "!\"");

        System.out.println("You walk up to the counter.");
        do {
            System.out.println("What would you like to do?"); 
            System.out.println("1. Buy a Bed");
            System.out.println("2. Buy a Drink");
            System.out.println("3. Listen to the Bard");
            System.out.println("4. Look at Quests");
            System.out.println("5. Talk to the Tavernkeep");
            System.out.println("6. Leave");
            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            //buying a bed
            if(Integer.parseInt(inputString) == 1){ 
                System.out.println("\"What type of bed would you like, traveler?\"");
                System.out.println("1. Just some hay - 10 gold - At least it's a place to sleep! (Half HP and mana)");
                System.out.println("2. A normal bed - 20 gold - restores you to full HP and mana.");
                System.out.println("3. Deluxe bed - 30 gold - Full health and mana + 10 tempoary HP");
                System.out.println("4. Back");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();

                if(Integer.parseInt(inputString) == 1) {
                    System.out.println("The pile of hay is uncomfortable to sleep on."
                        + "\nAt least you're somewhat warm and dry.");
                    player.playerCurHealth += (player.playerMaxHealth / 2);
                        if (player.playerCurHealth > player.playerMaxHealth)
                            player.playerCurHealth = player.playerMaxHealth;
                    System.out.println("Half your HP has been restored!");
                    System.out.println("You now have " + player.playerCurHealth + " HP!");
                }

                else if(Integer.parseInt(inputString) == 2) {
                    System.out.println("You sink into the bed and quickly fall asleep.");
                    player.playerCurHealth = player.playerMaxHealth;
                    player.curMana = player.maxMana;
                    System.out.println("Health and mana fully restored!");
                        
                }

                else if(Integer.parseInt(inputString) == 3) {
                    System.out.println("This is one of the comfiest beds you've ever slept on!");
                    player.playerCurHealth = (player.playerMaxHealth + 10);
                    player.curMana = player.maxMana;
                    System.out.println("Health fully restored with 10 extra HP, mana fully restored!");
                }

                else if(Integer.parseInt(inputString) == 4)
                    continue;

                else
                    System.out.println("That's not an option!");
            }

            //buying a drink
            else if(Integer.parseInt(inputString) == 2) {
                System.out.println("\"That'll be 5 gold, adventurer.\" (Y/N)");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();
            
                if(inputString.equalsIgnoreCase("Y")) {
                    if (player.playerGold < 5) {
                        System.out.println("You don't have enough gold!");
                        inputString = "0";
                    }
                    else {
                        player.playerGold -= 5;
                        drinksBought++;
                        player.playerDef -= 1;
                        player.playerAtt -=1;
                        player.playerStr +=1;
                        System.out.println("You quickly down the drink. You feel slightly stronger.");
                        System.out.println("Defense -1, Attack -1, Strength +1!");
                        inputString = "0";
                    }
                }
                else if(inputString.equalsIgnoreCase("N")) {
                    System.out.println("\"Alright, what else would you like?");
                    inputString = "0";
                }
            }

            //listening to the bard
            else if(Integer.parseInt(inputString) == 3) {
                if(roll == 1) 
                    System.out.println("The bard is playing a song he calls Ragnar the Red. You feel calm as you sit by the fire.");
                if(roll == 2) 
                    System.out.println("The bard is playing Through the Fire and the Flames." + 
                    "\nHow can he even do that with a lute?? You are equally impressed and confused.");
                if(roll == 3)
                    System.out.println("The bard is playing a nice, peaceful melody");
                if(roll == 4)
                    System.out.println("The bard plays a sad, melancholic song. You feel quite down now.");
            }

            //taking quests
            else if(Integer.parseInt(inputString) == 4) {
                System.out.println("Are you taking a quest or turning one in?");
                System.out.println("1. Taking a quest");
                System.out.println("2. Turning in a quest");
                System.out.println("3. Back");
                System.out.print("Input: ");
                inputString = keyboard.nextLine();

                if(Integer.parseInt(inputString) == 1) {
                    System.out.println("The tavernkeep hands you a list of available quests.");

                    for(int i = 0; i < availableQuests.length; i++) {
                        if(availableQuests[i] == null)
                            System.out.println((i + 1) + ". Taken!");
                        else
                            System.out.println((i + 1) + ". " + availableQuests[i].getName() + "s");
                        
                    }
                    do {
                        System.out.println("Would you like to examine a quest, take a quest, or go back?");
                        System.out.println("1. Examine Quest");
                        System.out.println("2. Take Quest");
                        System.out.println("3. Back");
                        System.out.print("Input: ");
                        inputString = keyboard.nextLine();

                        if(Integer.parseInt(inputString) == 1) {
                            System.out.println("Which quest?");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();

                            if(inputString.equalsIgnoreCase("back"))
                                continue;

                            for(int i = 0; i < availableQuests.length; i++) {
                                if (availableQuests[i] == null && (Integer.parseInt(inputString) == (i + 1))) {
                                    System.out.println("That quest is in your quest log!");
                                    break;
                                }
                                else if(availableQuests[i] != null && Integer.parseInt(inputString) == (i + 1)) {
                                    System.out.println(availableQuests[i].questInfo(availableQuests[i]));
                                    continue;
                                }
                            }
                        }

                        else if (Integer.parseInt(inputString) == 2) {
                            int questChosen = -1;
                            System.out.println("Which quest?");
                            System.out.print("Input: ");
                            inputString = keyboard.nextLine();

                            if(inputString.equalsIgnoreCase("back"))
                                continue;

                            for(int i = 0; i < availableQuests.length; i++) {
                                if(availableQuests[i] == null) {

                                }
                                if (availableQuests[i] == null && (Integer.parseInt(inputString) == (i + 1))) {
                                    System.out.println("That quest is already taken!");
                                    break;
                                }
                                
                                else if(availableQuests[i] != null && Integer.parseInt(inputString) == (i + 1)) {
                                    questChosen = i;
                                }
                                
                            }

                            if(questChosen != -1) {
                                System.out.println("Where in your quest log would you like to add it?");
                                player.printQuestLog(player);
                                System.out.print("Input: ");
                                inputString = keyboard.nextLine();
    
                                if(inputString.equalsIgnoreCase("back"))
                                    continue;
    
                                for(int i = 0; i < player.questLog.length; i++) {
                                    if(player.questLog[i] == null && (Integer.parseInt(inputString) == (i + 1))) {
                                        player.questLog[i] = availableQuests[questChosen];
                                        System.out.println("Quest " + availableQuests[questChosen].getName() + "s added to quest log!");
                                        availableQuests[questChosen] = null;
                                        break;
                                    }
                                    else if(player.questLog[i] != null && 
                                    (Integer.parseInt(inputString) == (i + 1))) {
                                        player.questLog[i] = availableQuests[questChosen];
                                        System.out.println("Quest " + availableQuests[questChosen].getName() + "s added to quest log!");
                                        availableQuests[questChosen] = null;
                                        break;
                                    }
                                }
                            }
                        }

                        else if(Integer.parseInt(inputString) == 3) {
                            continue;
                        }
                    } while (Integer.parseInt(inputString) != 3);
                }

                else if(Integer.parseInt(inputString) == 2) {
                    System.out.println("Which quest are you turning in?");
                    player.printQuestLog(player);
                    System.out.println((player.questLog.length + 1) + ". Back");
                    System.out.print("Input: ");
                    inputString = keyboard.nextLine();

                    if(Integer.parseInt(inputString) == (player.questLog.length + 1))
                        continue;

                    for(int i = 0; i < player.questLog.length; i++) {
                        if(player.questLog[i] != null && 
                        (inputString.equalsIgnoreCase(player.questLog[i].getName())
                        || Integer.parseInt(inputString) == (i + 1))) {
                            if(player.questLog[i].isQuestDone()) {
                                System.out.println("You hand in your quest.");

                                System.out.println("The tavernkeep gives you " + player.questLog[i].getPayout() + " Gold!");
                                player.playerGold += player.questLog[i].getPayout();
                                System.out.println("You now have " + player.getPlayerGold() + " Gold!");

                                System.out.println("You are awarded " + player.questLog[i].getXpReward() + " XP!");
                                player.xp += player.questLog[i].getXpReward();
                                System.out.println("Current XP: " + player.getXp() + " / " + player.getLevelUpXP());
                                if(player.xp > player.levelUpXP)
                                    player.levelUp(keyboard, player);

                                player.questLog[i] = null;
                            }
                            else {
                                System.out.println("That quest isn't complete!");
                            }
                        }
                    }
                }

                else if(Integer.parseInt(inputString) == 3) {
                    continue;
                }

                else {
                    System.out.println("That's not a valid input!");
                }

            }

            //MASSIVE WORK IN PROGRESS
            else if(Integer.parseInt(inputString) == 5) {
                System.out.println("1. \"What can you tell me about the town?\"");
                System.out.println("2. \"Have you heard any rumors lately?\"");
            }

            else if (inputString.equalsIgnoreCase("leave")) {

            }
            
            else {
                System.out.println("That's not a valid input!");
            }

        } while(Integer.parseInt(inputString) != 6);

        if (drinksBought > 0) {
            System.out.println("You sober up before you leave. You feel normal now.");
            player.playerDef += (1 * drinksBought);
            player.playerAtt += (1 * drinksBought);
            player.playerStr -= (1* drinksBought);
        }

        System.out.println("You walk out of the tavern.");
        System.out.println("--------------------------------------------");
    }

    //carriage interface
    public void takeCarriage(Scanner keyboard, Player player) {
        String inputString = "";

        System.out.println("The carriage driver looks at you and says" +
            "\n\"Where would you like to go?\"");

        if (player.location.equals("Bearpoint")) {
            System.out.println("1. Wolfrock - 50 gold");
            System.out.println("2. Leave");

            System.out.print("Input: ");
            inputString = keyboard.nextLine();

            if(Integer.parseInt(inputString) == 1) {
                if(player.playerGold < 50) {
                    System.out.println("You don't have enough gold!");
                }
                else {
                    System.out.println("You pay the carriage driver 50 gold to go to the town of Wolfrock.");
                    player.playerGold -= 50;
                    player.setLocation("Wolfrock");
                    //RavensThicket.enterWolfRock();
                }
            }
            else if(Integer.parseInt(inputString) == 2) {
                System.out.println("\"Maybe another time then traveler!\"");
            }

        }

        else if (player.location.equalsIgnoreCase("Wolfrock")) {

        }
    }

    //finding quest progress
    public void questProgress(Player player, Enemy npc) {
        for(int i = 0; i < player.questLog.length; i++) {
            if(player.questLog[i] != null) {
                if(player.getLocation().equals(player.questLog[i].getLocation())) {
                    if(npc.getName().equals(player.questLog[i].getToKill().getName())) {
                        player.questLog[i].numKills++;
                        System.out.println(player.questLog[i].getName() + " progressed!");
                    }
                }   
    
                if(player.questLog[i].getNumKills() >= player.questLog[i].getNumNeedToKill()) {
                    System.out.println("Quest complete! Return to the tavern for your reward!");
                    player.questLog[i].questDone = true;
                }
    
                if(player.questLog[i].getNumKills() > player.questLog[i].getNumNeedToKill()) {
                    player.questLog[i].setNumKills(player.questLog[i].getNumNeedToKill());
                }
            }
        }
    }

    //generating quests
    public Quest generateQuest(Location location) {
        Random r = new Random();
        int roll = r.nextInt(0, location.enemies.length);
        
        String name = ("Kill " + location.enemies[roll].getName());
        Enemy toKill = location.enemies[roll];
        int numNeedToKill = r.nextInt(1, 10);
        int payout = (numNeedToKill * 3); // times quest level??
        int xpReward = (numNeedToKill * 1);
        int numKills = 0;
        boolean questDone = false;

        Quest quest = new Quest(name, payout, xpReward, location, toKill, numNeedToKill, numKills, questDone);
        return quest;
    }
    


}

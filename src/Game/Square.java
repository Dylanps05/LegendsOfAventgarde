package Game;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Square implements Serializable {
    Methods method = new Methods();

    //fields
    //Location location;
    int combatChance;
    String name;
    String abb;
    String description;
    String actionDesc;
    String investDesc;
    Enemy[] enemies;
    Item[] items;
    boolean found;
    boolean town;
    boolean POI;

    //constructor
    public Square (String name, String abb, String description, String actionDesc, String investDesc, int combatChance, Enemy[] enemies, Item[] items, boolean found, boolean town, boolean POI) {
        //this.location = location;
        this.name = name;
        this.abb = abb;
        this.description = description;
        this.actionDesc = actionDesc;
        this.investDesc = investDesc;
        this.combatChance = combatChance;
        this.enemies = enemies;
        this.items = items;
        this.found = found;
        this.town = town;
        this.POI = POI;
    }

    //methods
    public void rollCombat(Player player, Square square, Scanner keyboard) throws Exception {
        Random r = new Random();
        int roll = r.nextInt(0, 100);

        // System.out.println("Combat Chance: " + square.getCombatChance());
        // System.out.println("Roll: " + roll);

        if(roll < square.getCombatChance()) {
            roll = r.nextInt(0,square.getEnemies().length);
            Enemy enemy = square.getEnemies()[roll];
            System.out.println("Suddenly, you are attacked by a " + enemy.getName() + "!");
            method.combat(keyboard, player, enemy);
        }
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getCombatChance() {
        return combatChance;
    }

    public void setCombatChance(int combatChance) {
        this.combatChance = combatChance;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
        this.found = true;
    }

    public boolean isPOI() {
        return POI;
    }

    public void setPOI(boolean pOI) {
        POI = pOI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Methods getMethod() {
        return method;
    }

    public void setMethod(Methods method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public String getInvestDesc() {
        return investDesc;
    }

    public void setInvestDesc(String investDesc) {
        this.investDesc = investDesc;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }
    
    //GENERAL SQUARE METHOD
    /*public void rwEnterSquare(Player player, Scanner keyboard, Square[][] squares) throws Exception {
        
        String inputString = "";
        Random r = new Random();
        int x = player.getPlayerx();
        int y = player.getPlayery();
        squares[x][y].found = true;

        boolean valid = false;

        if (squares[x][y].isTown()) {
            System.out.println("--------------------------------------------");
            enterBearPoint(keyboard, player, true, squares);
        }
        else if (squares[x][y].isPOI()) { 
            System.out.println("--------------------------------------------");
            rwEnterPOI(player, keyboard);
        }
        else {
            System.out.println("--------------------------------------------");
            player.setLocation("RedeemersWoods");
            
                System.out.println(squares[x][y].description);
                squares[x][y].rollCombat(player, squares[x][y], keyboard);
                System.out.println(squares[x][y].actionDesc);
                
                do {
                    System.out.print("Input: ");
                    inputString = keyboard.nextLine();
                    if (inputString.equalsIgnoreCase("leave")) {
                        valid = true;
                        player.playerMove(keyboard, player);
                        rwEnterSquare(player, keyboard, squares);
                    }
                    else if (inputString.equalsIgnoreCase("investigate")) {
                        int roll = r.nextInt(0,squares[x][y].items.length);
                        if (squares[x][y].items[roll] == null)
                            System.out.println(squares[x][y].investDesc);
                        else {
                            player.lootItem(player, keyboard, squares[x][y].items, roll);
                            squares[x][y].items[roll] = null;
                            System.out.println("What next? Leave, menu, or investigate further?");
                        }
                    }
                    else if (inputString.equalsIgnoreCase("menu")) {
                        player.printPlayerMenu(keyboard, player);
                    }
                    else {
                        System.out.println("Not a valid input!");
                    }
                } while (!valid);
            }
    } */
}

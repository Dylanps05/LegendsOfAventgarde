package Game;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class RedeemersWood implements Serializable {

    /*The Reedemer's Wood, where our hero begins their adventure. Located near the bottom of the Kingdom of Aventgarde,
    this small forest is relatively peaceful and quiet. The main village is known as Bearpoint, aptly named for the abundance of
    bears that are often seen nearby. Most of the people who live here make a living being lumberjacks or hunters,
    and are often troubled by goblins that steal their livelihood. However, a forgotten crypt nearby sometimes spooks those who get close.
    */
    
    static Methods method = new Methods();

    //Loot Tables
    static Item[] wolfLootTable = {null, null, Loot.getWolfFur(), Loot.getWolfFang(), Loot.getWolfTail(), Loot.getWolfSkull()};
    static Item[] giantRatLootTable = {null, null, Loot.getRatTail()};
    static Item[] goblinLootTable = {null, null, null, null, Loot.getHealthPotion(), Loot.getManaPotion(), Loot.getBronzeShield(), 
        Loot.getNecklace(), Loot.getArrow(), Loot.getBronzeDagger()};
    static Item[] bearLootTable = {null, null, Loot.getBearFur(), Loot.getBearTooth(), Loot.getBearPaw()};
    static Item[] banditLootTable = {null, Loot.getHealthPotion(), Loot.getManaPotion(), Loot.getBronzeAxe(), Loot.getBronzeShield(),
        Loot.getBronzeShield(), Loot.getArrow(), null};
    static Item[] fairyLootTable = {null};

    //enemies
    static Enemy wolfCub = new Enemy("Wolf Cub", 1, 8, 0, 0, 0, 2, 1, 3, 1, 2, wolfLootTable);
    static Enemy wolf1 = new Enemy("Wolf", 3, 12, 1, 1, 0, 3, 1, 3, 2, 4, wolfLootTable);
    static Enemy wolf2 = new Enemy("Wolf", 2, 10, 1, 0, 0, 2, 1, 2, 1, 3, wolfLootTable);
    static Enemy giantRat = new Enemy("Giant Rat", 2, 13, 0, 1, 0, 2, 1, 3, 1, 3, giantRatLootTable);
    static Enemy goblin = new Enemy("Goblin", 1, 10, 0, 1, 0, 2, 1, 2, 1, 3, goblinLootTable);
    static Enemy goblin2 = new Enemy("Goblin", 3, 13, 0, 1, 0, 3, 1, 3, 2, 4, goblinLootTable);
    static Enemy bear = new Enemy("Bear", 5, 20, 1, 1, 0, 4, 1, 5, 3, 8, bearLootTable);
    static Enemy bearCub = new Enemy("bearCub", 3, 15, 0, 1, 0, 3, 1, 3, 2, 5, bearLootTable);
    static Enemy bandit = new Enemy("Bandit", 2, 12, 1, 1, 0, 3, 0, 2, 3, 5, banditLootTable);
    static Enemy bandit2 = new Enemy("bandit",  1,  10, 0, 0, 0, 2, 0, 2, 2, 4, banditLootTable);
    static Enemy fairy = new Enemy("Fairy", 1, 8, 1, 3, 0, 2, 1, 2, 1, 5, fairyLootTable);

    //enemy arrays
    static Enemy[] forestEnemies = {wolfCub, wolf1, wolf2, giantRat, goblin, goblin2, bear, bandit, bandit2};
    static Enemy[] wildlife = {wolfCub, wolf1, wolf2, giantRat, bear};
    static Enemy[] goblins = {goblin, goblin2};
    static Enemy[] bandits = {bandit, bandit2};

    //stores
    static Item[] bpGeneralStoreItems = {Loot.getHealthPotion(), Loot.getManaPotion(), Loot.getArrow()};
    static Item[] bpBlacksmithItems = {Loot.getBronzeShield(), Loot.getBronzeAxe(), Loot.getBronzeSword(), Loot.getTwoHandedAxe(), Loot.getBronzeDagger()};
    static Item[] bpLeatherstoreItems = {Loot.getLeatherCoif(), Loot.getLeatherChaps(), Loot.getLeatherTunic(), Loot.getLeatherBoots(), Loot.getLeatherGloves()};
    static Item[] bpSpellShopItems = {Loot.getFireBolt(), Loot.getIceBolt(), Loot.getWaterBolt(), Loot.getWindStrike()};

    static Store bpGeneralStore = new Store("Hilgurd's General Store", "Handred", "General Store", bpGeneralStoreItems);
    static Store bpBlackSmith = new Store("Sam's Smithy", "Sam", "Smith", bpBlacksmithItems);
    static Store bpLeatherStore = new Store("Liandra's Leather", "Liandra", "Leatherworker", bpLeatherstoreItems, false, "Rucksack", "Bag", 1, 50);
    static Store bpSpellShop = new Store ("Sophie's Spells", "Sophie", "Spellshop", bpSpellShopItems);

    static Store[] bpMarket = new Store[4];
    
    //generating map    
    public static Square rwGenerateSquare() {
        Random r = new Random();
        
        int roll = r.nextInt(0, 100);

        //If roll is less than 50, roll the generic forest square.
        if (roll <= 50) {
            String name = "genericForest";
            String abb = "^^ ";
            String description = "You journey deeper into the forest, feeling a gentle breeze as you pass through the trees.";
            String actionDesc = "You stand in the middle of the towering trees. What would you like to do? \nLeave, menu, or investigate?";
            String investDesc = "You find nothing of note in this section of the forest.";
            Item [] genericForestItems = {null, null, null, null, new Item("Feather", 1)};

            roll = r.nextInt(0,100);
            int combatChance = roll;
        
            Enemy[][] totalEnemies = {wildlife, goblins, bandits};
            roll = r.nextInt(0, totalEnemies.length);

            Enemy[] enemies = totalEnemies[roll];
            
            return new Square(name, abb, description, actionDesc, investDesc, combatChance, enemies, genericForestItems, false, false, false);
        }

        //if roll is greater than 50, roll a special square.
        else {
            String[] names = {"sunnyMeadow",
                            "mossyClearing",
                            "silentGlade",
                            "stonyCrossing",
                            "berryGrove",
                            "brokenCarriage",
                            "willowTree",
                            "fernGlen"};

            String[] abbs = {"Sm ",
                            "Mc ",
                            "Sg ",
                            "Sc ",
                            "Bg ",
                            "Bc ",
                            "Wt ",
                            "Fg "};

            String[] descriptions = {"""
                    You come across a small, sunny meadow.
                    It's a cheerful patch of open grass, dotted with wildflowers of yellow, blue, and red.
                    Bees buzz lazily from bloom to bloom, and birds flit between the trees nearby.""",
                    """
                    You come upon a small, mossy clearing. 
                    It is a serene clearing bathed in dappled sunlight.
                    You feel a small breeze as leaves rustle from the trees. 
                    The ground is soft, blanketed with thick, fragrant moss, and the sound of a nearby stream trickles faintly.""",
                    """
                    You stumble upon a silent glade - a peculiar patch of forest where no birds sing and no breeze stirs. 
                    It's eerily quiet, with only the creak of old trees for company.""",
                    """
                    You come across a small, stony crossing. 
                    Flat stones form a natural bridge over a small stream, smooth from years of water flow. 
                    It's a perfect spot to pause and listen to the gentle rush of water.""",
                    """
                    You come across a small grove. 
                    Low bushes bursting with ripe berries cluster around this area. 
                    The sweet scent of fruit fills the air, and the sound of rustling leaves hints at small animals foraging nearby.""",
                    """
                    You come across a broken carriage. 
                    It is toppled over on its side, with wood splintered around it and a wheel missing. 
                    Whatever contents it had seems to be gone.""",
                    """
                    You come across a large willow tree, standing over a small pond.
                    As the wind blows, you hear its branches crackle and leaves float off the tree.
                    The pond appears still, its blue water reflecting whatever light shines through the surrounding trees.""",
                    """
                    You come across a small glen filled with vibrant green ferns.
                    A light breeze causes the fronds to sway gently.
                    The earthly smell of damp soil is rich and refreshing."""
                    };

            String[] actionDescs = {"You stand in the field of flowers. What you like to do?\nLeave, menu, or investigate?",
                                "You stand in the clearing, your feet sinking into the moss. What would you like to do?\nLeave, menu, or investigate?",
                                "You stand in the silence of the glade. What you like to do?\nLeave, menu, or investigate?",
                                "You stand by the small stream. What you like to do?\nLeave, menu, or investigate?",
                                "You stand, smelling the sweetness of the berries. What you like to do?\nLeave, menu or investigate?",
                                "You stand by the ruined carriage. What you like to do?\nLeave, menu, or investigate?",
                                "You stand by the willow tree. What would you like to do?\nLeave, menu, or investigate?",
                                "You stand by the ferns. What would you like to do?\nLeave, menu, or investigate?"
                                };

            String[] investDescs = {"You look among the flowers of the meadow, but none catches your eye.",
                                "You find nothing of note in the clearing.",
                                "You break the still silence by turning some stones, but find nothing.",
                                "You peer into the stream, but see nothing of note but your reflection.",
                                "You search through the berry bushes, but find nothing that piques your interest.",
                                "You search through the wreckage of the cart, but it seems it's pecked clean.",
                                "You search through the surrounding foliage near the tree, but find nothing of interest.",
                                "You find nothing intersting in the ferns."
                                };

            Item[] sunnyMeadowItems = {null, null, new Item("Dandelion", 3), new Item("Cool Stick", 1)};
            Item[] mossyClearingItems = {null, null, new Item("Moss-Covered Rock", 4)};
            Item[] silentGladeItems = {null, null, new Item("Black Rose", 14), null, null};
            Item[] stonyCrossingItems = {null, null, new Item("Perfect-Looking Stone", 5), new Item("Shiny Fish Scale", 5), null, null};
            Item[] berryGroveItems = {null, null, new Item("Sweet-Smelling Berry", 3), new Item("Sweet-Smelling Berry", 3), null};
            Item[] brokenCarriageItems = {null, null, new Item("Small Ring", 15), null, null, null, null};
            Item[] willowTreeItems = {null, null, null, new Item("Willow Branch",   2)};
            Item[] fernGlenItems = {null, null, new Item("Spiky Fern", 2)};
            Item[][] totalItems = {sunnyMeadowItems, mossyClearingItems, silentGladeItems, stonyCrossingItems, berryGroveItems, brokenCarriageItems, willowTreeItems, fernGlenItems};

            roll = r.nextInt(0,names.length);
            String name = names[roll];
            String abb = abbs[roll];
            String description = descriptions[roll];
            String actionDesc = actionDescs[roll];
            String investDesc = investDescs[roll];
            Item[] items = totalItems[roll];

            roll = r.nextInt(0,100);
            int combatChance = roll;

            Enemy[][] totalEnemies = {forestEnemies, wildlife, goblins, bandits};
            roll = r.nextInt(0, totalEnemies.length);

            Enemy[] enemies = totalEnemies[roll];

            return new Square(name, abb, description, actionDesc, investDesc, combatChance, enemies, items, false, false, false);
        }
    }

    static Square[][] rwGenerateMap() {
        Square[][] map = new Square[7][7];
        for (int x = 0; x < 7; x++) {
            for (int i = 0; i < 7; i++) {
                map[x][i] = rwGenerateSquare();
            }
        }

        map[3][3].setTown(true); //Bearpoint
        map[0][0].setPOI(true); //Goblin Camp
        map[6][6].setPOI(true); //Wolf Den
        map[5][2].setPOI(true); //crypt
        return map;
    }

    static Location RedeemersWoods = new Location("RedeemersWoods", rwGenerateMap(), forestEnemies);

    //Creating town of Bearpoint
    public static void enterBearPoint(Scanner keyboard, Player player, boolean inBearPoint, Square[][] squares) throws Exception {

        Random r = new Random();
        String inputString = "";
        int roll = r.nextInt(1, 5);

        bpMarket[0] = bpGeneralStore;
        bpMarket[1] = bpBlackSmith;
        bpMarket[2] = bpLeatherStore;
        bpMarket[3] = bpSpellShop;

        System.out.println("You enter the town of Bearpoint.");
        
        switch(roll) {
            case 1 -> System.out.println("The town is bustling with people getting ready to start their day.");
            case 2 -> System.out.println("The town is quiet as the sound of rain hits the rooftops.");
            case 3 -> System.out.println("Most of the town is asleep as crickets chirp through the night.");
            case 4 -> System.out.println("The town is illuminated by the morning sun shining through the trees.");
            case 5 -> System.out.println("The town is rife with life as people return from a hard day at work.");
        }

        do {
            System.out.print("""
            What would you like to do?
            1. Visit the Market
            2. Visit the Tavern
            3. Take the Carriage
            4. Leave the town? 
            Input:  """);
            inputString = keyboard.nextLine();

            if(inputString.equalsIgnoreCase("menu"))
                player.printPlayerMenu(keyboard, player);
            else if(Integer.parseInt(inputString) == 1) {
                method.enterMarket(keyboard, player, RedeemersWood.bpMarket);
            }
            else if(Integer.parseInt(inputString) == 2) {
                method.enterTavern(keyboard, player, RedeemersWood.RedeemersWoods);
            }
            else if(Integer.parseInt(inputString) == 3) {
                method.takeCarriage(keyboard, player);
            }
            else if(Integer.parseInt(inputString) == 4) {
                System.out.println("You exit the town of Bearpoint and head out into the Redeemer's Woods.");
                inBearPoint = false;
                player.playerMove(keyboard, player);
                rwEnterSquare(player, keyboard, squares);
            }

        } while(inBearPoint);
        
    }

    //interacting with map
    public static void rwEnterSquare(Player player, Scanner keyboard, Square[][] squares) throws Exception {
        
        String inputString = "";
        Random r = new Random();

        if(player.getPlayerx() >= RedeemersWood.RedeemersWoods.squares.length) {
            System.out.println("You feel you shouldn't venture further into the woods.");
            player.setPlayerx(player.getPlayerx() - 1);
        }
        else if(player.getPlayerx() < 0) {
            System.out.println("You feel you shouldn't venture further into the woods.");
            player.setPlayerx(player.getPlayerx() + 1);
        }
        else if(player.getPlayery() >= RedeemersWood.RedeemersWoods.squares.length) {
            System.out.println("You feel you shouldn't venture further into the woods.");
            player.setPlayery(player.getPlayery() - 1);
        }
        else if(player.getPlayery() < 0) {
            System.out.println("You feel you shouldn't venture further into the woods.");
            player.setPlayery(player.getPlayery() + 1);
        }

        int x = player.getPlayerx();
        int y = player.getPlayery();
        squares[x][y].setFound(true);

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
                        if (squares[x][y].items[roll] == null) {
                            System.out.println(squares[x][y].investDesc);
                            System.out.println("What next? Leave, menu, or investigate further?");
                        }
                        else {
                            player.lootItem(player, keyboard, squares[x][y].items, roll);
                            squares[x][y].items[roll] = null;
                            System.out.println("What next? Leave, menu, or investigate further?");
                        }
                    }
                    else if (inputString.equalsIgnoreCase("menu")) {
                        player.printPlayerMenu(keyboard, player);
                        System.out.println("What next? Leave, menu, or investigate further?");
                    }
                    else {
                        System.out.println("Not a valid input!");
                    }
                } while (!valid);
            }
    }


    private static void rwEnterPOI(Player player, Scanner keyboard) {

            //Goblin Camp
            if(player.getPlayerx() == 0 && player.getPlayery() == 0) {
                System.out.println("""
                    You come across a wall of wooden logs, carved with spikes on the top.
                    Smoke billows out from behind the walls, rising into the sky.
                    Behind the gates, you can hear sounds of what appears to be goblins rustling about.
                    You muster your breath, open the gates, and head inside.
                        """);
            }

            //Wolf Den
            if(player.getPlayerx() == 6 && player.getPlayery() == 6) {
                
            }

            //Crypt
            if(player.getPlayerx() == 5 && player.getPlayery() == 2) {
                
            }
    }
}
    

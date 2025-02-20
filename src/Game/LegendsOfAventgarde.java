package Game;

import java.util.Scanner;

public class LegendsOfAventgarde {
    public static void main(String[] args) throws Exception {
        //itializing objects
        Scanner keyboard = new Scanner(System.in);
        Methods method = new Methods();
        Player player = new Player();

        //fields
        String inputString = "";
        boolean valid = false;
        boolean loadedGame = false;
    
        //Welcome Screen
        System.out.println("--------------------------------------------");
        System.out.println("Welcome to Legends of Aventgarde!");
        System.out.println("Would you like to load a save? (Y/N)");
        System.out.print("Input: ");
        inputString = keyboard.nextLine();
        do {
            if (inputString.equalsIgnoreCase("Y")) {
                valid = true;
                player = method.loadGame(keyboard);
                loadedGame = true;
            }
            else if (inputString.equalsIgnoreCase("N")) {
                valid = true;
                player = player.characterCreation(keyboard, player);
                loadedGame = false;
            }
            else {
                System.out.println("Please enter Y or N!");
                inputString = keyboard.nextLine();
            }

        } while (valid == false);
       
        System.out.println("Welcome to Aventgarde, " + player.getPlayerName() + ", the " + player.getPlayerRace() 
                    + " " + player.getPlayerClass() + "!");
        if(!loadedGame) {
            System.out.println("--------------------------------------------");
            System.out.println("""
                               You begin your adventure in the town of Bearpoint - a small, quiet town nested in 
                               the Redeemer's Woods on the southern edge of the kingdom.""");
            player.setLocation("RedeemersWoods");
            RedeemersWood.enterBearPoint(keyboard, player, true, RedeemersWood.RedeemersWoods.squares);
        }
        
        else if(loadedGame) {
            RedeemersWood.rwEnterSquare(player, keyboard, RedeemersWood.RedeemersWoods.squares);
        }

        else {
            System.out.println("Something went wrong here. Please reload the game.");
        }
       
        keyboard.close();

    }

}

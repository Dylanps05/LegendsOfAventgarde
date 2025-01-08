package Game;

import java.io.Serializable;
import java.util.Random;

public class Quest implements Serializable{

    //fields
    public int payout;
    public int xpReward;
    public String location;
    public String name;
    public Enemy toKill;
    public int numNeedToKill;
    public int numKills;
    public Item gatherItem;
    public int itemsToGather;
    public int itemsGathered;
    public boolean questDone;
    

    //constructor
    public Quest(String name, int payout, int xpReward, Location location, Enemy toKill, int nuNeedToKill, int numKills, boolean questDone) {
        this.name = name;
        this.payout = payout;
        this.xpReward = xpReward;
        this.location = location.getName();
        this.toKill = toKill;
        this.numNeedToKill = nuNeedToKill;
        this.numKills = numKills;
        this.questDone = questDone;
    }

    //quest info
    public String questInfo(Quest quest) {
        String string = "";
        string += quest.location + "\n";
        string += quest.name + "\n";
        string += quest.payout + " Gold\n";
        string += quest.xpReward + " XP\n";
        string += quest.numKills + " / " + quest.numNeedToKill + " killed";

        return string;
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
        //int XPReward
        boolean questDone = false;

        Quest quest = new Quest(name, payout, xpReward, location, toKill, numNeedToKill, numKills, questDone);
        return quest;
    }

    //getters and setters
    public int getPayout() {
        return payout;
    }
    public void setPayout(int payout) {
        this.payout = payout;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Enemy getToKill() {
        return toKill;
    }
    public void setToKill(Enemy toKill) {
        this.toKill = toKill;
    }
    public int getNumNeedToKill() {
        return numNeedToKill;
    }
    public void setNumNeedToKill(int nuNeedToKill) {
        this.numNeedToKill = nuNeedToKill;
    }
    public int getNumKills() {
        return numKills;
    }
    public void setNumKills(int numKills) {
        this.numKills = numKills;
    }
    public Item getGatherItem() {
        return gatherItem;
    }
    public void setGatherItem(Item gatherItem) {
        this.gatherItem = gatherItem;
    }
    public int getItemsToGather() {
        return itemsToGather;
    }
    public void setItemsToGather(int itemsToGather) {
        this.itemsToGather = itemsToGather;
    }
    public int getItemsGathered() {
        return itemsGathered;
    }
    public void setItemsGathered(int itemsGathered) {
        this.itemsGathered = itemsGathered;
    }
    public boolean isQuestDone() {
        return questDone;
    }
    public void setQuestDone(boolean questDone) {
        this.questDone = questDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getXpReward() {
        return xpReward;
    }

    public void setXpReward(int xpReward) {
        this.xpReward = xpReward;
    }
    
}

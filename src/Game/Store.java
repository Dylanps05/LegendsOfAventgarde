package Game;
import java.io.Serializable;

public class Store implements Serializable {
    //Loot loot = new Loot();

    String name;
    String clerk;
    String type;
    Item[] items;

    //leatherworker
    boolean upgradeBag;
    String upgradeName;
    String upgradeFrom;
    int upgradeSize;
    int upgradePrice;

    //constructor
    public Store (String name, String clerk, String type, Item[] items) {
        this.name = name;
        this.clerk = clerk;
        this.type = type;
        this.items = items;
    }

    //Leatherworkers
    public Store (String name, String clerk, String type, Item[] items, 
            boolean upgradeBag, String upgradeName, String upgradeFrom, int upgradeSize, int upgradePrice) {
        this.name = name;
        this.clerk = clerk;
        this.type = type;
        this.items = items;
        this.upgradeBag = upgradeBag;
        this.upgradeFrom = upgradeFrom;
        this.upgradeName = upgradeName;
        this.upgradeSize = upgradeSize;
        this.upgradePrice = upgradePrice;
    }

    //methods
    public void printShop(Store store) {
        for(int i = 0; i < store.items.length; i++) {
            System.out.println((i + 1) + ". " + store.items[i].getName() + " - " + store.items[i].getValue() * 2 + " Gold");
        }
    }

    //getters and setters
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getClerk() {
        return clerk;
    }


    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public boolean isUpgradeBag() {
        return upgradeBag;
    }


    public void setUpgradeBag(boolean upgradeBag) {
        this.upgradeBag = upgradeBag;
    }


    public int getUpgradeSize() {
        return upgradeSize;
    }


    public void setUpgradeSize(int upgradeSize) {
        this.upgradeSize = upgradeSize;
    }

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public void setUpgradePrice(int upgradePrice) {
        this.upgradePrice = upgradePrice;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public void setUpgradeName(String upgradeName) {
        this.upgradeName = upgradeName;
    }

    public String getUpgradeFrom() {
        return upgradeFrom;
    }

    public void setUpgradeFrom(String upgradeFrom) {
        this.upgradeFrom = upgradeFrom;
    }
    
    
    
    

    
}

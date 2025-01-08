package Game;

import java.io.Serializable;

public class Item implements Serializable {

    //fields
    public String name;
    public int value;

    //constructor
    public Item(String name, int value) {
        setName(name);
        setValue(value);
    }

    //methods
    @Override
    public String toString() {
        return(getName()) + "\nValue: " + getValue() + " Gold";
    }

    public String getType() {
        return "Item";
    }

    //getters and setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    



    
    
}

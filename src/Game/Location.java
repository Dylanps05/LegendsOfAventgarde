package Game;

import java.io.Serializable;

public class Location implements Serializable {
    //fields
    private String name;
    public Square[][] squares;
    public Enemy[] enemies;


    //constructor
    public Location(String name, Square[][] squares, Enemy[] enemies) {
        this.name = name;
        this.squares = squares;
        this.enemies = enemies;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    
}

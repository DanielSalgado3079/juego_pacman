package model;

public class Wall extends ObjectGame {

    public Wall(int x, int y) {
        super(x, y, uploadImage("wall.png"), WALL);
    }

}

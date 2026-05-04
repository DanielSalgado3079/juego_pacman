package model;

/**
 * El punto es estatico, en la matris esta representado por el numero 2
 * y cuando el pacman se lo come le da 10 puntos
 */
public class Point extends ObjectGame {
    public static final int VALUE = 10;


    public Point(int x, int y) {
        super(x, y, uploadImage("pill.png"), POINT);
    }
}

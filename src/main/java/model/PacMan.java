package model;

import java.awt.image.BufferedImage;

/**
 * Clase pacman personaje principal del juego, gestiona
 * su posición
 */
public class PacMan extends Entity {
    // constantes para darle la posición inicial a pacman
    private static final int X_INITIAL = 240;
    private static final int Y_INITIAL = 432;
    private static final int SPEED = 1;
    private static final int INITIAL_LIVES = 3;

    //Coordenadas tuneles laterales (Fila X Pixeles)
    //Ejemplo = fila 9 X 24 px

    //Coordenadas del alto del tablero
    private static final int Y_TUNNEL = 216;

    //Coordinadas del ancho del tablero
    // 21 columnas y 24 px
    private static final int X_TUNNEL = 504;

    //Puntuación
    private int score;

    //Vidas
    private int lives;

    //Variable para ver si tiene la boca abierta
    private boolean openMouth;

    //Para pausar
    private boolean paused;

    //Enter
    private boolean enter;

    //Getters y Setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isOpenMouth() {
        return openMouth;
    }

    public void setOpenMouth(boolean openMouth) {
        this.openMouth = openMouth;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    //Constructor por defecto
    public PacMan() {
        super(X_INITIAL, Y_INITIAL, uploadImage("pacman_left.png"));
        this.score = 0;
        this.lives = INITIAL_LIVES;
        this.openMouth = true;
        this.paused = false;
        this.enter = false;
        setDirection(DIR_LEFT);
    }

    /**
     * Método para reiniciar la posición y la dirección
     * Cuando pierde una vida
     */
    public void resetPosition() {
        setX(X_INITIAL);
        setY(Y_INITIAL);
        setDirection(DIR_LEFT);
        setActive(true);
        setSprite(uploadImage("pacman_left.png"));
    }

    /**
     * Método para reiniciar completamente
     */
    public void resetAll() {
        resetPosition();
        this.lives = INITIAL_LIVES;
        this.score = 0;
    }


    public PacMan(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    /**
     * Mueve a Pacman según su dirección actual y gestiona el túnel lateral.
     */
    @Override
    public void update() {
        // Túnel: sale por la derecha → entra por la izquierda
        if (getX() > X_TUNNEL && getY() == Y_TUNNEL) {
            setX(0);
        }
        // Túnel: sale por la izquierda → entra por la derecha
        if (getX() < 0 && getY() == Y_TUNNEL) {
            setX(X_TUNNEL);
        }

        setX(getX() + calculateDx() * SPEED);
        setY(getY() + calculateDy() * SPEED);
    }

    /**
     * Suma puntos a la puntuación acumulada.
     */
    public void addPoint(int point) {
        this.score += point;
    }

    /**
     * Descuenta una vida.
     */
    public void loseLive() {
        if (lives > 0) lives--;
    }

    /**
     * Actualiza el sprite según la dirección y alterna la animación de boca.
     * Se llama cada ciertos frames para que la boca "masque".
     */
    public void updateSprite(int direction) {
        String file;
        if (openMouth) {
            file = "pacman_closed.png";
        } else {
            if (direction == DIR_LEFT) {
                file = "pacman_left.png";
            } else if (direction == DIR_RIGHT) {
                file = "pacman_right.png";
            } else if (direction == DIR_UP) {
                file = "pacman_up.png";
            } else if (direction == DIR_DOWN) {
                file = "pacman_down.png";
            } else file = "pacman_left.png";
        }
        setSprite(uploadImage(file));
        openMouth = !openMouth;
    }

    /**
     * Activa o desactiva la pausa.
     */
    public void updatePause() {
        paused = !paused;
    }
}

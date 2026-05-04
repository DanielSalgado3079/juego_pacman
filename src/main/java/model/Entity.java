package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Clase abstracta base para crear cada entidad del juego
 *
 * @author Daniel Blandón
 * @version 1.0
 */
public abstract class Entity {
    /**
     * pocisión en x
     */

    private int x;
    /**
     * pisición en y
     */

    private int y;
    private int width;
    private int height;
    private BufferedImage sprite;
    private boolean active;
    private int direction;

    //Constantes manejo dirección
    protected static final int DIR_NONE = 0;
    protected static final int DIR_LEFT = 1;
    protected static final int DIR_RIGHT = 3;
    protected static final int DIR_UP = 2;
    protected static final int DIR_DOWN = 4;

    public Entity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }

        this.active = true;
        this.direction = DIR_NONE;
    }

    //Getters y Setters


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        if (sprite != null) {

            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
        this.sprite = sprite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Por que cada clase define su propia logica de actualización de identidad
     */
    public abstract void update();

    /**
     * Método para cuando haya un colision
     *
     * @return retorna exactamente el lugar de colisión
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Metodo que predice cuanddo va a haber una colision
     *
     * @param preX
     * @param preY
     * @return retorna cuando los objetos estabn muy cerca a una colisión
     */
    public Rectangle getHitbox(int preX, int preY) {
        return new Rectangle(preX, preY, width, height);
    }

    /**
     * Calcula el desplazamiento horizontal según la dirección actual.
     * Devuelve -1, 0 o +1.
     */
    public int calculateDx() {
        if (direction == DIR_LEFT) return -1;
        if (direction == DIR_RIGHT) return 1;
        return 0;
    }

    /**
     * Calcula el desplazamiento vertical según la dirección actual.
     * Devuelve -1, 0 o +1.
     */
    public int calculateDy() {
        if (direction == DIR_UP) return -1;
        if (direction == DIR_DOWN) return 1;
        return 0;
    }

    /**
     * Carga una imagen desde la carpeta de recursos.
     * Uso: Entidad.cargarImagen("pacman_left.png")
     */
    public static BufferedImage uploadImage(String nombre) {
        try {
            InputStream is = Entity.class.getResourceAsStream("/resources/images/" + nombre);
            if (is == null) {
                System.err.println("Imagen no encontrada: " + nombre);
                return null;
            }
            return ImageIO.read(is);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + nombre);
            return null;
        }
    }
}

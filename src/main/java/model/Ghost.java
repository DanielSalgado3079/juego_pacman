package model;

import java.awt.image.BufferedImage;


/**
 * Clase abstracta va a ser el papa de los fantasmas
 * define el movimiento basico y el estado comestible
 * cado hijo implementa su propia logica y tambien podemos manipular el tiempo del estado en el que el fantasma se puede comer
 */
public abstract class Ghost extends Entity {

    //Constante del tiempo en que estará en estado comestible el fantasma
    //9 seg x 60 FPS = 540
    private static final int DURATION_EATEABLE = 540;
    //variables
    //Velocidad
    private int speed;
    //Estado comestible
    private boolean stateEatable;
    //Contador de comestible
    private int countEatable;
    private BufferedImage spriteOriginal;

    //Constructor


    public Ghost(int x, int y, String sprite, int dirTinitial, int speed) {
        //Al metodo le entra la ruta y devuelve la imagen de tipo Buffered
        super(x, y, uploadImage(sprite));
        this.speed = speed;
        this.stateEatable = false;
        this.countEatable = 0;
        this.spriteOriginal = uploadImage(sprite);
        setDirection(dirTinitial);
    }

    /**
     * Cada subclase decide como cambiar de derecciones al chocar con un muro
     * Blinky: persigue, Pinky: persigue. (Polimorfismo)
     */

    public abstract void changeDirection(int posPacX, int posPacY);

    /**
     * Reinicia el fantasma a su estado y posición inicial
     */
    public abstract void restart();


    /**
     * Método encargado de invertir la dirección del fantasma
     */
    public void reverseDirection() {
        int direction = getDirection();
        if (direction == DIR_LEFT) {
            setDirection(DIR_RIGHT);
        } else if (direction == DIR_RIGHT) {
            setDirection(DIR_LEFT);
        } else if (direction == DIR_DOWN) {
            setDirection(DIR_UP);
        } else if (direction == DIR_UP) {
            setDirection(DIR_DOWN);
        }
    }

    /**
     * Activa o desactiva el estado comible del fantasma
     * al cativerse cambie el sprite al fantasma y empieza la cuenta atras.
     */

    public void setStateEatable(boolean active) {
        if (active) {
            this.stateEatable = true;
            countEatable = DURATION_EATEABLE;
            setSprite(uploadImage("ghost_teal.png"));
        } else {
            this.stateEatable = false;
            countEatable = 0;
            setSprite(spriteOriginal);
        }
    }


    /**
     * Se sobreescribe el metodo actualizar para que mueva los fantasmas y descuente el contador comestible si el fantasma esta activo, si no no descuenta
     */
    @Override
    public void update() {
        setX(getX() + calculateDx() * speed);
        setY(getY() + calculateDy() * speed);

        //Si esta activo se reduce el contador para que deje de estar comestible en algun momento
        if (stateEatable) {
            countEatable--;
            //cuando el contador sea menor al 0 el fantasma deja de ser comestible
            if (countEatable <= 0) {
                stateEatable = false;
                //Devolver a fantasma a su posición original
                setSprite(spriteOriginal);
            }
        }
    }

    //Gettes y Setters
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isStateEatable() {
        return stateEatable;
    }

    public int getCountEatable() {
        return countEatable;
    }

    public void setCountEatable(int countEatable) {
        this.countEatable = countEatable;
    }

    public BufferedImage getSpriteOriginal() {
        return spriteOriginal;
    }

    public void setSpriteOriginal(BufferedImage spriteOriginal) {
        this.spriteOriginal = spriteOriginal;
    }
}

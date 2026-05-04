package controller;

public class GameController implements Runnable {

    //FPS a los que va a ir el juego
    private static final int FPS = 60;

    //Los nanos por frame
    private static final long NANOS_PER_FRAME = 1_000_000_000_000L / FPS;

    //cuantos frames se muestran en ese tiempo
    private static final int FRAMES_ANIMATION = 8;

    //total puntos salen del total de 2 y 3 en la matriz del mapa
    private static final int TOTAL_POINTS = 185;


    @Override
    public void run() {

    }
}

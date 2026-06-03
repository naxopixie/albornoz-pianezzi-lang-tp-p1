package juego;
import java.awt.Color;
import entorno.Entorno;

public class Castillo {
    double x;
    double y;
    double ancho;
    double alto;

    public Castillo(double x, double y) {
        this.x = x;
        this.y = y;
        this.ancho = 100;
        this.alto = 100;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.YELLOW);
    }
}
package juego;
import java.awt.Color;
import entorno.Entorno;

public class Isla {
    double x;
    double y;
    double ancho;
    double alto;

    public Isla(double x, double y, double ancho, double alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
    }
    
}
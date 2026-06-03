package juego;
import java.awt.Color;
import entorno.Entorno;

public class Enemigo {
    double x;
    double y;
    double ancho;
    double alto;
    double velocidad;
    int danio;
    Color color;

    public Enemigo(double x, double y) {
        this.x = x;
        this.y = y;
        this.ancho = 30;
        this.alto = 30;
        this.danio = 1;      
        this.color = Color.RED; 
        if (this.x < 400) {
            this.velocidad = 2;
        } 
        else {
            this.velocidad = -2;       
       }
        if (Math.random() < 0.20) {
            this.danio = 2;                        
            this.velocidad = this.velocidad * 1.5;
            this.color = Color.BLUE;
        }

    }
    

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.color);
    }

    public void mover() {
        this.x = this.x + this.velocidad; 
    }
}
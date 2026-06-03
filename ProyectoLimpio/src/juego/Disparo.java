package juego;
import java.awt.Color;
import entorno.Entorno;

public class Disparo {
    double x;
    double y;
    double angulo;
    double velocidad;
    double radio;

    public Disparo(double x, double y, double angulo) {
        this.x = x;
        this.y = y;
        this.angulo = angulo;
        this.velocidad = 3;
        this.radio = 15;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarCirculo(this.x, this.y, this.radio * 2, Color.ORANGE);
    }

    public void mover() {
        this.x = this.x + Math.cos(this.angulo) * this.velocidad;
        this.y = this.y + Math.sin(this.angulo) * this.velocidad;
    
    }
    public boolean chocaCon(Enemigo enemigo) {
        boolean colisionX = this.x - this.radio < enemigo.x + enemigo.ancho / 2 && 
                            this.x + this.radio > enemigo.x - enemigo.ancho / 2;        
        boolean colisionY = this.y - this.radio < enemigo.y + enemigo.alto / 2 && 
                            this.y + this.radio > enemigo.y - enemigo.alto / 2;

        return colisionX && colisionY;
    }
}
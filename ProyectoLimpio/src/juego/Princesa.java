package juego;
import java.awt.Color;
import entorno.Entorno;

public class Princesa {
	
    double x;
    double y;
    double ancho;
    double alto;
    double velocidady;
    boolean estaenelAire;
    int vidas;

    public Princesa(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.ancho = 30;
        this.alto = 50;
        this.velocidady = 0;
        this.estaenelAire = false;
        this.vidas = 3;
    }

    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);
    }

    public void moverDerecha() {
        this.x = this.x + 3;
    }

    public void moverIzquierda() {
        this.x = this.x - 3;
    }    
    public void caer() {
        this.y = this.y + 5; }
    public boolean estaApoyadaSobre(Isla isla) {
        boolean coincideX = this.x + this.ancho / 2 > isla.x - isla.ancho / 2 && 
                            this.x - this.ancho / 2 < isla.x + isla.ancho / 2;
        
        boolean coincideY = this.y + this.alto / 2 >= isla.y - isla.alto / 2 && 
                            this.y + this.alto / 2 <= isla.y + isla.alto / 2;

        return coincideX && coincideY;
    }
    public void apoyarSobre(Isla isla) {
    	y = isla.y - isla.alto / 2 - alto / 2;
    	velocidady= 0;
    
    }
    
    public void salto() {
    		velocidady= -12;
    		estaenelAire= true;
    	} 	
    	
    public void actualizarsalto() {
    	y = y + velocidady;
    	velocidady = velocidady + 0.5;
    }   
    	
    
    public boolean chocaCabezaCon(Isla isla) {
        boolean coincideX = this.x + this.ancho / 2 > isla.x - isla.ancho / 2 && 
        					this.x - this.ancho / 2 < isla.x + isla.ancho / 2;      
        boolean coincideY = this.y - this.alto / 2 <= isla.y + isla.alto / 2 && 
        					this.y - this.alto / 2 >= isla.y - isla.alto / 2;
                       
        return coincideX && coincideY;
    }
    public boolean chocaCon(Enemigo enemigo) {
        boolean colisionX = this.x - this.ancho / 2 < enemigo.x + enemigo.ancho / 2 && 
                            this.x + this.ancho / 2 > enemigo.x - enemigo.ancho / 2;
        
        boolean colisionY = this.y - this.alto / 2 < enemigo.y + enemigo.alto / 2 && 
                            this.y + this.alto / 2 > enemigo.y - enemigo.alto / 2;
        return colisionX && colisionY;
    
    }
    public boolean chocaConCastillo(Castillo castillo) {
        boolean colisionX = this.x - this.ancho / 2 < castillo.x + castillo.ancho / 2 && 
                            this.x + this.ancho / 2 > castillo.x - castillo.ancho / 2;
        boolean colisionY = this.y - this.alto / 2 < castillo.y + castillo.alto / 2 && 
                            this.y + this.alto / 2 > castillo.y - castillo.alto / 2;
        return colisionX && colisionY;
    }
   }

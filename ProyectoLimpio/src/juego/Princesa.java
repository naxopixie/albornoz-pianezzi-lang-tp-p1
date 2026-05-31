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
    int cooldownSalto; //nuevo
    int cooldownMaximo; //nuevo

    //constructor
    public Princesa(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.ancho = 30;
        this.alto = 50;
        this.velocidady = 0;
        this.estaenelAire = false;
        this.cooldownSalto = 0; //nuevo
        this.cooldownMaximo = 3; //nuevo ticks espera
    }

    // metodo para dibujarse con el entorno
    public void dibujar(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.PINK);
    }

    // movilidad
    public void moverDerecha() {
        this.x = this.x + 3;
    }

    public void moverIzquierda() {
        this.x = this.x - 3;
    }    
    public void caer() {
        this.y = this.y + 5; } //caida
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
    	estaenelAire= false;
    	cooldownSalto = cooldownMaximo; //nuevo
    }
    
    public void salto() {
    	if(!estaenelAire && cooldownSalto == 0) {  // && cooldownSalto == 0)nuevo
    		velocidady= -14;
    		estaenelAire= true;
    	} 	
    	}
    public void actualizarsalto() {
    	y = y + velocidady;
    	velocidady = velocidady + 0.6;
    }
    
    public void actualizarCooldown() { //nuevo
    	if (cooldownSalto > 0) { //nuevo
    		cooldownSalto--; //nuevo "--" decrece ticks a partir de maximo 5; 4; 3; 
    	}
    }
    
   }
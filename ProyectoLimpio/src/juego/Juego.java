package juego;


import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	private Princesa elizabeth;
	
	private Isla[] islas;
	
	private Isla ultimaIslaSegura;
	
	private Enemigo[] enemigos;
	
	private Disparo ataque;
	
	private Castillo castillo;
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
		this.elizabeth = new Princesa(400, 400);
		this.enemigos = new Enemigo [7];
		this.islas = new Isla[15]; 
        int posX = 400;
        for (int i = 0; i < islas.length; i++) {
            if (i % 2 == 0) {
                this.islas[i] = new Isla(posX, 550, 200, 30);
                posX = posX + 280; 
                this.castillo = new Castillo(posX, 485);
            } else {
                int alturaAleatoria;
                if (Math.random() > 0.5) {
                    alturaAleatoria = 400; 
                } else {
                    alturaAleatoria = 250; 
                }
                this.islas[i] = new Isla(posX - 140, alturaAleatoria, 200, 20);}
            }
        Isla ultimaIsla = islas[islas.length - 1];
        double posYCastillo = ultimaIsla.y - ultimaIsla.alto / 2 - 50; 
        this.castillo = new Castillo(ultimaIsla.x, posYCastillo); }


	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick()
	// Procesamiento de un instante de tiempo
	{
			    if (elizabeth.vidas < 1) {
            entorno.cambiarFont("Arial", 60, Color.RED);
            entorno.escribirTexto("GAME OVER", 200, 300);
            return; 
      
	    }
	    if (castillo != null && elizabeth.chocaConCastillo(castillo)) {
            entorno.cambiarFont("Arial", 60, Color.GREEN);        
            entorno.escribirTexto("¡GANASTE!", 200, 300);           
            return; 
        }
	    entorno.cambiarFont("Arial", 24, Color.RED);
	    entorno.escribirTexto("Vidas: " + elizabeth.vidas, 50, 50);
        elizabeth.dibujar(entorno);
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null) {
                islas[i].dibujar(entorno);
            }
        }
      
        if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            if (elizabeth.x < 400) {
                elizabeth.moverDerecha();
            } else {
                for (int i = 0; i < islas.length; i++) {
                    if (islas[i] != null) {
                        islas[i].x = islas[i].x - 3; 
                    }
                }      
                for (int i = 0; i < enemigos.length; i++) {
                    if (enemigos[i] != null) {
                        enemigos[i].x = enemigos[i].x - 3;
                    }
                }   if (castillo != null) {
                    castillo.x = castillo.x - 3;
                }              
            }
        }
        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            if (elizabeth.x > 15) {
                elizabeth.moverIzquierda();
            }
        }
        double yAnterior = elizabeth.y;
        
  
        boolean estaApoyada = false;
        Isla islaDondeSeApoya = null;
        
        for (int i = 0; i < islas.length; i++) {
            if (islas[i] != null && elizabeth.estaApoyadaSobre(islas[i])) {
                estaApoyada = true;
                islaDondeSeApoya = islas[i];
            }
        }
        
        if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && estaApoyada) {
            elizabeth.salto();
        }
              
        elizabeth.actualizarsalto();
               
        boolean estaSubiendo = elizabeth.y < yAnterior;
              
        if (!estaApoyada && !estaSubiendo) {
            elizabeth.estaenelAire = true;
            elizabeth.caer();
        }
        if (estaSubiendo) {
            for (int i = 0; i < islas.length; i++) {             
                if (islas[i] != null && elizabeth.chocaCabezaCon(islas[i])) {                  
                    elizabeth.velocidady = 0;                 
                    elizabeth.y = islas[i].y + islas[i].alto / 2 + elizabeth.alto / 2;
                }
            }
        }       
        if (estaApoyada && !estaSubiendo) {
            elizabeth.apoyarSobre(islaDondeSeApoya);
            elizabeth.estaenelAire = false;                       
            ultimaIslaSegura = islaDondeSeApoya; 
        }
        
        if (elizabeth.y > entorno.alto()) {        
            elizabeth.x = ultimaIslaSegura.x;
            elizabeth.y = 100;                     
            elizabeth.vidas = elizabeth.vidas - 1;
        }
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] == null) {
                if (Math.random() < 0.01) {
                	double alturaAleatoria;

                	do {
                	    alturaAleatoria = Math.random() * 500 + 50;
                	} while (
                	    alturaAleatoria > 220 && alturaAleatoria < 280 ||
                	    alturaAleatoria > 370 && alturaAleatoria < 430 ||
                	    alturaAleatoria > 520 && alturaAleatoria < 580
                	);                                                        
                    double posX;
                    if (Math.random() > 0.5) {
                        posX = 800; 
                    } else {
                        posX = 0;
                    }
                    
                    enemigos[i] = new Enemigo(posX, alturaAleatoria); 
                }
            } else {
                enemigos[i].dibujar(entorno);
                enemigos[i].mover();
                
                if (elizabeth.chocaCon(enemigos[i])) {                   
                	elizabeth.vidas = elizabeth.vidas - enemigos[i].danio; 
                    enemigos[i] = null; 
                }              
                else if (enemigos[i].x < 0 || enemigos[i].x > 800) {
                    enemigos[i] = null; 
                }            
                if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && ataque == null) {                  
                    double angulo = Math.atan2(entorno.mouseY() - elizabeth.y, entorno.mouseX() - elizabeth.x);
                  
                   ataque = new Disparo(elizabeth.x, elizabeth.y, angulo);
                }
              
                if (ataque != null) {
                    ataque.dibujar(entorno);
                    ataque.mover();
                    if (ataque.x < 0 || ataque.x > entorno.ancho() || ataque.y < 0 || ataque.y > entorno.alto()) {
                    	ataque = null;
                   }
                    else {
                       for (int j = 0; j < enemigos.length; j++) {                        
                           if (enemigos[j] != null && ataque != null && ataque.chocaCon(enemigos[j])) {
                               enemigos[j] = null;
                               ataque = null;
                           }
                       }                 
                   }
                }
                if (castillo != null) {
                    castillo.dibujar(entorno);
                }
            }
        }
	}          

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

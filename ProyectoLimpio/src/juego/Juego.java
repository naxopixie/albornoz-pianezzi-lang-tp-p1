package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	private Princesa elizabeth;
	
	private Isla piso;
	private Isla piso2;
	private Isla piso3;
	private Isla piso4;
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
		this.piso = new Isla(400, 550, 200, 30);
		this.piso2 = new Isla(320, 450, 100, 30);
		this.piso3 = new Isla(240, 350, 100, 30);
		this.piso4 = new Isla(560, 450, 100, 30);
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick()
	{
		// Procesamiento de un instante de tiempo
        elizabeth.dibujar(entorno);
        piso.dibujar(entorno);
        piso2.dibujar(entorno);
        piso3.dibujar(entorno);
        piso4.dibujar(entorno);
      
        if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
            elizabeth.moverDerecha();
        }
        if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
            elizabeth.moverIzquierda();
        }
        boolean estaApoyada =
        		elizabeth.estaApoyadaSobre(piso) ||
        		elizabeth.estaApoyadaSobre(piso2) ||
        		elizabeth.estaApoyadaSobre(piso3) ||
        		elizabeth.estaApoyadaSobre(piso4);
        
        if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
        	elizabeth.salto();
        	
        elizabeth.actualizarsalto();
        if(estaApoyada) {
        	if(elizabeth.estaApoyadaSobre(piso)) {
        		elizabeth.apoyarSobre(piso);
        	}
        	if(elizabeth.estaApoyadaSobre(piso2)) {
        		elizabeth.apoyarSobre(piso2);
        	}
        	if(elizabeth.estaApoyadaSobre(piso3)) {
        		elizabeth.apoyarSobre(piso3);
        	}
        	if(elizabeth.estaApoyadaSobre(piso4)) {
        		elizabeth.apoyarSobre(piso4);
        	} else {
        		elizabeth.estaenelAire = false;
        	}
        }}
        
        
     // para que la princesa cuando no este apoyada sobre una isla caiga
        
        if (!estaApoyada) {
        	elizabeth.estaenelAire = true;
            elizabeth.caer(); //nuevo
        }
        
        elizabeth.actualizarCooldown(); // nuevo
        
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

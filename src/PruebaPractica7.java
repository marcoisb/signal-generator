/** 
 * Programa de prueba de la Practica 6 Fase 1 
 * 
 * @author PRL, DTE-UPM
 * @version 1.1
 */

import generadores.*;
import java.util.Scanner;
import componentes.*;

public class PruebaPractica7 {
static final int NUMEROMUESTRAS = 1000;
	public static void main(String[] args) {
		//constantes definidas para cumplir las restricciones pedidas:
		/*
			"Los siguientes valores serán fijos: la frecuencia de la señal será 2/1000.  
			la fase inicial de las señales sinusoidales será 0 y para el umbral en el caso 
			del generador con saturación se utilizará por defecto el valor 0.8.
			Para el cso del amplificador se usará una ganancia de 0.5"
		*/

		final double f = 2.0 / NUMEROMUESTRAS;
		final double faseInicialSinusoidal =0;
		final double umbralDefecto=0.8;
		final double gananciaDefecto=0.5;
		
		GeneradorSeñal generador = null;
		String tipoGenerador, tipoComponente;
		Componente componente = null;
		Componente componenteCircuito0 = null;
		Componente componenteCircuito1 = null;
		CircuitoSerie circuitoSerie = null;
		int numElementosCircuito = 2;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Teclee tipo (T o S o U):");
		tipoGenerador = sc.nextLine().toUpperCase();
		if (!tipoGenerador.equals("T") && !tipoGenerador.equals("S") && !tipoGenerador.equals("U")) {
			System.out.println("El tipo de generador especificado no es correcto.");
		} else {
			System.out.print("Teclee Componente (C,D,P,A,S):");
			tipoComponente = sc.nextLine().toUpperCase();
			if (!tipoComponente.equals("C") && !tipoComponente.equals("D") 
					                        && !tipoComponente.equals("P")&& !tipoComponente.equals("A")
					                        && !tipoComponente.equals("S")) {
				System.out.println("El tipo de componente especificado no es correcto.");
			} else {
				switch (tipoGenerador) {
				case "T": generador= new GeneradorSeñalTriangular("triangular",f);
			          break;
				case "S": generador= new GeneradorSeñalSinusoidal("sinusoidal",f,faseInicialSinusoidal); 
			          break;
				case "U": generador= new GeneradorSeñalSinusoidalConSaturación(
			            "sinusoidal con saturación",
			            f,
						faseInicialSinusoidal,
						umbralDefecto);
				      break;
				}
				switch (tipoComponente) {
				  case "C":
					componente = new Condensador("Condensador 1");
					break;
				  case "D":
					componente = new Diodo("Diodo 1");
					break;
				  case "P":
					componente = new PuenteDiodos("Puente 1");
					break;
				  case "A":
					componente = new Amplificador("Amplificador",gananciaDefecto);
					break;
				  case "S":
					  
					  circuitoSerie = new CircuitoSerie(numElementosCircuito);
			 
					  componenteCircuito0 = new PuenteDiodos(" PuenteDiodos Circuito");
					  circuitoSerie.addComponente(componenteCircuito0);					 
					  componenteCircuito1 = new Condensador("Condensador Circuito");
					  circuitoSerie.addComponente(componenteCircuito1);
					  
					  System.out.println("El circuito serie tiene los siguientes componentes: ");
					  System.out.println("	PuenteDiodos final");
					  System.out.println("	Condensador Circuito final");
					  
					  break;
				}
				
				try{
					
					if(tipoComponente.equals("S")){
				
						mostrarSonda(generador, circuitoSerie, NUMEROMUESTRAS);
					
					}else{
				
						mostrarSonda(generador, componente, NUMEROMUESTRAS);
					
					}
					
				}catch( MalFuncionamientoException e){
					
					if(tipoComponente.equals("S")){
						
						System.out.println("El componente: "+ circuitoSerie.toString() + " ha reportado: " + e.getMessage());
						
					
					}else{
				
						System.out.println("El componente: "+ componente.getNombre() + " ha reportado: " + e.getMessage());
					
					}					
				}
			}
		}
		sc.close();
	}

	/**
	 * Muestra en la sonda gráfica el resultado de aplicar la señal del
	 * generador sobre el componente, con el número de muestras indicado. En el titulo
	 * de la ventana gráfica se añadirá el nombre del componente si el parámetro 
	 * componente es de clase Componente ó el texto fijo "ITransferencia" en
	 * cualquier otro caso.
	 * 
	 * @param generador
	 *            es el generador de señal a utilizar
	 * @param componente
	 *            es el componente al que aplicar la señal del generador
	 * @param muestras
	 *            es el número de muestras a mostrar en la ventana gráfica
	 */
	private static void mostrarSonda(GeneradorSeñal generador, ITransferencia componente, int muestras) throws MalFuncionamientoException{
		
		
		String nombreGenerador = null;
		String nombreComponente = null;
		
		
		if(generador instanceof GeneradorSeñalTriangular){
			
			nombreGenerador = "GeneradorSeñalTriangular";
			
		}else if(generador instanceof GeneradorSeñalSinusoidal){
			
			nombreGenerador = "GeneradorSeñalSinusoidal";
			
		}else if(generador instanceof GeneradorSeñalSinusoidalConSaturación){
			
			nombreGenerador = "GeneradorSeñalSinusoidalConSaturación";
			
		}
		
		
		if(componente instanceof Condensador){
			
			nombreComponente = "Condensador";		
			
		}else if(componente instanceof Diodo){
			
			nombreComponente = "Diodo";		
			
		}else if(componente instanceof Amplificador){
			
			nombreComponente = "Amplificador";		
			
		}else if(componente instanceof PuenteDiodos){
			
			nombreComponente = "PuenteDiodos";
			
		}else if(componente instanceof CircuitoSerie){
			
			nombreComponente = "CircuitoSerie";
		}
			
		SondaGráfica Pantalla = new SondaGráfica(nombreGenerador + " " + nombreComponente);
		
		
		
		for(int i = 0; i < muestras - 1; i++){
			
			Pantalla.addMuestra(componente.getSalida(generador.getSalida()));
			
		}
	}

}
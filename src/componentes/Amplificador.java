package componentes;

/**
 * Componente que simula la operaci�n de un amplificador/atenuador ideal de una ganancia dada
 * 
 * @author Marco Israel Simba�a Bueno
 * @version 1.0
 *
 */

public class Amplificador extends Componente{

	private double ganancia;
	
	/**
	 * Construye un amplificador
	 * 
	 * @param nombre  - asignado al condensador
	 * @param ganancia - ganancia del amplificador ideal. Si ganancia menor que 1.0 funciona como atenuador. Si ganancia negativa funciona adem�s como inversor.
	 */
	public Amplificador(String nombre, double ganancia){
		
		super(nombre);
		this.ganancia = ganancia;
	
	}
	
	/**
	 * Devuelve la salida que se obtiene tras aplicar esta entrada a un amplificador
	 * 
	 * @param entrada - siguiente valor de se�al de entrada
	 * @return devuelve el resultado del proceso de la se�al de entrada por el componente concreto
	 */
	public double getSalida(double entrada){
		
		double salida = 0.0;
		
		salida = entrada*ganancia;
	
		return salida;	
		
	}
	
	/**
	 * Devuelve la ganancia del amplificador/atenuador
	 * 
	 * @return ganancia actual
	 */
	
	public double getGanancia(){
		
		return ganancia;
		
	}
	
	/**
	 * Establece una nueva ganancia del amplificador/atenuador/inversor.
	 * 
	 * @param ganancia - la ganancia a establecer. Si ganancia menor que 1.0 funciona como atenuador
	 */
	
	public void setGanancia(double ganancia){
		
		this.ganancia = ganancia;
			
	}
	
}
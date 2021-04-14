package componentes;
/**
* Componente que simula la operaci�n de un puente de diodos ideal
*
* @author Marco Israel Simba�a Bueno
* @version 1.0
*/
public class PuenteDiodos extends Componente {

	/**
	 * Construye un puente de diodos
	 * 
	 * @param nombre - y/o identificador del puente de diodos
	 */
	
	public PuenteDiodos(String nombre){
		
		super(nombre);
	
	}
	/**
	 * Devuelve la salida que se obtiene tras aplicar la entrada a un puente de diodos ideal
	 * 
	 * @param entrada - siguiente valor de se�al de entrada
	 *  
	 */
	public double getSalida(double entrada){
	
		double salida = 0.0;
		
		salida = Math.abs(entrada);
		
		return salida;
	
	}

	
	
}

package componentes;
/**
* Componente que simula la operación de un diodo ideal
*
* @author Marco Israel Simbaña Bueno
* @version 1.0
*/
public class Diodo extends Componente{
	/**
	 * Construye un diodo
	 * 
	 * @param nombre - y/o identificador del componente
	 */
	public Diodo (String nombre){
	
		super(nombre);
	
	}
	/**
	 * Devuelve la salida que se obtiene tras aplicar esta señal de entrada a un diodo ideal
	 * 
	 *@param entrada - siguiente valor de señal de entrada
	 * @return devuelve el resultado del proceso de la señal de entrada por el componente concreto
	 * 
	 */
	
	public double getSalida(double entrada){
		
		double salida = 0.0;
		double cero = 0.0;
		int aux0 = 0;
		
		aux0 = Double.compare(entrada, cero);
		
		if(aux0 >= 0){
			
			salida = entrada;
			
		}
		
		return salida;

	}
}
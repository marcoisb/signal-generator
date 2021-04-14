package componentes;
/**
 * Interfaz a cumplir por cualquier objeto que tenga capacidad de, a partir de una señal de entrada y/o de su propio estado interno, obtener una señal de salida (en base a su función de transferencia).
 * 
 *@author Marco Israel Simbaña Bueno
 *@version 1.0
 */
public interface ITransferencia {

	/**
	 * Devuelve el siguiente valor (o muestra) de la señal de salida, en base al valor de señal de entrada y/o el estado interno del objeto.
	 * 
	 * @param entrada - siguiente valor de señal de entrada
	 * @return salida siguiente valor de la señal de salida producida
	 */
	public double getSalida(double entrada);
	
}

package componentes;
/**
 * Interfaz a cumplir por cualquier objeto que tenga capacidad de, a partir de una se�al de entrada y/o de su propio estado interno, obtener una se�al de salida (en base a su funci�n de transferencia).
 * 
 *@author Marco Israel Simba�a Bueno
 *@version 1.0
 */
public interface ITransferencia {

	/**
	 * Devuelve el siguiente valor (o muestra) de la se�al de salida, en base al valor de se�al de entrada y/o el estado interno del objeto.
	 * 
	 * @param entrada - siguiente valor de se�al de entrada
	 * @return salida siguiente valor de la se�al de salida producida
	 */
	public double getSalida(double entrada);
	
}

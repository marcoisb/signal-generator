

import componentes.Componente;


/**
 * Interfaz a cumplir por cualquier objeto que quiera tener funcionalidad de colecci�n de componentes.
 * @author Marco Israel Simba�a Bueno
 * @version 1.0
 */
public interface IComponentes {
	
	/**
	 * A�ade a la colecci�n de componentes el componente que se pasa como par�metro
	 * 
	 * @param c - componente a a�adir a la colecci�n
	 * @return - true si ha podido a�adir el componente a la colecci�n y false en caso contrario
	 * 
	 */
	public boolean addComponente(Componente c);
	
	/**
	 * Devuelve el componente cuyo nombre se pasa como par�metro
	 *  
	 * @param nombre - Nombre del componente
	 * @return - El componente buscado o null si no se ha podido encontrar
	 *
	 */
	
	public Componente getComponente(String nombre);
	
	
	/**
	 * Borra el componente cuyo nombre se pasa como par�metro
	 * 
	 * @param nombre - Nombre del componente
	 * @return - El componente borrado o null si no se ha podido borrar
	 * 
	 */
	public Componente delComponente(String nombre);
	
	/**
	 * Devuelve un array con los nombres de todos los componentes de la colecci�n
	 * 
	 * @return - el array con los nombres de los componentes 
	 * 
	 */
	public String[] getNombresComponentes();
	
	/**
	 * Devuelve un array con todos los componentes de la colecci�n
	 * 
	 * @return - el array con los componentes
	 */
	
	public Componente[] getComponentes();

	/**
	 * Devuelve el tama�o de la colecci�n
	 * 
	 * @return - el tama�o
	 */
	
	public int size();

}



import componentes.Componente;


/**
 * Interfaz a cumplir por cualquier objeto que quiera tener funcionalidad de colección de componentes.
 * @author Marco Israel Simbaña Bueno
 * @version 1.0
 */
public interface IComponentes {
	
	/**
	 * Añade a la colección de componentes el componente que se pasa como parámetro
	 * 
	 * @param c - componente a añadir a la colección
	 * @return - true si ha podido añadir el componente a la colección y false en caso contrario
	 * 
	 */
	public boolean addComponente(Componente c);
	
	/**
	 * Devuelve el componente cuyo nombre se pasa como parámetro
	 *  
	 * @param nombre - Nombre del componente
	 * @return - El componente buscado o null si no se ha podido encontrar
	 *
	 */
	
	public Componente getComponente(String nombre);
	
	
	/**
	 * Borra el componente cuyo nombre se pasa como parámetro
	 * 
	 * @param nombre - Nombre del componente
	 * @return - El componente borrado o null si no se ha podido borrar
	 * 
	 */
	public Componente delComponente(String nombre);
	
	/**
	 * Devuelve un array con los nombres de todos los componentes de la colección
	 * 
	 * @return - el array con los nombres de los componentes 
	 * 
	 */
	public String[] getNombresComponentes();
	
	/**
	 * Devuelve un array con todos los componentes de la colección
	 * 
	 * @return - el array con los componentes
	 */
	
	public Componente[] getComponentes();

	/**
	 * Devuelve el tamaño de la colección
	 * 
	 * @return - el tamaño
	 */
	
	public int size();

}

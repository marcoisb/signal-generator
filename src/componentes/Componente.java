package componentes;

/**
 * 
 * Clase abstracta que define el comportamiento com�n de componentes electr�nicos
 * 
 * @author Marco Israel Simba�a Bueno
 * @version 1.0
 * 
 */
public abstract class Componente implements ITransferencia{
	
	private String nombre;
	
	/**
	 * Constructor
	 * 
	 * @param nombre - nombre y/o identificador del componente
	 */
	
	public Componente(String nombre){
		
		this.nombre = nombre;
		
	}
	/**
	 * Obtener el nombre del componente
	 * 
	 * @return devuelve el nombre y/o identificador del componente
	 */
	
	public String getNombre(){
		
		return nombre;
		
	}
	/**
	 * @return una cadena con el formato: "Componente [nombre=x]" donde x ser� el nombre del componente.
	 */
	
	public String toString(){

		return "Componente " + this.nombre;
		
	}
	
	/**
	 * M�todo abstracto a implementar por cada tipo de componente
	 * 
	 * @param entrada - siguiente valor de se�al de entrada
	 * @return devuelve el resultado del proceso de la se�al de entrada por el componente concreto
	 */
	public abstract double getSalida(double entrada);
	
	/**
	 * Compara dos componentes
	 * @param c
	 * @return devuelve una variable booleana true si los dos componentes son iguales y si no es asi devuelve false
	 * 		   
	 */
	public boolean equals(Componente c){
		
		boolean exito = false;
		
		if(nombre == c.getNombre()){
			
			exito = true;
			
		}
		
		return exito;
		
	}
	
}

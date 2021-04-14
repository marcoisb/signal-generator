package componentes;

/**
 * Componente que simula la operación de un circuito serie
 * 
 * @author Marco Israel Simbaña Bueno
 * @version 1.0
 * 
 */
public class CircuitoSerie implements ITransferencia {
	
	private int maxComponentes;
	private Componente[] listaComponentes;
	private int numComponentes;
	
	/**
	 * Crea un circuito serie
	 * 
	 * @param maxComponentes - tamaño máximo de componentes que admite el circuito. Debe ser mayor que 0.
	 */
	
	public CircuitoSerie(int maxComponentes){
		
		this.maxComponentes = maxComponentes; //tiene que ser mayor que cero
		this.numComponentes = 0;		
		listaComponentes = new Componente[maxComponentes];
		for (int i = 0; i < maxComponentes; i++) {
			
			listaComponentes[i] = null;
			
		}
		
	}
	
	/**
	 * Agrega al circuito un nuevo componente en serie tras los existentes si el circuito no tiene hueco no lo añade.
	 * 
	 * @param componente  - el objeto de clase Componente a agregar al circuito.
	 */
	
	public void addComponente(Componente componente){		
		
		boolean exito = false;
		
		if(numComponentes == maxComponentes){
			
			throw new IllegalArgumentException("Se ha alcanzado el maximo de componentes");
			
		}
		
		for (int i = 0; i < maxComponentes;  i++) {
			
			if(listaComponentes[i] == null && exito == false){
				exito = true;
				numComponentes = numComponentes + 1;
				listaComponentes[i] = componente;
			}
		
		}
		
	}
	/**
	 * Devuelve el número de componentes que tiene un circuito
	 * 
	 * @return número de componentes en el circuito.
	 */
	
	public int size(){
		
		return numComponentes;
		
	}
	/**
	 * Indica si un circuito ha llegado o no al límite de su capacidad
	 * 
	 * @return Cierto si lleno, falso si existe al menos un hueco.
	 */
	
	public boolean isLleno(){
		
		boolean islleno = false;
		
		if(numComponentes == maxComponentes){
			
			islleno = true;
			
		}
		
		return islleno;
		
	}
	
	/**
	 * Devuelve la salida que se obtiene tras aplicar la señal de entrada a este circuito serie El circuito debe contener al menos un componente.
	 * 
	 * @param entrada - siguiente valor de señal de entrada
	 * @return salida siguiente valor de la señal de salida producida
	 *
	 */
	public double getSalida(double entrada){
	
		double salida = 0.0;
		double aux0 = entrada;
		double aux1 = 0.0;
		//Hay que distinguir si el componente que usamos es un condensador ya que el 
		//condensador solo puede tener entradas positivas en caso contrario las entradas sin cero
		
		for (int i = 0; i < maxComponentes; i++) {
			
			if(listaComponentes[i] instanceof Condensador){
				if(aux0 >= 0){
					
					aux1 = listaComponentes[i].getSalida(Math.abs(aux0));
				
				}else{
				
					aux1 = listaComponentes[i].getSalida(Math.abs(0));
				}
				aux0 = aux1;
			
			}else{
					
				aux1 = listaComponentes[i].getSalida((aux0));
				aux0 = aux1;
			
			}
	
		}
		
		salida =  aux1;
		
		return salida;
		
	}
	
	/**
	 * devuelve una cadena con la información del circuito y los componentes que lo componen (formato libre)
	 *
	 */
	public String toString(){
		
		String salida = " ";
		String aux0 = " ";
		String aux1 = " ";
		
		for (int i = 0; i < maxComponentes; i++) {
			
			if(i == 0){
				
				aux0 = listaComponentes[i].getNombre();
			
			}else{
				
				aux1 = listaComponentes[i].getNombre() + aux0;
				aux0 = aux1;
				
			}
		}
		
		salida = aux0;
		
		return salida;
		
	}
	
	
}

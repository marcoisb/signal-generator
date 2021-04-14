package componentes;
/**
 * Componente que simula un condensador con p�rdidas (con 0,1% de p�rdida por cada muestra de se�al entrada inferior a su estado actual)
 *
 * @author Marco Israel Simba�a Bueno
 * @version 1.0
 */
public class Condensador extends Componente {
	
	private double salidaAnterior;
	
	/**
	 * Construye un condensador. Un condensador se crea descargado (salida = 0.0) Su coeficiente de p�rdidas es fijo = 0,1 % para cualquier condensador (porcentaje de p�rdida de se�al por cada t que transcurre sin que la se�al de entrada tenga capacidad de cargar el condensador
	 * 
	 * @param nombre - asignado al condensador
	 */
	
	public Condensador(String nombre){
		
		super(nombre);
		salidaAnterior = 0.0;
		
	}
	
	/**
	 * Devuelve la se�al de salida que se obtiene tras aplicar la entrada a un condensador. El condensador tiene p�rdidas, con coeficiente de p�rdidas fijo = 0,1%. (porcentaje de p�rdida en su estado (se�al de salida) cada muestra (t) que la se�al de entrada no tiene capacidad para cargar el condensador.
	 * 
	 * @param entrada - Se�al de entrada. Debe ser un valor positivo (mayor que 0)
	 * @return devuelve el resultado del proceso de la se�al de entrada por el componente concreto
	 *
	 */

	public double getSalida(double entrada){
		
		double salida = 0.0;
		int aux0 = 0;
		
		double perdidas = 0.999;
		
		aux0 = Double.compare(entrada, perdidas*salidaAnterior);
		
		if(aux0 >= 0){
			
			salida = entrada;
			
		}else{
			
			salida =  perdidas*salidaAnterior;
		
		}
		
		salidaAnterior = salida; 
		
		if((Double.compare(salida,0.0)) < 0){
			
			throw new IllegalArgumentException("salida menor que cero");
			
		}
		
		return salida;
		
	}
}

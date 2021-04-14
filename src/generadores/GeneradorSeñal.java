package generadores;
/**
 * 
 * @author Marco Israel Simbaña Bueno
 *
 */
public class GeneradorSeñal {

	private String nombre;
	protected double F;
	private double salida = 0.0;
	static final double pi = 3.14;
/**
 * 
 * @param nombre
 * @param f
 */
	public GeneradorSeñal(String nombre, double f){
	
		this.nombre =  nombre;
		
		if( Double.compare(f, 0.0) <= 0){
			
			throw new IllegalArgumentException("frecuencia menor igual que cero");
			
		}
	
		this.F = f;
		salida = 0.0;
		
	}
/**
 * 
 * 	
 * @return
 */
	public String getNombre(){
		
		return nombre;
			
	}

	public double getSalida(){
		
		return salida;
		
	}
	
	public double getFrecuencia(){
		
		return F;
	
	}
}

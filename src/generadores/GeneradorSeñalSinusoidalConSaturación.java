package generadores;
/**
 * Modela un generador de señal sinusoidal pero con un valor máximo de saturación
 * @author Marco Israel Simbaña Bueno
 * @version 1.0
 * 
 */
public class GeneradorSeñalSinusoidalConSaturación extends GeneradorSeñalSinusoidal{
	
	private double saturación = 0.0;
	
	/**
	 * Constructor
	 * @param nombre - nombre para este generador
	 * @param f - frecuencia de la señal generada
	 * @param fase0 - fase inicial de la señal generada
	 * @param saturación - nivel de saturación
	 */
	public GeneradorSeñalSinusoidalConSaturación (String nombre, double f, double fase0, double saturación){
		
		super(nombre,f,fase0);

		if((Double.compare(saturación, 0.0)) < 0 && (Double.compare(saturación, 1.0)) > 0 ){
			
			throw new IllegalArgumentException("Fase negativa");
		}
		this.saturación = saturación;

	}
	
	/**
	 * Proporciona el siguiente valor de la señal de salida. La primera vez que se invoca a este método devuelve el valor de la señal de salida en el instante inicial t=0
	 * @return siguiente valor de la señal de salida sinusoidal acotada
	 * 
	 */
	public double getSalida(){
		
		double salida = 0.0;
		double valorAux = super.getSalida(); //Valor auxiliar que se usa para no tener que llamar varias vecez a getSalida
		
		int aux0 = Double.compare(valorAux, saturación);
		int aux1 = Double.compare(Math.abs(valorAux),saturación);
		int aux2 = Double.compare(valorAux, -saturación);
		
		if(aux0 > 0){
			
			salida = saturación;
			
		}else if(aux1 == 0 || aux1 < 0){
			
			salida = valorAux;
			
		}else if(aux2 < 0){
			
			salida = -saturación;	
		
		}
			
		return salida;
		
	}
	
}

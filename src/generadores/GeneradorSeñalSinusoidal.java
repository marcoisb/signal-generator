package generadores;

/**
 * Generador de señal sinusoidal. Produce una sinusoide periódica con período T=360 (360 muestras), y frecuencia definidas en el constructor
 * 
 * @author Marco Israel Simbaña Bueno
 * @version 1.0
 * 
 *
 */
public class GeneradorSeñalSinusoidal extends GeneradorSeñal {
	
	private double tMuestra;
	private double periodo;
	private double fase;

	/**
	 * Constructor
	 * @param nombre - nombre para este generador
	 * @param f  - frecuencia de la señal generada
	 * @param fase0 - fase inicial de la señal generada
	 */
	public GeneradorSeñalSinusoidal (String nombre, double f, double fase0){
		
		super(nombre, f);
		this.tMuestra = 0.0;
		this.periodo = 1/f;
		
		if((Double.compare(fase0, 0.0)) < 0){
			
			throw new IllegalArgumentException("Fase negativa");
		}
		
		this.fase = fase0;

	}
	/**
	 * Proporciona el siguiente valor de la señal de salida. La primera vez que se invoca a este método devuelve el valor de la señal de salida en el instante inicial t=0;
	 * @return siguiente valor de la señal de salida sinusoidal
	 * 
	 */
	
	public double getSalida(){
		
		if (tMuestra == periodo){
			
			tMuestra = 0.0;
		
		}else{
			
			tMuestra = tMuestra + 1.0; 
				
		}

		return ( Math.sin(2*pi*getFrecuencia()*(tMuestra - 1)) + fase);
		
	}
	
}

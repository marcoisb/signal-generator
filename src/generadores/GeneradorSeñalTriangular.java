package generadores;
/**
 * Generador de señal triangular. Produce una señal triangular periodica con periodo T = 360 muestras
 * @author Marco Israel Simbaña Bueno
 * @version 1.0
 *
 */
public class GeneradorSeñalTriangular extends GeneradorSeñal{
	 private double tMuestra = 0.0;
	
	 /**
	  * Constructor
	  * @param nombre - nombre para este generador
	  * @param f - frecuencia de la señal generada
	  */
	
	public GeneradorSeñalTriangular(String nombre , double f){
		
		super(nombre,f);
	
	}
	
	/**
	 * Proporciona el siguiente valor de la señal de salida. La primera vez que se invoca a este método devuelve el valor de la señal de salida en el instante inicial t=0;
	 * @return siguiente valor de la señal de salida sinusoidal
	 * 
	 */
	public double getSalida(){
		
		double periodo = (1/F);
		double cero = 0.0;
		double salida =  cero;
		int aux0;
		int aux1;
		int aux2;
		int aux3;
		
			
		aux0 = Double.compare(tMuestra, cero);
		aux1 = Double.compare(tMuestra,periodo/4);
		aux2 = Double.compare(tMuestra,((3*periodo)/4));
		aux3 = Double.compare(tMuestra, periodo);
		
		if (tMuestra == periodo){
			
			tMuestra = 0.0;
		
		}else{
			
			tMuestra = tMuestra + 1.0; 

		}
		
		if((aux0 > 0 || aux0 == 0) && aux1 < 0){
	
			salida = 4.0*tMuestra/periodo;
			
		}else if((aux1 > 0 || aux1 == 0) && aux2 < 0 ){
			
			salida = (2.0 - ((4.0*tMuestra)/periodo));
			
		}else if((aux2 > 0 || aux2 == 0) && aux3 < 0){
			
			salida = (-(4.0) + (4.0*(tMuestra/periodo)));
			
		}
		
		return salida;
			
	}
}
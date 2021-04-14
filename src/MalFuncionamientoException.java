
/**
 *Generar una excepcin
 *	 
 * @author Marco Israel Simba�a Bueno
 * @version 1.0
 */
public class MalFuncionamientoException extends Exception {
	


	private static final long serialVersionUID = -5501652248410827237L;

	public MalFuncionamientoException(){}
	
	/**
	 * Construtor
	 * 
	 * @param mensajeError
	 */
	public MalFuncionamientoException(String mensajeError){
		
		super(mensajeError);
		
		
	}
	
	

}
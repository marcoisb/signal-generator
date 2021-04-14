import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import componentes.*;

public class ComponentesList implements IComponentes {

	private List<Componente> listaComponentes;
	
	/**
	 * Constructor de la clase ComponentesList
	 * 
	 */
	
	public ComponentesList(){
		
		this.listaComponentes = new ArrayList<Componente>();
		
	}
	
	/**
	 * Añade a la colección de componentes el componente que se pasa como parmetro
	 * 
	 * @param c - componente a añadir a la colección
	 * @return - true si ha podido añadir el componente a la colección y false en caso contrario
	 * 
	 */
	
	public boolean addComponente(Componente c){
		
		boolean añadido = false;
		boolean repetido = false;
		Iterator<Componente> iterador;
		
		if(listaComponentes.size() != 0){
			
			iterador = listaComponentes.iterator();
			
			while (iterador.hasNext() && !repetido){
				
				if(c.equals(iterador.next())){
					
					repetido = true;
					
				}		
			}
			
			if(!repetido){
				
				añadido = listaComponentes.add(c);
			}
			
		}else{
			
			añadido = listaComponentes.add(c);
		}
		
		return añadido;
		
	}
	
	/**
	 * Devuelve el componente cuyo nombre se pasa como parmetro
	 *  
	 * @param nombre - Nombre del componente
	 * @return - El componente buscado o null si no se ha podido encontrar
	 *
	 */
	
	public Componente getComponente(String nombre){
		
		Componente componenteBuscado =  null;
		boolean encontrado = false;
		Iterator<Componente> iterador;
		
		if(!(listaComponentes.isEmpty())){
				
			iterador = listaComponentes.iterator();
		
			while (iterador.hasNext() && !encontrado){
				
				componenteBuscado = iterador.next();
					
				if((componenteBuscado.getNombre()) == nombre){
			
					encontrado = true;
				
		
				}else{
					
					componenteBuscado = null;
				}
			}
		}
		
		return componenteBuscado;
		
	}
	
	/**
	 * Borra el componente cuyo nombre se pasa como parmetro
	 * 
	 * @param nombre - Nombre del componente
	 * @return - El componente borrado o null si no se ha podido borrar
	 * 
	 */
	
	public Componente delComponente(String nombre){
		
		Componente componenteBorrar =  null;
		boolean encontrado = false;
		Iterator<Componente> iterador;
		
		if(!(listaComponentes.isEmpty())){
			
			iterador = listaComponentes.iterator();
			
			while (iterador.hasNext() && !encontrado){
		  
				componenteBorrar = iterador.next();	
				
				if((componenteBorrar.getNombre()) == nombre){
			
					encontrado = true;
					listaComponentes.remove(componenteBorrar);
					
				}else{
					
					componenteBorrar = null;
				}
			}
		}
	
		return componenteBorrar;
		
	}
	
	/**
	 * Devuelve un array con los nombres de todos los componentes de la colección
	 * 
	 * @return - el array con los nombres de los componentes 
	 * 
	 */
	
	public String[] getNombresComponentes(){
		
		String[] nombreComponentes;
		Iterator<Componente> iterador;
		int i = 0;
		nombreComponentes = new String[listaComponentes.size()];
		iterador = listaComponentes.iterator();
							
		while(iterador.hasNext() && i < listaComponentes.size()){
		
			nombreComponentes[i] = iterador.next().getNombre();
			i++;
		}
		
		return 	nombreComponentes;
	
	}
	
	/**
	 * Devuelve un array con todos los componentes de la colección
	 * 
	 * @return - el array con los componentes
	 */
	
	public Componente[] getComponentes(){
		
		Componente[] conjuntoComponentes;
		conjuntoComponentes = new Componente[listaComponentes.size()];
		Iterator<Componente> iterador;
		int i = 0;
		iterador = listaComponentes.iterator();
		
		while(iterador.hasNext() && i < listaComponentes.size()){

			conjuntoComponentes[i] = iterador.next();
			i++;
		
		}	
		
		return conjuntoComponentes;
		
		
	}
	
	/**
	 * Devuelve el tamaño de la colección
	 * 
	 * @return - el tamaño
	 */
	
	public int size(){
		
		
		return listaComponentes.size();
		
	}
}



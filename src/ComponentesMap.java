import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import componentes.*;

public class ComponentesMap implements IComponentes {
	

	private Map<String,Componente> mapComponentes;
	
	/**
	 * Constructor de la clase ComponentesList
	 * 
	 */
	
	public ComponentesMap(){
		
		this.mapComponentes = new HashMap<String, Componente>();
		
	}
	
	/**
	 * Añade a la colección de componentes el componente que se pasa como parámetro
	 * 
	 * @param c - componente a añadir a la colección
	 * @return - true si ha podido añadir el componente a la colección y false en caso contrario
	 * 
	 */
	
	public boolean addComponente(Componente c){
		
		boolean añadido = false;
		boolean repetido = false;
		String[] nombresActuales = new String[mapComponentes.size()];
		Set <String> nombreMap = mapComponentes.keySet();
		int i = 0; 
		int j = 0;
		
		if(mapComponentes.size() != 0){
			
			for(Iterator<String> iterador = nombreMap.iterator(); iterador.hasNext();){
			
				nombresActuales[i] = iterador.next();
				i++;
			}
			
			while(j < mapComponentes.size() && !repetido) {
			
				if(nombresActuales[j].equals(c.getNombre())){
				
					repetido = true;
					
				}
				j++;
			}
			
			if(!repetido){
				
				mapComponentes.put(c.getNombre(), c);
				añadido = true;
			}
		
		}else{
			
			mapComponentes.put(c.getNombre(), c);
			añadido = true;
		}
		
		return añadido;
		
	}
	
	/**
	 * Devuelve el componente cuyo nombre se pasa como parámetro
	 *  
	 * @param nombre - Nombre del componente
	 * @return - El componente buscado o null si no se ha podido encontrar
	 *
	 */
	
	public Componente getComponente(String nombre){
		
		Componente componenteBuscado =  null;
		componenteBuscado = mapComponentes.get(nombre);
		
		return componenteBuscado;
		
	}
	
	/**
	 * Borra el componente cuyo nombre se pasa como parámetro
	 * 
	 * @param nombre - Nombre del componente
	 * @return - El componente borrado o null si no se ha podido borrar
	 * 
	 */
	
	public Componente delComponente(String nombre){
		
		Componente componenteBorrar =  null;
		
		componenteBorrar = mapComponentes.remove(nombre);
	
		return componenteBorrar;
		
	}
	
	/**
	 * Devuelve un array con los nombres de todos los componentes de la colección
	 * 
	 * @return - el array con los nombres de los componentes 
	 * 
	 */
	
	public String[] getNombresComponentes(){
		
		String[] nombreComponentes = new String[mapComponentes.size()];
		int i= 0;
		Set <String> llaves = mapComponentes.keySet();
		
		for(Iterator<String> iterador = llaves.iterator(); iterador.hasNext();){
			
			nombreComponentes[i] = iterador.next();
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
		conjuntoComponentes = new Componente[mapComponentes.size()];
		Iterator<Componente> iterador;
		int i = 0;
		iterador = mapComponentes.values().iterator();  //Asigno al iterador una colleccion con los valores del mapa
		
		while(iterador.hasNext()){

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
		
		
		return mapComponentes.size();
		
	}
}


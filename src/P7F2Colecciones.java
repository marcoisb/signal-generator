import java.util.InputMismatchException;
import java.util.Scanner;

import componentes.*;
import generadores.*;

public class P7F2Colecciones {

	static final int NUMEROMUESTRAS = 1000;

	public static void main(String[] args) {

		final double f = 2.0 / NUMEROMUESTRAS;
		final double faseInicialSinusoidal = 0;
		final double umbralDefecto = 0.8;
		final double gananciaDefecto = 0.5;
		int componentesAñadidos = 0;
		int posicionComponente = 0;
		int opcion = 0;
		int numComponentes = 0;
		boolean circuitoLleno = false;
		boolean opcionSeleccionada = false;
		boolean componentesSelec =  false;
		String[] arrayNombresComponentes;
		Componente[] arrayComponentes;
		
		boolean condicionNumComponente = false;
		boolean condicionGenerador = false;
		GeneradorSeñal generador = null;
		String tipoGenerador;
		CircuitoSerie circuito;

		
		//Inicializa componentes
		
		Componente condensador1 = new Condensador("condensador1");
		Componente condensador2 = new Condensador("condensador2");
		Componente diodo1 = new Diodo("diodo1");
		Componente diodo2 = new Diodo("diodo1");
		Componente puenteDiodos1 = new PuenteDiodos("puenteDiodos1");
		Componente puenteDiodos2 = new PuenteDiodos("puenteDiodos2");
		Componente amplificador1 = new Amplificador("amplificador1", gananciaDefecto);
		Componente amplificador2 = new Amplificador("amplificador2", gananciaDefecto);

		//Inicio 
		
		System.out.println("Practica 7");
		Scanner sc = new Scanner(System.in);
		int implementacion = 0;
		boolean elegidaImplementacion = false;
		
		do {
			try {
				System.out.println("Implementaciones:");
				System.out.println();
				System.out.println("	1 Modo list");
				System.out.println("	2 Modo map");
				System.out.println();
				
				System.out.print("Elija implementación: ");
				implementacion = sc.nextInt();	
							
				if (!(implementacion == 1) && !(implementacion == 2)) {

					System.out.println("Opcion elejida es incorrecta");
											
				} else {
					
					elegidaImplementacion = true;
				}

			} catch (InputMismatchException ime) {

				System.out.println("Datos introducidos incorrectos ");
				sc.next();
			}
			
		}while(!elegidaImplementacion);
		
		// Se configura implementación
		
		IComponentes coleccion = null;
		IComponentes coleccionCircuito = null;
		
		if(implementacion == 1){
			
			coleccion = new ComponentesList();
			coleccionCircuito = new ComponentesList();
		
		}else if(implementacion == 2){
			
			coleccion = new ComponentesMap();
			coleccionCircuito = new ComponentesMap();
			
		}
		
		if( coleccion.addComponente(condensador1) &&
			coleccion.addComponente(condensador2) &&
			coleccion.addComponente(diodo1) &&
			coleccion.addComponente(diodo2) &&
			coleccion.addComponente(puenteDiodos1) &&
			coleccion.addComponente(puenteDiodos2) &&
			coleccion.addComponente(amplificador1) &&
			coleccion.addComponente(amplificador2)){
				
				componentesSelec = true;
			}
			
			
	
		
		System.out.println("Coleccion de componentes creada..." + componentesSelec);

		do {
			try {

				System.out.print("Elija el numero de componentes del circuito: ");
				numComponentes = sc.nextInt();
				sc.nextLine();

				if (numComponentes > 0 && numComponentes <= 3) {

					condicionNumComponente = true;

				} else {

					System.out.println("Introducir como m¡ximo de 3 componentes.");
				}

			} catch (InputMismatchException ime) {

				System.out.println("Solo se pueden introducir números. ");
				sc.next();
			}

		} while (!condicionNumComponente);

		circuito = new CircuitoSerie(numComponentes);

		arrayNombresComponentes = coleccion.getNombresComponentes();
		arrayComponentes = coleccion.getComponentes();

		System.out.println("Lista de componentes disponibles:");
		listarComponentes(coleccion);

		do {
			try {

				System.out.print("Elija el número del componente que quiere añadir al circuito: ");
				posicionComponente = sc.nextInt();
				sc.nextLine();
				posicionComponente = posicionComponente - 1; //resto uno por que los componentes se muestran desde 1 a 8 mientras que en el array desde 0 a 7

				if (posicionComponente >= 0 && posicionComponente < coleccion.size()) {
					
					if(coleccionCircuito.addComponente(arrayComponentes[posicionComponente])){
						
						System.out.println("Componente " + arrayNombresComponentes[posicionComponente] + " añadido.");
						componentesAñadidos++;
					
					}else{
							
						System.out.println("No puedes añadir el mismo componente dos veces");
					}					
					
					if (componentesAñadidos == numComponentes) {

						circuitoLleno = true;
					}

				} else {

					System.out.println("Opción seleccionada no disponible");
				}
			} catch (InputMismatchException ime) {

				System.out.println("Solo se pueden introducir números. ");
				sc.next();
			}

		} while (!circuitoLleno);

		circuito = crearCircuito(numComponentes, coleccionCircuito);

		System.out.println("Circuito creado!");

		boolean salir = false;

		do {
			System.out.println("Seleccionando generador...");

			do {

				System.out.print("Teclee tipo (T o S o U): ");
				tipoGenerador = sc.nextLine().toUpperCase();

				if (!tipoGenerador.equals("T") && !tipoGenerador.equals("S") && !tipoGenerador.equals("U")) {

					System.out.println("El tipo de generador especificado no es correcto.");

				} else {

					switch (tipoGenerador) {
					case "T":
						generador = new GeneradorSeñalTriangular("triangular", f);
						break;
					case "S":
						generador = new GeneradorSeñalSinusoidal("sinusoidal", f, faseInicialSinusoidal);
						break;
					case "U":
						generador = new GeneradorSeñalSinusoidalConSaturación("sinusoidal con saturación", f,
								faseInicialSinusoidal, umbralDefecto);
						break;
					}

					condicionGenerador = true;

				}

			} while (!condicionGenerador);

			System.out.println("Generador seleccionado!");

			System.out.println("Seleccionando componente ó circuito serie...");

			do {
				try {

					System.out.print("Pulsa 1 para seleccionar un componente ó 2 para seleccionar el circuito serie: ");

					opcion = sc.nextInt();
					sc.nextLine();
					if (opcion != 1 && opcion != 2) {

						System.out.println("Opcion elejida es incorrecta");
					} else {

						opcionSeleccionada = true;
					}

				} catch (InputMismatchException ime) {

					System.out.println("Solo se pueden introducir números. ");
					sc.next();
				}

			} while (!opcionSeleccionada);

			if (opcion == 1) {

				do {
					try {

						
						listarComponentes(coleccion);
						System.out.print("Elija el componente que quiere representar: ");
						posicionComponente = sc.nextInt();
						
						posicionComponente = posicionComponente - 1;

						if (posicionComponente >= 0 && posicionComponente < coleccion.size()) {

							System.out.println(
									"Componente " + arrayNombresComponentes[posicionComponente] + " seleccionado.");

							try {

								mostrarSonda(generador, arrayComponentes[posicionComponente], NUMEROMUESTRAS);

							} catch (MalFuncionamientoException e) {

								System.out.println("El componente: " + arrayNombresComponentes[posicionComponente]
										+ " ha reportado: " + e.getMessage());

							}

						} else {

							System.out.println("Opción seleccionada no disponible");
						}
					} catch (InputMismatchException ime) {

						System.out.println("Solo se pueden introducir números. ");
						sc.next();
					}

				} while (!circuitoLleno);

			} else if (opcion == 2) {

				System.out.println("Componentes del circuito serie: ");
				listarComponentes(coleccionCircuito);

				try {

					mostrarSonda(generador, circuito, NUMEROMUESTRAS);

				} catch (MalFuncionamientoException e) {

					System.out.println("El componente: " + circuito.toString() + " ha reportado: " + e.getMessage());

				}

			}
			
			String salirBucle;
			boolean eleccionSalida = false;
			
			do {
				try {
					
					System.out.print("Desea salir? S/N: ");
					salirBucle = sc.nextLine().toUpperCase();	
								
					if (!salirBucle.equals("N") && !salirBucle.equals("S")) {

						System.out.println("Opcion elejida es incorrecta");
												
					} else {
						
						eleccionSalida = true;
						
						if(salirBucle.equals("S")){
							
							salir = true;
														
						}
					}

				} catch (InputMismatchException ime) {

					System.out.println("Datos introducidos incorrectos ");
					sc.next();
				}
				
			}while(!eleccionSalida);
		
			
		} while (!salir);

		sc.close();
		System.out.println("Adios");
	}

	public static CircuitoSerie crearCircuito(int numComponentes, IComponentes coleccion) {

		CircuitoSerie circuito = new CircuitoSerie(numComponentes);
		Componente[] arrayComponentes = coleccion.getComponentes();

		for (int i = 0; i < coleccion.size(); i++) {

			circuito.addComponente(arrayComponentes[i]);

		}

		return circuito;

	}

	public static void listarComponentes(IComponentes coleccion) {
		
		String[] arrayNombresComponentes = coleccion.getNombresComponentes();
		
		for (int i = 0; i < coleccion.size(); i++) {
			
			System.out.print("Componente " + (i + 1) + ": ");
			System.out.println(arrayNombresComponentes[i]);

		}

	}

	private static void mostrarSonda(GeneradorSeñal generador, ITransferencia componente, int muestras)
			throws MalFuncionamientoException {

		String nombreGenerador = null;
		String nombreComponente = null;

		if (generador instanceof GeneradorSeñalTriangular) {

			nombreGenerador = "GeneradorSeñalTriangular";

		} else if (generador instanceof GeneradorSeñalSinusoidal) {

			nombreGenerador = "GeneradorSeñalSinusoidal";

		} else if (generador instanceof GeneradorSeñalSinusoidalConSaturación) {

			nombreGenerador = "GeneradorSeñalSinusoidalConSaturación";

		}

		if (componente instanceof Condensador) {

			nombreComponente = "Condensador";

		} else if (componente instanceof Diodo) {

			nombreComponente = "Diodo";

		} else if (componente instanceof Amplificador) {

			nombreComponente = "Amplificador";

		} else if (componente instanceof PuenteDiodos) {

			nombreComponente = "PuenteDiodos";

		} else if (componente instanceof CircuitoSerie) {

			nombreComponente = "CircuitoSerie";
		}

		SondaGráfica Pantalla = new SondaGráfica(nombreGenerador + " " + nombreComponente);

		if (componente instanceof Condensador) {

			for (int i = 0; i < muestras - 1; i++) {

				double salidaSeñal = generador.getSalida();
				if (salidaSeñal  >= 0) Pantalla.addMuestra(componente.getSalida(Math.abs(salidaSeñal)));
				else Pantalla.addMuestra(componente.getSalida(0));
			}

		} else {

			for (int i = 0; i < muestras - 1; i++) {

				Pantalla.addMuestra(componente.getSalida(generador.getSalida()));
			}
		}
		
	}

}

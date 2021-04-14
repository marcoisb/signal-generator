import java.awt.Color;

import simplegui.SimpleGUI;

/**
 * Sonda gráfica. Dibuja las muestras de una señal en una ventana gráfica.
 * 
 * La muestra actual se representa mediante un punto rojo distintivo. Cuando se
 * llega al final de la ventana, se desplaza la señal hacia la izquierda
 * (descartándose la parte desplazada) y se continúa dibujando nuevas muestras.
 * 
 * El área de representación de la señal inicialmente tiene un rango vertical
 * por defecto entre -1 y +1, pero se va ajustando si el rango de la señal se
 * hace demasiado grande o demasiado pequeño.
 * 
 * @author DTE-UPM
 * 
 *
 */
public class SondaGráfica {

	private SimpleGUI sg;
	// Valor base para cálculo de dimensiones (lo suyo es que coincida con un
	// número entero de ciclos de los generadores)
	private final static int XBASE = 500;
	// Fracción de la señal que se avanzará cada vez que se llegue al extremo
	// derecho de la pantalla. Se avanzará un 1/F de la pantalla.
	private final static int FRACCION_AVANCE = 3;
	// Usado para poner un título diferente a cada ventana de sonda.
	private static int proximoIdSonda = 1;
	// Valor de t actual (eje de abscisas)
	private double tActual = 0;
	// Valores de t de los extremos izquierdo y derecho visibles del eje de
	// abscisas.
	private double tmin, tmax;
	// Valores inferior y superior del eje de ordenadas.
	private double ymin, ymax;
	// Guarda una pantalla de muestras.
	private double[] muestras;
	// Posición base en la tabla de muestras guardadas (apunta al inicio de las
	// muestras válidas)
	int posBaseMuestras = 0;
	// Índice de la última muestra válida en la tabla de muestras guardadas
	// (respecto a la posición base)
	int posMuestraActual = 0;
	// Márgenes (en píxeles)
	int margenIzquierdo = 60, margenDerecho = 6, margenInferior = 20, margenSuperior = 20;
	// Dimensiones del área gráfica para la señal (en píxeles)
	private int anchuraGrafica, alturaGrafica;

	/**
	 * Crea una sonda gráfica en una ventana con el titulo especificado
	 * 
	 * @param titulo
	 *            titulo de la sonda (se muestra en el título de la ventana)
	 * @param escala
	 *            Valor máximo a mostrar en la escala del eje Y
	 */
	public SondaGráfica(String titulo, double escala) {
		// Dimensiones: 2 ciclos de generador de ancho, 4:3 relación de aspecto
		sg = new SimpleGUI(2 * XBASE + margenDerecho, (int) (2 * XBASE / 4.0 * 3.0), false);
		sg.setTitle("Sonda gráfica " + proximoIdSonda++ + ": " + titulo);
		ymin = -escala;
		ymax = escala;
		tmin = 0;
		tmax = 2 * XBASE;
		dibujarEjes();
	}

	/**
	 * Crea una sonda gráfica en una ventana con el titulo especificado y con
	 * valor máximo 1 en el eje Y.
	 * 
	 * @param titulo
	 *            titulo de la sonda (se muestra en el título de la ventana)
	 */
	public SondaGráfica(String titulo) {
		this(titulo, 1);
	}

	/**
	 * Proporciona a la sonda una muestra adicional de la señal. La sonda lo
	 * representa en la ventana gráficamente.
	 * 
	 * @param muestra
	 *            Valor de la muestra.
	 */
	public void addMuestra(double muestra) {
		if (muestras != null) {
			// Borra el punto rojo de la muestra anterior y lo redibuja en
			// negro.
			sg.eraseSingleDrawable("punto");
			redimensionarEjes(muestra);
			dibujarPunto(tActual, muestras[(posBaseMuestras + posMuestraActual) % muestras.length], 1, Color.BLACK,
					null);
			// Redimension abcisas
			// Calcula los valores mínimo y máximo del eje de ordenadas para la
			// nueva pantalla

			// Avanza la posición en la tabla de muestras.
			posMuestraActual++;
			// Si llega al final de la pantalla, desplaza el eje de abscisas
			// y redimensiona el de ordenadas (si es necesario)
			if (posMuestraActual == muestras.length) {
				// Avanza la posición base en la tabla de muestras
				posBaseMuestras = (posBaseMuestras + muestras.length / FRACCION_AVANCE) % muestras.length;
				posMuestraActual = muestras.length - muestras.length / FRACCION_AVANCE;
				sg.eraseAllDrawables();
				dibujarEjes();
				double incrementoX = (tmax - tmin) / FRACCION_AVANCE;
				tmin += incrementoX;
				tmax += incrementoX;
				double t1 = tmin;
				for (int i = 0; i < muestras.length - muestras.length / FRACCION_AVANCE; i++) {
					dibujarPunto(t1, muestras[(posBaseMuestras + i) % muestras.length], 1, Color.BLACK, null);
					t1++;
				}
			}
		} else
			// Es la primera muestra que recibe la sonda.
			muestras = new double[(int) tmax];
		// Guarda la muestra y la dibuja con un punto rojo grande
		muestras[(posBaseMuestras + posMuestraActual) % muestras.length] = muestra;
		dibujarPunto(tActual, muestra, 4, Color.RED, "punto");
		tActual++;
		// Introduce un pequeño retardo para la visualización
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Dibuja un punto en el área de la señal.
	 * 
	 * @param t
	 *            Valor del eje de abscisas
	 * @param y
	 *            Valor del eje de ordenadas
	 * @param radio
	 *            Radio del punto (en píxeles)
	 * @param color
	 *            Color del punto.
	 * @param nombre
	 *            Nombre del drawable o null si no se quiere dar nombre.
	 */
	private void dibujarPunto(double t, double y, double radio, Color color, String nombre) {
		sg.drawDot(margenIzquierdo + (t - tmin) / (tmax - tmin) * anchuraGrafica,
				margenSuperior + (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica, radio, color, 1, nombre);
	}

	/**
	 * Dibuja los ejes. En realidad solo dibuja el eje de ordenadas
	 */
	private void dibujarEjes() {
		double y, separacion;

		// Calcula las dimensiones del área gráfica para la señal
		anchuraGrafica = sg.getWidth() - margenIzquierdo - margenDerecho;
		alturaGrafica = sg.getHeight() - margenInferior - margenSuperior;
		// Dibuja el eje de ordenadas
		sg.drawLine(margenIzquierdo, 0, margenIzquierdo, sg.getHeight());
		// Pone marcas de valores en el eje de ordenadas
		y = ymin;
		separacion = (ymax - ymin) / 20;
		while (y <= ymax) {
			sg.drawLine(margenIzquierdo - 5, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior,
					margenIzquierdo, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior);
			String valor = String.format("%.2f", y);
			sg.drawText(valor, 0, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior);
			y += separacion;
		}
	}

	/**
	 * Comprueba si es necesario redimensionar los ejes y, en caso afirmativo,
	 * calcula los nuevos valores extremos.
	 */
	private void redimensionarEjes(double muestra) {
		// Calcula los valores mínimo y máximo del eje de ordenadas para las
		// muestras guardadas
		double mmin = ymin, mmax = ymax;
		if (muestra < mmin)
			mmin = muestra;
		if (muestra > mmax)
			mmax = muestra;

		// Mira si hay que redimensionar el eje de ordenadas
		if ((mmax != ymax) || (mmin != ymin)) {
			// Para cada extremo, calcula el valor más cercano (por abajo para
			// ymin y por arriba para ymax)
			// que es un número redondo (entendiéndose por "redondo" que es
			// múltiplo de una potencia de 10
			// de orden cercano al tamaño del eje)
			System.out.println("Redimensionando...");
			double orden, valorSignificativo;
			final double cercaniaOrdenRedondeo = 1;
			orden = Math.floor(Math.log10(Math.abs(mmax - mmin))) - cercaniaOrdenRedondeo;
			valorSignificativo = Math.floor(mmin / Math.pow(10, orden));
			ymin = valorSignificativo * Math.pow(10, orden) * 2;
			valorSignificativo = Math.ceil(mmax / Math.pow(10, orden));
			ymax = valorSignificativo * Math.pow(10, orden) * 2;
			// Si el cero está más o menos cerca de la mitad del rango del eje,
			// lo pone exactamente en el centro.
			System.out.println("Redimensionando y:" + ymax + "-" + ymin);
			if (Math.signum(ymax) != Math.signum(ymin) && Math.abs(Math.abs(ymax / ymin) - 1) <= 0.25) {
				double max = Math.max(Math.abs(ymax), Math.abs(ymin));
				ymax = max;
				ymin = -max;
			}
			sg.eraseAllDrawables();
			dibujarEjes();
			// Redibuja las muestras anteriores al redimensionar
			for (int i = 0; i < posMuestraActual; i++)
				dibujarPunto(i, muestras[(posBaseMuestras + i) % muestras.length], 1, Color.BLACK, null);

		}
	}

}

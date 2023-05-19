package examen1819.solucionMarta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Modelo extends HashMap<Libro, Estadisticas> implements Serializable {

	public static final int CORRECTO = 0;
	public static final int ERROR = 1;
	public static final int CANCELADO = 2;

	private JFileChooser selectorFicheros;
	private FileNameExtensionFilter filtroSer;
	private FileNameExtensionFilter filtroTxt;

	public Modelo() {
		this.creaFiltrosyChoosers();
	}

	public void creaFiltrosyChoosers() {
		this.filtroSer = new FileNameExtensionFilter("Fichero de estadísticas serializados", "ser");
		this.filtroTxt = new FileNameExtensionFilter("Fichero del texto de un libro", "txt");
		this.selectorFicheros = new JFileChooser();
		this.selectorFicheros.addChoosableFileFilter(this.filtroSer);
		this.selectorFicheros.addChoosableFileFilter(this.filtroTxt);
	}

	public Set<Libro> getConjuntoLibros() {
		return this.keySet();
	}

	public Libro getLibroPorTítulo(String título) {
		for (Libro libro : this.keySet())
			if (libro.getTítulo().equals(título))
				return libro;
		return null;
	}

	/*
	 * a) Permite seleccionar un fichero .txt y lo lee y muestra en el área de //
	 * texto. Devuelve un objeto de la clase libro, con los datos obtenidos. // Si
	 * no se leyó el libro por el motivo que fuera, se devuelve null.
	 */
	public Libro cargarLibro(Ventana padreDelFileChooser) throws FileNotFoundException, IOException {
		Libro libro = null;
		this.selectorFicheros.setFileFilter(this.filtroTxt);
		this.selectorFicheros.setCurrentDirectory(new File("\\."));
		int op = this.selectorFicheros.showOpenDialog(padreDelFileChooser);
		if (op == JFileChooser.APPROVE_OPTION) {
			File fichero = this.selectorFicheros.getSelectedFile();
			try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
				libro = new Libro();
				//libro.setTítulo(fichero.getName().substring(0, fichero.getName().lastIndexOf(".")));
				libro.setTítulo(fichero.getName().split("\\.")[0]);
				String contenido = "";
				String linea;
				while ((linea = in.readLine()) != null) {
					contenido += linea;
				}
				libro.setTexto(contenido);
			}
		}
		return libro;
	}

	/*
	 * b)// Recibe un objeto tipo Libro, y calcula y devuelve las estadísticas //
	 * calculadas: número de palabras distintas que tiene (usar un mapa y //
	 * StringTokenizer) y las tres palabras que aparecen con más frecuencia, y // su
	 * número de apariciones.
	 */
	public Estadisticas obtenEstadísticasYAñadeAlModelo(Libro libro) {
		Estadisticas estadisticas = new Estadisticas();
		TreeMap<String, Integer> mapaPalabras = new TreeMap<String, Integer>();
		StringTokenizer st = new StringTokenizer(libro.getTexto(), " \n,.!()?\"¡¿");
		while (st.hasMoreTokens()) {
			String palabra = st.nextToken();

			if (!mapaPalabras.containsKey(palabra))
				mapaPalabras.put(palabra, 0);
			mapaPalabras.put(palabra, mapaPalabras.get(palabra) + 1);
		}
		estadisticas.setNumPalabrasDistintas(mapaPalabras.size());

		List<Entry<String, Integer>> listPalsOrdenadasPorNumApariciones = new ArrayList<>(mapaPalabras.entrySet());
		listPalsOrdenadasPorNumApariciones.sort(Entry.comparingByValue());
		for (int i = 0; i < 3; i++) {
			estadisticas.getTresPalabras().put(
					listPalsOrdenadasPorNumApariciones.get(listPalsOrdenadasPorNumApariciones.size() - i - 1).getKey(),
					listPalsOrdenadasPorNumApariciones.get(listPalsOrdenadasPorNumApariciones.size() - i - 1)
							.getValue());
		}
		this.put(libro, estadisticas);
		return estadisticas;
	}

	/*
	 * //c) Recibe la ventana padre del diálogo de selección de fichero, y permite
	 * // guardar en un fichero serializado un HashMap<Libro, Estadísticas>, // con
	 * los datos del modelo. NO EL MODELO. Devuelve CORRECTO si se pudo // hacer.
	 */
	public int guardarMapaSerializado(Ventana ventana) throws FileNotFoundException, IOException {
		int res = ERROR;
		HashMap<Libro, Estadisticas> mapaAEscribir = new HashMap<Libro, Estadisticas>(this);
		this.selectorFicheros.setFileFilter(this.filtroSer);
		this.selectorFicheros.setCurrentDirectory(new File("."));
		int op = this.selectorFicheros.showOpenDialog(ventana);
		if (op == JFileChooser.APPROVE_OPTION) {
			File fichero = this.selectorFicheros.getSelectedFile();
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichero))) {
				out.writeObject(mapaAEscribir);
				res = CORRECTO;
			}

		}
		return res;
	}

	/*
	 * //d) Recibe la ventana padre del diálogo de selección de fichero, y permite
	 * // leer un fichero serializado. Si se corresponde con un fichero de un //
	 * HashMap<Libro, Estadísticas>, devuelve el número de pares que contiene. // En
	 * otro caso, o produce una excepción o devuelve 0.
	 */
	public int comprobarModeloSerializado(Ventana ventana)
			throws IOException, FileNotFoundException, ClassNotFoundException, ClassCastException {
		int numPares = 0;
		this.selectorFicheros.setFileFilter(this.filtroSer);
		this.selectorFicheros.setCurrentDirectory(new File("."));
		int op = this.selectorFicheros.showOpenDialog(ventana);
		if (op == JFileChooser.APPROVE_OPTION) {
			File fichero = this.selectorFicheros.getSelectedFile();
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichero))) {

				HashMap<Libro, Estadisticas> hashLeido = (HashMap<Libro, Estadisticas>) in.readObject();
				numPares = hashLeido.size();

			}
		}
		return numPares;
	}

}

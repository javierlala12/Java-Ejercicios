package examen1819.solucionMarta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Ventana extends JFrame {

	private Principal controlador;

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArchivos;
	private JMenuItem menuCargarSer;
	private JMenuItem menuGuardarSer;
	private JSeparator separator;
	private JMenuItem menuCargarLibro;
	private JSeparator separator_1;
	private JMenuItem menuSalir;
	private JScrollPane scrollPane;
	private JTextArea areaTexto;
	private JPanel panel;
	private JLabel lblLibro;
	private JComboBox<String> comboLibros;
	private JPanel panel_1;
	private JTextField palabra1;
	private JProgressBar barra1;
	private JLabel numVeces1;
	private JTextField palabra2;
	private JProgressBar barra2;
	private JLabel label;
	private JLabel numVeces2;
	private JTextField palabra3;
	private JProgressBar barra3;
	private JLabel numVeces3;
	private JLabel lblNmeroDePalabras;
	private JTextField campoNumPalabras;
	private JButton botonEstadisticas;

	private Libro libroActual;

	public Ventana(Principal controlador) {
		setTitle("Gesti\u00F3n de Estad\u00EDsticas de Libros, S.A.");
		this.controlador = controlador;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

		}

		this.libroActual = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 362);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivos = new JMenu("Archivos");
		menuBar.add(mnArchivos);

		menuCargarSer = new JMenuItem("Comprobar correcci\u00F3n de fichero serializado");
		menuCargarSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuComprobarSerPulsado();
			}
		});
		mnArchivos.add(menuCargarSer);

		menuGuardarSer = new JMenuItem("Guardar fichero de libros y estad\u00EDsticas serializado");
		menuGuardarSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuGuardarSerPulsado();
			}
		});
		mnArchivos.add(menuGuardarSer);

		separator = new JSeparator();
		mnArchivos.add(separator);

		menuCargarLibro = new JMenuItem("Cargar libro de txt");
		menuCargarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuCargarLibroPulsado();
			}
		});
		mnArchivos.add(menuCargarLibro);

		separator_1 = new JSeparator();
		mnArchivos.add(separator_1);

		menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuSalirPulsado();
			}
		});
		mnArchivos.add(menuSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Estad\u00EDsticas",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE));

		areaTexto = new JTextArea();
		lblLibro = new JLabel("Libro:");

		palabra1 = new JTextField();
		palabra1.setEditable(false);
		palabra1.setColumns(10);

		barra1 = new JProgressBar();
		barra1.setForeground(Color.RED);

		numVeces1 = new JLabel("");

		palabra2 = new JTextField();
		palabra2.setEditable(false);
		palabra2.setColumns(10);

		barra2 = new JProgressBar();
		barra2.setForeground(Color.RED);

		label = new JLabel("");

		numVeces2 = new JLabel("");

		palabra3 = new JTextField();
		palabra3.setEditable(false);
		palabra3.setColumns(10);

		barra3 = new JProgressBar();
		barra3.setForeground(Color.RED);

		numVeces3 = new JLabel("");

		campoNumPalabras = new JTextField();
		campoNumPalabras.setEditable(false);
		campoNumPalabras.setColumns(10);

		botonEstadisticas = new JButton("Calcular estad\u00EDsticas del libro cargado y a\u00F1adirlo al modelo");
		comboLibros = new JComboBox<String>();
		comboLibros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboLibrosCambiado();
			}
		});

		this.comboLibros.addItem("seleccione un libro...");
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Tres palabras m\u00E1s frecuentes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		lblNmeroDePalabras = new JLabel("N\u00FAmero de palabras distintas en el libro:");

		botonEstadisticas.setEnabled(false);
		botonEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonEstadisticasPulsado();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(botonEstadisticas, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING,
								gl_panel.createSequentialGroup()
										.addComponent(lblLibro, GroupLayout.PREFERRED_SIZE, 57,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboLibros, 0, 283, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING,
								gl_panel.createSequentialGroup().addComponent(lblNmeroDePalabras)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(campoNumPalabras,
												GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblLibro).addComponent(
						comboLibros, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNmeroDePalabras)
						.addComponent(campoNumPalabras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE).addComponent(botonEstadisticas)
				.addContainerGap()));

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(palabra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(barra3, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(palabra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(barra2, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(palabra1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(barra1, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(numVeces1).addGap(12))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
										.addComponent(numVeces2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(gl_panel_1.createSequentialGroup().addGap(4)
										.addComponent(numVeces3, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
										.addContainerGap()))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(palabra1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1
								.createSequentialGroup().addGap(14)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(numVeces1)
										.addComponent(barra1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(3)))
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(6).addComponent(palabra2,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(barra2,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(numVeces2)))
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(palabra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(barra3,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(9).addComponent(numVeces3)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);

		areaTexto.setEditable(false);
		scrollPane.setViewportView(areaTexto);
		contentPane.setLayout(gl_contentPane);
	}

	public void mostrar() {
		this.setVisible(true);
	}

	protected void menuCargarLibroPulsado() {
		try {
			Libro libroCargado = this.controlador.cargarLibro(this);
			if (libroCargado != null) {
				this.areaTexto.setText(libroCargado.getTexto());
				this.libroActual = libroCargado;
				this.botonEstadisticas.setEnabled(true);
			} else
				this.libroActual = null;
		} catch (FileNotFoundException e) {
			this.error("No se pudo abrir el fichero");
		} catch (IOException e) {
			this.error("Error de lectura del fichero");
		}

	}

	private void error(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void msg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Correcto", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void botonEstadisticasPulsado() {
		Estadisticas estadística;
		estadística = this.controlador.obtenEstadísticasYAñadeAlModelo(this.libroActual);
		if (estadística.getTresPalabras().size() < 3)
			this.error("El texto no tiene más de tres palabras");
		else {
			this.actualizaCombo();
			if (libroActual != null)
				this.comboLibros.setSelectedItem(this.libroActual.getTítulo());
			this.botonEstadisticas.setEnabled(false);
		}

	}

	private void actualizaCombo() {
		this.comboLibros.removeAllItems();
		this.comboLibros.addItem("seleccione un libro...");
		for (Libro libro : this.controlador.getConjuntoLibros())
			this.comboLibros.addItem(libro.getTítulo());
	}

	protected void comboLibrosCambiado() {
		if (this.comboLibros.getSelectedIndex() > 0) {
			Libro libroSeleccionado = this.controlador.getLibroPorTítulo((String) this.comboLibros.getSelectedItem());
			this.areaTexto.setText(libroSeleccionado.getTexto());
			this.libroActual = libroSeleccionado;
			this.actualizaBarras(libroSeleccionado);
		} else {
			this.actualizaBarras(null);
			this.areaTexto.setText("");
		}

		this.botonEstadisticas.setEnabled(false);
	}

	private void actualizaBarras(Libro libro) {
		if (libro != null) {
			Estadisticas estadística;
			estadística = this.controlador.obtenEstadísticasYAñadeAlModelo(this.libroActual);
			ArrayList<String> listaPalabras = new ArrayList<String>();
			listaPalabras.addAll(estadística.getTresPalabras().keySet());
			ArrayList<Integer> listaValores = new ArrayList<Integer>();
			listaValores.addAll(estadística.getTresPalabras().values());

			this.barra1.setMaximum(listaValores.get(0));
			this.barra2.setMaximum(listaValores.get(0));
			this.barra3.setMaximum(listaValores.get(0));

			this.barra1.setValue(listaValores.get(0));
			this.barra2.setValue(listaValores.get(1));
			this.barra3.setValue(listaValores.get(2));

			this.palabra1.setText(listaPalabras.get(0));
			this.palabra2.setText(listaPalabras.get(1));
			this.palabra3.setText(listaPalabras.get(2));

			this.numVeces1.setText("(" + listaValores.get(0) + ")");
			this.numVeces2.setText("(" + listaValores.get(1) + ")");
			this.numVeces3.setText("(" + listaValores.get(2) + ")");

			this.campoNumPalabras.setText("" + estadística.getNumPalabrasDistintas());

		} else {
			this.barra1.setValue(0);
			this.barra2.setValue(0);
			this.barra3.setValue(0);

			this.palabra1.setText("");
			this.palabra2.setText("");
			this.palabra3.setText("");

			this.numVeces1.setText("");
			this.numVeces2.setText("");
			this.numVeces3.setText("");

			this.campoNumPalabras.setText("");
		}
	}

	protected void menuSalirPulsado() {
		System.exit(0);
	}

	protected void menuGuardarSerPulsado() {
		int resultado = -1;
		try {
			resultado = this.controlador.guardarModeloSerializado(this);
			if (resultado == Modelo.CORRECTO)
				this.msg("Modelo guardado");

		} catch (FileNotFoundException eFNF) {
			this.error("Error al abrir el fichero: " + eFNF.toString());
		} catch (IOException eIO) {
			this.error("Error al escribir en el fichero: " + eIO.toString());
		}
	}

	protected void menuComprobarSerPulsado() {
		int resultado;
		try {
			resultado = this.controlador.comprobarModeloSerializado(this);
			this.msg("El fichero es correcto; contiene " + resultado + " libros.");

		} catch (ClassCastException eCC) {
			this.error("El fichero no es correcto");
		} catch (FileNotFoundException eFNF) {
			this.error("Error al abrir el fichero");
		} catch (IOException eIO) {
			this.error("El fichero no es correcto");
		} catch (ClassNotFoundException eCNF) {
			this.error("No se encuentra la clase Modelo");
		}
	}
}

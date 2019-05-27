package Sistema;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TDAArbol.Tree;
import TDAColaCP.PriorityQueue;
import TDALista.PositionList;
import java.awt.Color;
import java.awt.Toolkit;

public class application {

	private JFrame frmSistemaDeArchivos;
	private ArbolArchivos logica;
	private JButton btnCargar;
	JButton btnAgregar ;
	JComboBox<String> cMostrar;
	JScrollPane scrollPane ;
	JTextArea textArea ;
	private JButton btnMostrarCamino;
	private JButton btnClonar;
	JButton btnArchivos;
	JButton btnCarpetas;
	JButton btnArchivosP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					application window = new application();
					window.frmSistemaDeArchivos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeArchivos = new JFrame();
		frmSistemaDeArchivos.setTitle("Sistema de archivos");
		frmSistemaDeArchivos.setBounds(450, 250, 900, 460);
		frmSistemaDeArchivos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setToolTipText("Crea un disco local como raiz del arbol");
		btnCargar.setBounds(12, 33, 128, 25);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String raiz=JOptionPane.showInputDialog("Escribe el nombre disco local");
				if(raiz!=null && !raiz.equals("") ) {
				
				logica=new ArbolArchivos();
				logica.CargarArbol(raiz);
				btnCargar.setEnabled(false);
				btnAgregar.setEnabled(true);
				cMostrar.setEnabled(true);
				btnClonar.setEnabled(true);
				btnMostrarCamino.setEnabled(true);
				btnArchivos.setEnabled(true);
				btnCarpetas.setEnabled(true);
				btnArchivosP.setEnabled(true);
				btnClonar.setToolTipText("Permite hacer un clon de arbol");
				btnAgregar.setToolTipText("Agregar un nuevo elemento a una carpeta");
				btnMostrarCamino.setToolTipText("Muestra el camino del elemento hacia la raiz");
				btnArchivos.setToolTipText("Lista y muestra los archivos del arbol en preorden");
				btnCarpetas.setToolTipText("Lista y muestra las carpetas del arbol en preorden");
				cMostrar.setToolTipText("Seleccione como desea mostrar el arbol");
				btnArchivosP.setToolTipText("Muestra los archivos ordenados de mayor a menor profundidad");
				btnCargar.setToolTipText("Deshabilitado porque ya se creo un disco");
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingrese un nombre valido");
				}
			}
		});
		frmSistemaDeArchivos.getContentPane().setLayout(null);
		frmSistemaDeArchivos.getContentPane().add(btnCargar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnAgregar.setBounds(12, 71, 128, 25);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nodo=JOptionPane.showInputDialog("Escriba nombre a agregar");
				String padre=JOptionPane.showInputDialog("Escriba nombre de la carpeta donde agregar");
				
					if(logica.AgregarNodo(nodo, padre)) {
						
					}
					else {
						JOptionPane.showMessageDialog(null,"Ingrese carpeta existente");
					}
			}
		});
		btnAgregar.setEnabled(false);
		frmSistemaDeArchivos.getContentPane().add(btnAgregar);
		
		cMostrar = new JComboBox<String>();
		cMostrar.setToolTipText("Deshabilitado por que no hay ningun archivo");
		cMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=(String) cMostrar.getSelectedItem();
				if(s.equals("Preorden")) {
					textArea.setText("Preorden:\n"+logica.MostrarPreOrden());
				}
				else if(s.equals("Postorden")) {
					textArea.setText("PostOrden: \n"+logica.MostrarPostOrden());
				}
				else {
					if(s.equals("Por Niveles")) {
					textArea.setText("Por Niveles: \n"+logica.MostrarPorNiveles());
					}
				}
				
				
			}
		});
		cMostrar.setBounds(12, 109, 128, 24);
		cMostrar.setEnabled(false);
		frmSistemaDeArchivos.getContentPane().add(cMostrar);
		cMostrar.addItem("Tipo de muestra");
		cMostrar.addItem("Preorden");
		cMostrar.addItem("Postorden");
		cMostrar.addItem("Por Niveles");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 13, 645, 357);
		frmSistemaDeArchivos.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		btnMostrarCamino = new JButton("Mostrar camino");
		btnMostrarCamino.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnMostrarCamino.setEnabled(false);
		btnMostrarCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nodo=JOptionPane.showInputDialog("Escriba nombre de archivo/carpeta a buscar");
				String s=logica.MostrarCamino(nodo);
				if (s.equals("Posicion Invalida")) {
					JOptionPane.showMessageDialog(null,"Ingresar carpeta o archivo existente");
				}
				else {
					textArea.setText("Camino:\n"+logica.MostrarCamino(nodo));
				}
			}
		});
		btnMostrarCamino.setBounds(12, 146, 128, 25);
		frmSistemaDeArchivos.getContentPane().add(btnMostrarCamino);
		
		btnClonar = new JButton("Clonar");
		btnClonar.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnClonar.setEnabled(false);
		btnClonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tree<String> t=logica.clonar();
				String st="";
				for(String s:t) {
					st+=s+" ";
				}
				textArea.setText("Clon del arbol: \n"+st);
			}
		});
		btnClonar.setBounds(12, 184, 128, 25);
		frmSistemaDeArchivos.getContentPane().add(btnClonar);
		
		btnArchivos = new JButton("ListarAchivos");
		btnArchivos.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnArchivos.setEnabled(false);
		btnArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PositionList<String> l=logica.archivos();
				String st="";
				for(String s:l) {
					st+=s+" ";
				}
				if(st.equals("")) {
					textArea.setText("No existen archivos");
				}
				else {
				textArea.setText("Archivos: \n"+st);
				}
			}
		});
		btnArchivos.setBounds(12, 222, 128, 25);
		frmSistemaDeArchivos.getContentPane().add(btnArchivos);
		
		btnCarpetas = new JButton("ListarCarpetas");
		btnCarpetas.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnCarpetas.setEnabled(false);
		btnCarpetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PositionList<String> l=logica.carpetas();
				String st="";
				for(String s:l) {
					st+=s+" ";
				}
				textArea.setText("Carpetas: \n"+st);
			}
			
		});
		btnCarpetas.setBounds(12, 260, 128, 25);
		frmSistemaDeArchivos.getContentPane().add(btnCarpetas);
		
		btnArchivosP = new JButton("Archivos prioridad");
		btnArchivosP.setToolTipText("Deshabilitado por que no hay ningun archivo");
		btnArchivosP.setEnabled(false);
		btnArchivosP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String st=logica.hojasCP();
			if(st.equals("")) {
				textArea.setText("No existen archivos");
			}
			else {
				textArea.setText("Archivos ordenados por profundidad: \n"+st);
			}
			}
		});
		btnArchivosP.setBounds(12, 298, 142, 25);
		frmSistemaDeArchivos.getContentPane().add(btnArchivosP);
		
		
	}
}

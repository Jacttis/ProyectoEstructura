package Sistema;
import Excepciones.*;
import TDAArbol.*;
import TDALista.*;
import TDAPila.*;
import Extras.*;
import java.util.Comparator;
import java.util.Iterator;
import TDACola.*;
import TDAColaCP.*;
/**
 * Clase ArbolArchivos
 * @author Julian Acttis y Axel Fontana
 *
 */
public class ArbolArchivos {
	protected Arbol<String> tree;
	
	public ArbolArchivos() {
		tree=null;
	}
	/**
	 * Crea un nuevo arbol y su raiz es el elemento pasado como parametro.
	 * @param s elemento a agregar como raiz
	 */
	protected void CargarArbol(String s) {
		tree=new Arbol<String>();
		try {
			tree.createRoot(s);
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Agrega un nodo con rótulo r como útimo hijo de un nodo con rotulo p.
	 * @param r Elemento a agregar
	 * @param p Elemento padre
	 * @return boolean devuelve si el elemento fue agregado o no
	 */
	protected boolean AgregarNodo(String r,String p) {
		boolean esta=false;
		try {
		Iterable<Position<String>> lista=tree.positions();
		Iterator<Position<String>> it=lista.iterator();
		Position<String>pos=null;
		
		while(it.hasNext() && !esta){
			Position<String> aux=it.next();
			if(aux.element().equals(p)) {
				esta=true;
				pos=aux;
			}
		}
		if(esta)
			tree.addLastChild(pos, r);
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		return esta;
	}
	/**
	 * Devuelve un String del arbol en PreoOrden
	 * @return String arbol en preorden
	 */
	public String MostrarPreOrden() {
		String st="";
		for(String s:tree) {
			st+=s+" ";
		}
		return st;
	}
	/**
	 * Devuelve un String del arbol en PostOrden
	 * @return String arbol en postorden
	 */
	public String MostrarPostOrden() {
		String ret="";
		PositionList<String>l=new ListaDoblementeEnlazada<String>();
		try {
			pos(l,tree.root());
			while(!l.isEmpty()) {
				ret+=l.remove(l.first())+" ";
			} 
		} catch (EmptyTreeException | InvalidPositionException | EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	private void pos(PositionList<String>l,Position<String> r) {
		
		try {
			for(Position<String> h:tree.children(r)) {
				pos(l,h);
			}
			l.addLast(r.element());
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
/**
 * Devuelve un String del arbol por niveles
 * @return String arbol por niveles
 */
	public String MostrarPorNiveles() {
		String s="";
		try {
		Queue<Position<String>> cola=new ColaCircular<Position<String>>() ;
		cola.enqueue(tree.root());
		cola.enqueue(null);
		while(!cola.isEmpty()) {
			Position<String>aux=cola.dequeue();
			if(aux!=null) {
				s+=aux.element()+" ";
				for(Position<String>h:tree.children(aux)) {
					cola.enqueue(h);
				}
			}
			else {
				s+="\n";
				if(!cola.isEmpty()) {
					cola.enqueue(null);
				}
			}
			
		}
		
		
		
		}
		catch( EmptyTreeException | EmptyQueueException | InvalidPositionException e) {
			
		}
		return s;
	}
/**
 * Devuelve un String del camino de un nodo con rotulo s a la raiz
 * @param s rotulo del nodo a mostrar camino
 * @return String camino de la raiz a el nodo de rotulo pasado por parametro
 */
	public String MostrarCamino(String s) {
		Iterable<Position<String>> lista=tree.positions();
		Iterator<Position<String>> it=lista.iterator();
		Stack<String> pila=new PilaEnlazada<String>();
		Position<String>pos=null;
		String ret="";
		boolean esta=false;
		try {
		while(it.hasNext() && !esta){
			Position<String> aux=it.next();
			if(aux.element().equals(s)) {
				esta=true;
				pos=aux;
			}
		}
		if(!esta) {
			return "Posicion Invalida";
		}
		while(pos!=null) {
			pila.push(pos.element());
			if (pos==tree.root()) {
				pos=null;
			}
			else {
				pos=tree.parent(pos);
			}
		}
		while(!pila.isEmpty()) {
			if(pila.size()==1) {
				ret+=pila.pop();
			}
			else {
				ret+=pila.pop()+"-->";
			}
		}
	  }
		catch(InvalidPositionException | BoundaryViolationException | EmptyTreeException | EmptyStackException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * Devuelve un arbol nuevo identico 
	 * @return Tree 
	 */
	public Tree<String> clonar(){
		Tree<String> nuevo=new Arbol<String>();
		try {
			if(!tree.isEmpty()) {
				nuevo.createRoot(tree.root().element());
				clonaraux(nuevo,tree.root(),nuevo.root());
			}
		
		}
		catch(InvalidOperationException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return nuevo;
	}
	private void clonaraux(Tree<String>nuevo,Position<String> post,Position<String>posn) {
	try {
		for(Position<String> ht:tree.children(post)) {
			Position<String> hn= nuevo.addLastChild(posn, ht.element());
			clonaraux(nuevo,ht,hn);
		}
	}
	catch(InvalidPositionException e) {
		e.printStackTrace();
	}
		
		
	}
	/**
	 * Devuelve una lista con los archivos del arbol
	 * @return PositionList
	 */
	public PositionList<String> archivos(){
		PositionList<String> arch=new ListaDoblementeEnlazada<String>();
	  try {	
		for(Position<String> s :tree.positions()) {
			if(tree.isExternal(s) && !tree.isRoot(s))
				arch.addLast(s.element());
		}
	  }
	  catch(InvalidPositionException e) {
		  e.printStackTrace();
	  }
	  return arch;
	}
	/**
	 * Devuelve una lista con las carpetas del arbol
	 * @return PositionList
	 */
	public PositionList<String> carpetas(){
		PositionList<String> carp=new ListaDoblementeEnlazada<String>();
		try {
		for(Position<String> s:tree.positions()) {
			if(tree.isInternal(s) || tree.isRoot(s)) {
				carp.addLast(s.element());
			}
		}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		return carp;
		
	}
	/**
	 * Devuelve un String con los archivos ordenados de mayor a menor profundidad
	 * @return string
	 */
	public String hojasCP() {
		PriorityQueue<Integer,String> pq=hojasCPaux();
		String st="";
		try {
		while(!pq.isEmpty()) {
			st=pq.removeMin().getValue()+" "+st;
		}
		}
		catch(EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
		return st;
	}

	private PriorityQueue<Integer,String> hojasCPaux(){
		Comparator<Integer> c=new Comparador<Integer>();
		PriorityQueue<Integer,String> salida = new Heap<Integer,String>(c);
		Iterable<Position<String>> elementos =tree.positions();
		for(Position<String> i :elementos) {
			try {
				if(tree.isExternal(i) && !tree.isRoot(i)) {
				salida.insert(profundidad(i),i.element());
				}
			} catch (InvalidKeyException | InvalidPositionException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
		private int profundidad(Position<String> p) {
			int salida = 0;
			try {
				if (p == tree.root())
					salida = 0;
				else {
					salida = 1 + profundidad(tree.parent(p));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return salida;
		}





}

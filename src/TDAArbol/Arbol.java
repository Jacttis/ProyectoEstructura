package TDAArbol;
import TDALista.*;
import java.util.Iterator;
import Extras.*;
import Excepciones.*;
/**
 * Clase Arbol
 * @author Julian Acttis y Axel Fontana
 *
 * @param <E>
 */
public class Arbol<E> implements Tree<E> {

	TNodo<E>raiz;
	int size;
	
	public Arbol() {
		raiz=null;
		size=0;
	}
	
	/**
	 * Consulta la cantidad de nodos en el árbol.
	 * @return Cantidad de nodos en el árbol.
	 */
	public int size() {
		return size;
	}

	/**
	 * Consulta si el árbol está vacío.
	 * @return Verdadero si el árbol está vacío, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size==0;
	}
	/**
	 * Devuelve un iterador de los elementos almacenados en el árbol en preorden.
	 * @return Iterador de los elementos almacenados en el árbol.
	 */
	public Iterator<E> iterator(){
		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		for (Position<E> p: positions())
			l.addLast(p.element());
		
		return l.iterator();
	}
	/**
	 * Devuelve una colección iterable de las posiciones de los nodos del árbol.
	 * @return Colección iterable de las posiciones de los nodos del árbol.
	 */
	public Iterable<Position<E>> positions(){
		PositionList<Position<E>> l=new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty()) {
		pre(l,raiz);
		}
		return l;
		
	}
	private void pre(PositionList<Position<E>> l,TNodo<E> r) {
		l.addLast(r);
		for(TNodo<E> h:r.getHijos()) {
			pre(l,h);
		}	
	}
	
	/**
	 * Reemplaza el elemento almacenado en la posición dada por el elemento pasado por parámetro. Devuelve el elemento reemplazado.
	 * @param v Posición de un nodo.
	 * @param e Elemento a reemplazar en la posición pasada por parámetro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion Invalida");
		}
		TNodo<E> aux=checkPosition(v);
		E ret=aux.element();
		aux.setElemento(e);
		return ret;
	}
	private TNodo<E> checkPosition(Position<E> p ) throws InvalidPositionException{
		if (p==null) {
			throw new InvalidPositionException("Posicion Invalida");
		}
		TNodo<E> n=null;
		try {
		 n=(TNodo<E>)p;
		}
		catch(ClassCastException e) {
			throw new InvalidPositionException("Posicion Invalida");
		}
		return n;
		
	}
	/**
	 * Devuelve la posición de la raíz del árbol.
	 * @return Posición de la raíz del árbol.
	 * @throws EmptyTreeException si el árbol está vacío.
	 */
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty()) {
			throw new EmptyTreeException("Arbol Vacio");
		}
		return raiz;
	}
	
	/**
	 * Devuelve la posición del nodo padre del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Posición del nodo padre del nodo correspondiente a la posición dada.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde a la raíz del árbol.
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion Invalida");
		}
		TNodo<E> aux=checkPosition(v);
		if(aux==raiz) {
			throw new BoundaryViolationException("Es una raiz");
		}
		return aux.getPadre();
		
	}
	/**
	 * Devuelve una colección iterable de los hijos del nodo correspondiente a una posición dada.
	 * @param v Posición de un nodo.
	 * @return Colección iterable de los hijos del nodo correspondiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		PositionList<Position<E>> lhijos=new ListaDoblementeEnlazada<Position<E>>();
		TNodo<E> aux=checkPosition(v);
		for(TNodo<E> hijos:aux.getHijos()) {
			lhijos.addLast(hijos);
		}
		return lhijos;
		}
	/**
	 * Consulta si una posición corresponde a un nodo interno.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return !aux.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a un nodo externo.
	 * @param v Posición de un nodo.
	 * @return Verdadero si la posición pasada por parámetro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return aux.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posición dada corresponde a la raíz del árbol.
	 * @param v Posición de un nodo.
	 * @return Verdadero, si la posición pasada por parámetro corresponde a la raíz del árbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return aux==raiz;
	}
	/**
	 * Crea un nodo con rótulo e como raíz del árbol.
	 * @param  e Rótulo que se asignará a la raíz del árbol.
	 * @throws InvalidOperationException si el árbol ya tiene un nodo raíz.
	 */
	public void createRoot(E e) throws InvalidOperationException{
		if (raiz!=null) {
			throw new InvalidOperationException("El arbol ya tiene una raiz");
		}
		TNodo<E> nuevo=new TNodo<E>(e);
		raiz=nuevo;
		size++;
	}
	
	/**
	 * Agrega un nodo con rótulo e como primer hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws	InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(p);
		TNodo<E>nuevo=new TNodo<E>(e);
		nuevo.setParent(aux);
		aux.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}
	/**
	 * Agrega un nodo con rótulo e como útimo hijo de un nodo dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o el árbol está vacío.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(p);
		TNodo<E>nuevo=new TNodo<E>(e);
		nuevo.setParent(aux);
		aux.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará delante de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param rb Posición del nodo que será el hermano derecho del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición rb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(p);
		TNodo<E> auxr=checkPosition(rb);
		TNodo<E>nuevo=new TNodo<E>(e);
		nuevo.setParent(aux);
		PositionList<TNodo<E>> lhijos=aux.getHijos();
		if(lhijos.isEmpty() || auxr==raiz) {
			throw new InvalidPositionException("INvalida ");
		}
		try {
			
			Position<TNodo<E>>pos=lhijos.first();
			while(pos.element()!=auxr && pos!=null) {
				if(pos==lhijos.last()) {
					p=null;
				}
				else
				pos=lhijos.next(pos);
			}
			if (pos==null) {
				throw new InvalidPositionException("Posicion invalida");
			}
			lhijos.addBefore(pos, nuevo);
			size++;
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return nuevo;
		
	}
	
	/**
	 * Agrega un nodo con rótulo e como hijo de un nodo padre dado. El nuevo nodo se agregará a continuación de otro nodo también dado.
	 * @param e Rótulo del nuevo nodo.
	 * @param p Posición del nodo padre.
	 * @param lb Posición del nodo que será el hermano izquierdo del nuevo nodo.
	 * @return La posición del nuevo nodo creado.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida, o el árbol está vacío, o la posición lb no corresponde a un nodo hijo del nodo referenciado por p.
	 */
	public Position<E> addAfter (Position<E> p, Position<E> lb, E e) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(p);
		TNodo<E> auxr=checkPosition(lb);
		TNodo<E>nuevo=new TNodo<E>(e);
		nuevo.setParent(aux);
		PositionList<TNodo<E>> lhijos=aux.getHijos();
		if(lhijos.isEmpty() || auxr==raiz) {
			throw new InvalidPositionException("INvalida ");
		}
		try {
			
			Position<TNodo<E>>pos=lhijos.last();
			while(pos.element()!=auxr && pos!=null) {
				if(pos==lhijos.first()) {
					pos=null;
				}
				else
				pos=lhijos.prev(pos);
			}
			if (pos==null) {
				throw new InvalidPositionException("Posicion invalida");
			}
			lhijos.addAfter(pos, nuevo);
			size++;
			
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return nuevo;
	}
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo externo. 
	 * @param p Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo externo, o el árbol está vacío.
	 */
	public void removeExternalNode (Position<E> p) throws InvalidPositionException{
		if(isInternal(p)){
			throw new InvalidPositionException("");
		}
		try {
			TNodo<E> nodo = checkPosition(p);
			if(size==1) {
				raiz=null;
			}
			else {
				TNodo<E> padre=nodo.getPadre();
				PositionList<TNodo<E>> hermanos=padre.getHijos();
				boolean es=false; Position<TNodo<E>> pos=hermanos.first();
				while (!es) {
					if(pos.element()==nodo || pos==hermanos.last()) {
						es=true;
					}
					else {
						pos=hermanos.next(pos);
					}
				}
				hermanos.remove(pos);
			
				
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		size--;
	
		}
	/**
	 * Elimina el nodo referenciado por una posición dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol,  únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param p Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o no corresponde a un nodo interno o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeInternalNode(Position<E> p) throws InvalidPositionException
	{
		if(isExternal(p))
			throw new InvalidPositionException("El nodo es externo");
		
		TNodo<E> nodo = checkPosition(p);
		try {
			if(nodo==raiz) {
				if(nodo.getHijos().size()>1) {
					throw new InvalidPositionException("");
				}
				if(nodo.getHijos().size()==1) {
					raiz=nodo.getHijos().remove(nodo.getHijos().first());
					raiz.setParent(null);
				}
			}
			else {
				TNodo<E> padre=nodo.getPadre();
				PositionList<TNodo<E>> hermanos=padre.getHijos();
				PositionList<TNodo<E>> hijos=nodo.getHijos();
				boolean es=false; Position<TNodo<E>> pos=hermanos.first();
				while (!es) {
					if(pos.element()==nodo || pos==hermanos.last()) {
						es=true;
					}
					else {
						pos=hermanos.next(pos);
					}
				}
				for(TNodo<E> hijo:hijos) {
					hermanos.addBefore(pos, hijo);
					hijo.setParent(padre);
				}
				hermanos.remove(pos);
				
				
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			throw new InvalidPositionException("");
		}
		size--;
	}
	/**
	 * Elimina el nodo referenciado por una posición dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la raíz del árbol,  únicamente podrá ser eliminado si tiene un solo hijo, el cual lo reemplazará.
	 * @param p Posición del nodo a eliminar.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o corresponde a la raíz (con más de un hijo), o el árbol está vacío.
	 */
	public void removeNode(Position<E> p) throws InvalidPositionException
	{
		TNodo<E> pos = checkPosition(p);
		
		if (isInternal(pos))
			removeInternalNode(pos);
		else
			removeExternalNode(pos);
	}
	

	
			
	
	
}























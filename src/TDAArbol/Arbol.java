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
	 * Consulta la cantidad de nodos en el �rbol.
	 * @return Cantidad de nodos en el �rbol.
	 */
	public int size() {
		return size;
	}

	/**
	 * Consulta si el �rbol est� vac�o.
	 * @return Verdadero si el �rbol est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size==0;
	}
	/**
	 * Devuelve un iterador de los elementos almacenados en el �rbol en preorden.
	 * @return Iterador de los elementos almacenados en el �rbol.
	 */
	public Iterator<E> iterator(){
		PositionList<E> l = new ListaDoblementeEnlazada<E>();
		for (Position<E> p: positions())
			l.addLast(p.element());
		
		return l.iterator();
	}
	/**
	 * Devuelve una colecci�n iterable de las posiciones de los nodos del �rbol.
	 * @return Colecci�n iterable de las posiciones de los nodos del �rbol.
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
	 * Reemplaza el elemento almacenado en la posici�n dada por el elemento pasado por par�metro. Devuelve el elemento reemplazado.
	 * @param v Posici�n de un nodo.
	 * @param e Elemento a reemplazar en la posici�n pasada por par�metro.
	 * @return Elemento reemplazado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
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
	 * Devuelve la posici�n de la ra�z del �rbol.
	 * @return Posici�n de la ra�z del �rbol.
	 * @throws EmptyTreeException si el �rbol est� vac�o.
	 */
	public Position<E> root() throws EmptyTreeException{
		if(isEmpty()) {
			throw new EmptyTreeException("Arbol Vacio");
		}
		return raiz;
	}
	
	/**
	 * Devuelve la posici�n del nodo padre del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Posici�n del nodo padre del nodo correspondiente a la posici�n dada.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 * @throws BoundaryViolationException si la posici�n pasada por par�metro corresponde a la ra�z del �rbol.
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
	 * Devuelve una colecci�n iterable de los hijos del nodo correspondiente a una posici�n dada.
	 * @param v Posici�n de un nodo.
	 * @return Colecci�n iterable de los hijos del nodo correspondiente a la posici�n pasada por par�metro.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
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
	 * Consulta si una posici�n corresponde a un nodo interno.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo interno, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return !aux.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posici�n dada corresponde a un nodo externo.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero si la posici�n pasada por par�metro corresponde a un nodo externo, falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return aux.getHijos().isEmpty();
	}
	
	/**
	 * Consulta si una posici�n dada corresponde a la ra�z del �rbol.
	 * @param v Posici�n de un nodo.
	 * @return Verdadero, si la posici�n pasada por par�metro corresponde a la ra�z del �rbol,falso en caso contrario.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException{
		if(isEmpty()) {
			throw new InvalidPositionException("Posicion invalida");
		}
		TNodo<E> aux=checkPosition(v);
		return aux==raiz;
	}
	/**
	 * Crea un nodo con r�tulo e como ra�z del �rbol.
	 * @param  e R�tulo que se asignar� a la ra�z del �rbol.
	 * @throws InvalidOperationException si el �rbol ya tiene un nodo ra�z.
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
	 * Agrega un nodo con r�tulo e como primer hijo de un nodo dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
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
	 * Agrega un nodo con r�tulo e como �timo hijo de un nodo dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o el �rbol est� vac�o.
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
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� delante de otro nodo tambi�n dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param rb Posici�n del nodo que ser� el hermano derecho del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n rb no corresponde a un nodo hijo del nodo referenciado por p.
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
	 * Agrega un nodo con r�tulo e como hijo de un nodo padre dado. El nuevo nodo se agregar� a continuaci�n de otro nodo tambi�n dado.
	 * @param e R�tulo del nuevo nodo.
	 * @param p Posici�n del nodo padre.
	 * @param lb Posici�n del nodo que ser� el hermano izquierdo del nuevo nodo.
	 * @return La posici�n del nuevo nodo creado.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida, o el �rbol est� vac�o, o la posici�n lb no corresponde a un nodo hijo del nodo referenciado por p.
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
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo externo. 
	 * @param p Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo externo, o el �rbol est� vac�o.
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
	 * Elimina el nodo referenciado por una posici�n dada, si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o no corresponde a un nodo interno o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
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
	 * Elimina el nodo referenciado por una posici�n dada. Si se trata de un nodo interno. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * Si el nodo a eliminar es la ra�z del �rbol,  �nicamente podr� ser eliminado si tiene un solo hijo, el cual lo reemplazar�.
	 * @param p Posici�n del nodo a eliminar.
	 * @throws InvalidPositionException si la posici�n pasada por par�metro es inv�lida o corresponde a la ra�z (con m�s de un hijo), o el �rbol est� vac�o.
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























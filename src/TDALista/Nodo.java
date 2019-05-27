package TDALista;
import Extras.*;
/**
 * Clase Nodo
 * @author Julian Acttis y Axel Fontana
 *
 * @param <E>
 */
public class Nodo<E> implements Position<E> {
	E element;
	Nodo<E> next,prev;
	
	public Nodo(E e,Nodo<E> next, Nodo<E> prev){
		element=e;
		this.next=next;
		this.prev=prev;
		
	}
	/**
	 * Consulta el elemento del nodo
	 * @return El elemento del nodo
	 */
	public E element() {
		return element;
	}
	/**
	 * Consulta el nodo siguiente al actual 
	 * @return El nodo siguiente al actual
	 */
	public Nodo<E> getNext(){
		return next;
	}
	/**
	 * Consulta el nodo anterior al actual
	 * @return El nodo anterior al actual
	 */
	public Nodo<E> getPrev(){
		return prev;
	}
	/**
	 * Establece el nodo siguiente al actual
	 * @param n
	 */
	public void setNext(Nodo<E> n) {
		next=n;
	}
	/**
	 * Establece el nodo anterior al actual
	 * @param p
	 */
	public void setPrev(Nodo<E> p) {
		prev=p;
	}
	/**
	 * Establece el elemento de un nodo
	 * @param e
	 */
	public void setElement(E e) {
		element=e;
	}
	
	
	
	
	
	
	
}

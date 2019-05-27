package TDAPila;
/**
 * Clase Nodo
 * @author Julian Acttis y Axel Fontana
 *
 * @param <E>
 */
public class Nodo<E> {
	private E elemento;
	private Nodo<E> siguiente;
	
	public Nodo() {
		this(null, null); 
	
	}
	public Nodo( E item ) {
		this(item,null);
	
	}
	public Nodo( E item, Nodo<E> sig ){
		elemento=item; siguiente=sig;
	
	}
	
	/**
	 * Establece el elemento del nodo
	 * @param elemento
	 */
	public void setElemento( E elemento ){
		this.elemento=elemento;
	
	}
	/**
	 * Establece el nodo siguiente al actual
	 * 
	 * @param siguiente
	 */
	public void setSiguiente( Nodo<E> siguiente ) {
		this.siguiente = siguiente;
	
	}
	
	/**
	 * Consulta el elemento del nodo
	 * @return El elemento del nodo 
	 */
	public E getElemento(){
		return elemento;
	
	}
	/**
	 * Consulta el nodo siguiente al actual
	 * @return El siguiente nodo al actual
	 */
	public Nodo<E> getSiguiente(){
		return siguiente;
	
	} 
}

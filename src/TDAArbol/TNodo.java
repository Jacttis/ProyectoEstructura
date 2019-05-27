package TDAArbol;
import TDALista.*;
import Extras.*;
/**
 * Clase TNodo 
 *
 * @author Julian Acttis y Axel Fontana
 *
 * @param <E>
 */
public class TNodo<E> implements Position<E> {
	TNodo<E> padre;
	PositionList<TNodo<E>> hijos;
	E elemento;
	
	public TNodo()
	{
		padre=null;
		elemento=null;
		hijos = new ListaDoblementeEnlazada<TNodo<E>>();
	}
	
	public TNodo(E elem)
	{
		padre=null;
		elemento=elem;
		hijos = new ListaDoblementeEnlazada<TNodo<E>>();
	}
	
	public TNodo(E elem,TNodo<E> p)
	{
		elemento = elem;
		padre = p;
		hijos = new ListaDoblementeEnlazada<TNodo<E>>();
	}
	/**
	 * Devuelve el elemento del nodo actual
	 */
	public E element() {
		return elemento;
	}
	/**
	 * Establece el padre del nodo actual
	 * @param p
	 */
	public void setParent(TNodo<E> p) {
		padre=p;
	}
	/**
	 * Consulta los hijos del nodo actual
	 * @return Una lista con los hijos del nodo actual
	 */
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}
	/**
	 * Establece el elemento del nodo actual
	 * @param elem
	 */
	public void setElemento(E elem) {
		elemento=elem;
	}
	/**
	 * Consulta el padre del nodo actual
	 * @return el nodo del padre del nodo actual
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	



}

package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;
import Excepciones.*;
import Extras.*;



public class ElementIterator<E> implements Iterator<E> {

	private PositionList<E> l;
	private Position<E> p ;
	
	public ElementIterator(PositionList<E> lista) {
		try{
			l = lista;
			p = lista.isEmpty() ? null :  lista.first();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Consulta si el iterator tiene mas elementos 
	 */
	public boolean hasNext() {
		return p!=null;
	}

	/**
	 * Devuelve el siguiente elemento en la iteracion
	 */
	public E next() throws NoSuchElementException {
		E ret = null;
		try{
			if (p==null)
				throw new NoSuchElementException();
			ret = p.element();
			p = l.last() == p ? null : l.next(p);
		}catch(BoundaryViolationException | EmptyListException | InvalidPositionException e){}
		
		return ret;
	}

}
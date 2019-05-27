package TDAColaCP;
import Excepciones.*;
import java.util.Comparator;
/**
 * Clase Heap
 * @author Julian Acttis y Axel Fontana
 *
 * @param <K>
 * @param <V>
 */
public class Heap<K,V> implements PriorityQueue<K,V>{
	protected Entrada<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size;

	public Heap( Comparator<K> comp ) {
		
		elems = (Entrada<K,V> []) new Entrada[10000];
		this.comp = comp;
		size = 0;
	}
	/**
	 * Consulta la cantidad de elementos de la cola.
	 * @return Cantidad de elementos de la cola.
	 */	
	public int size() {
		return size;
	}
	/**
	 * Consulta si la cola est� vac�a.
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
	public Entry<K,V> min() throws EmptyPriorityQueueException{
	if (isEmpty())
		throw new EmptyPriorityQueueException("");
	return elems[1];
	
	}
	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave es inv�lida.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if(key==null) {
			throw new InvalidKeyException("");
		}
		Entrada<K,V> entrada = new Entrada<K,V>(key, value);
		
		elems[++size] = entrada;
		
		int i = size; 
		boolean seguir = true;
		while ( i>1 && seguir ) {
			Entrada <K,V> elemActual = elems[i];
			Entrada <K,V> elemPadre = elems[i/2];
			if( comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
				Entrada<K,V> aux = elems[i];
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i /= 2;
			} else 
				seguir = false;
		}
		return entrada;
	}
	/**
	 * Remueve y devuelve la entrada con menor prioridad de la cola.
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola est� vac�a.
	 */
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> entrada = min();
		
		if( size == 1 ) { 
			elems[1] = null; size = 0; return entrada;
		}
		else {
		
			elems[1] = elems[size]; elems[size] = null; size--;
			
			int i = 1; 
			boolean seguir = true;
			while ( seguir ) {
				
				int hi = i*2; int hd = i*2+1;
				boolean tieneHijoIzquierdo = hi <= size(); boolean tieneHijoDerecho = hd <= size();
				if( !tieneHijoIzquierdo ) seguir = false;
				else {
					int m ; 
					if( tieneHijoDerecho ) {
					
						if( comp.compare( elems[hi].getKey(), elems[hd].getKey()) < 0 ) m = hi;
						else m = hd;
					} else m = hi;
				
				
				if( comp.compare(elems[i].getKey(), elems[m].getKey()) > 0 ) {
					Entrada<K,V> aux = elems[i];
					elems[i] = elems[m];
					elems[m] = aux;
					i = m; 
				} else seguir = false;
			}}
		}
		return entrada;
	}


}	

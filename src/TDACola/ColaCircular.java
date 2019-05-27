package TDACola;
import Excepciones.*;
/**
 * Clase ColaCircular
 * @author Julian Acttis y Axel Fontana
 *
 * @param <E>
 */
public class ColaCircular<E> implements Queue<E>{
	protected int Inicio,Final;
	protected E Cola [];
	protected static final int longitud=10;
	
	public ColaCircular() {
		Inicio=0;
		Final=0;
		Cola=(E []) new Object[longitud];;
	}
	

	/**
	 * Devuelve la cantidad de elementos en la cola.
	 * @return Cantidad de elementos en la cola.
	 */
	public int size() {
		return ((Cola.length-Inicio+Final)% Cola.length);
	}

	/**
	 * Consulta si la cola est� vac�a.
	 * @return Verdadero si la cola est� vac�a, falso en caso contrario.
	 */
	public boolean isEmpty() {
		return Final==Inicio;
	}
	
	/**
	 * Inspecciona el elemento que se encuentra en el frente de la cola.
	 * @return Elemento que se encuentra en el frente de la cola.
	 * @throws EmptyQueueException si la cola est� vac�a.
	 */
	public E front() throws EmptyQueueException{
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia");
		}
		return Cola[Inicio];
	}
	
	/**
	 * Inserta un elemento en el fondo de la cola.
	 * @param element Nuevo elemento a insertar.
	 */
	public void enqueue(E element) {
		if(Cola.length-1==size()){
			
			E[] aux=copiar(Inicio);
			Final=size();
			Inicio=0;
			Cola=aux;
		}
		Cola[Final]=element;
		Final=(Final+1)%Cola.length;
		
	}
	private E[] copiar(int m) {
		E[] aux=(E[])new Object[2*Cola.length];
		for(int j=0;j<size();j++) {
			aux[j]=Cola[m];
			Cola[m]=null;
			m=(m+1)%Cola.length;
		}
	return aux;
	}
	
	/**
	 * Remueve el elemento en el frente de la cola.
	 * @return Elemento removido.
	 * @throws EmptyQueueException si la cola est� vac�a.
	 */
	public E dequeue() throws EmptyQueueException{
		if (isEmpty()) {
			throw new EmptyQueueException("Cola Vacia");
		}
		E aux =Cola[Inicio];
		Cola[Inicio]=null;
		Inicio=(Inicio+1)%Cola.length;
		return aux;
		
		
		
	}
	
	
	
}

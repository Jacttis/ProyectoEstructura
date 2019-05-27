package TDAColaCP;
/**
 * Clase Entrada
 * @author Julian Acttis y Axel Fontana
 *
 * @param <K>
 * @param <V>
 */
public class Entrada<K,V> implements Entry<K,V> {
	private K clave;
	private V valor;
	public Entrada(K k, V v) { 
		clave = k; valor = v; 
	}
	/**
	 * Consulta la clave de la Entrada
	 */
	public K getKey() { 
		return clave; 
	}
	/**
	 * Consulta el valor de la Entrada
	 */
	public V getValue() { 
		return valor;
	}
	/**
	 * Establece la clave de la Entrada
	 * @param k
	 */
	public void setKey( K k ) {
		clave = k; 
	}
	/**
	 * Establece el valor de la Entrada
	 * @param v
	 */
	public void setValue(V v) {
		valor = v; 
	}
}
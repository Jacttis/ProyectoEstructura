package TDAColaCP;

public class Comparador<E  extends Comparable<E>>	implements java.util.Comparator<E> {
	/**
	 * Compara dos elementos y devuelve un numero positivo si el primer elemento es mayor al segundo,
	 * devuelve un numero negativo si el primer elemento es menor al segundo o 0 si son iguales
	 */
	public int compare( E a, E b ) {
	return a.compareTo( b );
	}
}

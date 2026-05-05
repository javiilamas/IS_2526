package es.unican.is2.practica5b;

import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;
/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public boolean add(E elemento) {
	    if (elemento == null) throw new NullPointerException(); // Correcto [2]
	    
	    int indice = 0;
	    if (lista.size() != 0) {
	        while (indice < lista.size()) {
	            int comp = elemento.compareTo(lista.get(indice));
	            
	            // Defecto 2: Si ya existe, no se añade y retorna false
	            if (comp == 0) {
	                return false; 
	            }
	            
	            // Defecto 1 corregido: Cambiar < 0 por > 0 para orden natural
	            if (comp > 0) {
	                indice++;
	            } else {
	                break;
	            }
	        }
	    }
	    lista.add(indice, elemento);
	    return true;
	}
	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		lista.clear();
	}

}
package es.unican.is2.seguroscommon;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
public class Cliente {

    private String dni;
    private String nombre;
    private boolean minusvalia;
    private List<Seguro> seguros = new LinkedList<>();

    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean getMinusvalia() {
        return minusvalia;
    }

    public void setMinusvalia(boolean minusvalia) {
        this.minusvalia = minusvalia;
    }

    /**
     * Calcula el total a pagar por el cliente por
     * todos los seguros a su nombre
     */
    public double totalSeguros() {
        double total = 0.0;
        for (Seguro s : seguros) {
            total += s.precio();
        }
        if (minusvalia) {
            total = total * 0.75;
        }
        return total;
    }
}

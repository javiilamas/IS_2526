import java.util.ArrayList;

/**
 * Métricas iniciales de la clase Conductor.
 *
 * Métodos considerados: constructor, dni, getDni, getNombre, getApellido1,
 * apellido2, getDire, sueldo y anhadeTransporte.
 *
 * WMC = suma de la complejidad ciclomática de todos los métodos.
 * WMC = constructor(5) + dni(1) + getDni(1) + getNombre(1)
 *     + getApellido1(1) + apellido2(1) + getDire(1)
 *     + sueldo(6) + anhadeTransporte(1) = 18
 *
 * WMCn = 18 / 9 = 2.00
 *
 * CCog = constructor(2) + sueldo(7) = 9
 * El resto de métodos son getters o métodos secuenciales, por tanto CCog = 0.
 *
 * CCogn = 9 / 9 = 1.00
 *
 * CBO = 1: Transporte.
 * DIT = 0.
 * NOC = 0.
 */
public class Conductor {

	
	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		// WMC constructor:
	    // CC = 1 base + 1 por el if + 3 por los operadores || adicionales = 5
	    //
	    // CCog constructor:
	    // if: +1
	    // secuencia de operadores ||: +1
	    // CCog = 2
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String dni() {
		return dni;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String apellido2() {
		return apellido2;
	}

	public String getDire() {
		return dire;
	}

	public double sueldo() {

	    // WMC sueldo:
	    // CC = 1 base + 1 por el for + 3 por los case del switch + 1 por el if = 6
	    //
	    // CCog sueldo:
	    // for: +1
	    // switch dentro del for: +2
	    // if dentro del for y del switch: +3
	    // else: +1
	    // CCog = 7

	    
		
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) {
				case Mercancias:
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas:
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas:
					if (t.getPersonas() < 10)
						sueldoExtraTransporte = t.horas() * 0.5;
					else
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}

}

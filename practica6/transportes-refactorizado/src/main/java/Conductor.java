import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado.
 *
 * Refactorizaciones aplicadas:
 * - Move Method: el calculo del extra concreto de cada transporte se mueve a Transporte.
 * - Replace Conditional with Polymorphism: desaparece el switch por categoria.
 * - Replace Magic Number with Symbolic Constant.
 *
 * Metricas finales:
 * Metodos considerados: constructor, dni, getDni, getNombre, getApellido1,
 * apellido2, getDire, sueldo y anhadeTransporte.
 * WMC = constructor(5) + dni(1) + getDni(1) + getNombre(1)
 *     + getApellido1(1) + apellido2(1) + getDire(1)
 *     + sueldo(2) + anhadeTransporte(1) = 14
 * WMCn = 14 / 9 = 1.56
 * CCog = constructor(2) + sueldo(1) = 3
 * CCogn = 3 / 9 = 0.33
 * CBO = 1: Transporte
 * DIT = 0
 * NOC = 0
 */
public class Conductor {

	private static final double SUELDO_BASE = 700.0;

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		// WMC constructor:
		// CC = 1 base + 1 por if + 3 por operadores || adicionales = 5
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
		// CC = 1 base + 1 por for = 2
		//
		// CCog sueldo:
		// for: +1
		// CCog = 1
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {
			sueldoTransportes += t.extra();
		}
		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}
}

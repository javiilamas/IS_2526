/**
 * Clase abstracta que representa un transporte realizado por un conductor.
 *
 * Refactorizaciones aplicadas:
 * - Extract SuperClass: Transporte pasa a ser la clase base comun.
 * - Move Method: parte del calculo del sueldo se mueve desde Conductor a Transporte.
 * - Replace Magic Number with Symbolic Constant.
 *
 * Metricas finales:
 * Metodos considerados: constructor, horas y extra.
 * WMC = constructor(2) + horas(1) + extra(1) = 4
 * WMCn = 4 / 3 = 1.33
 * CCog = constructor(1) + horas(0) + extra(0) = 1
 * CCogn = 1 / 3 = 0.33
 * CBO = 0
 * DIT = 0
 * NOC = 2
 */
public abstract class Transporte {

	private static final double EXTRA_BASE_HORA = 5.0;

	private double horas;

	public Transporte(double horas) {
		// WMC constructor:
		// CC = 1 base + 1 por if = 2
		//
		// CCog constructor:
		// if: +1
		// CCog = 1
		if (horas <= 0) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}

	public double horas() {
		return horas;
	}

	public double extra() {
		return horas * EXTRA_BASE_HORA + extraAdicional();
	}

	protected abstract double extraAdicional();
}

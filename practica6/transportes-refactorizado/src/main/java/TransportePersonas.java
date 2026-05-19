/**
 * Transporte de personas.
 *
 * Refactorizaciones aplicadas:
 * - Extract SubClass.
 * - Replace Conditional with Polymorphism.
 * - Replace Magic Number with Symbolic Constant.
 *
 * Metricas finales:
 * Metodos considerados: constructor, getPersonas y extraAdicional.
 * WMC = constructor(2) + getPersonas(1) + extraAdicional(2) = 5
 * WMCn = 5 / 3 = 1.67
 * CCog = constructor(1) + getPersonas(0) + extraAdicional(1) = 2
 * CCogn = 2 / 3 = 0.67
 * CBO = 1: Transporte
 * DIT = 1
 * NOC = 0
 */
public class TransportePersonas extends Transporte {

	private static final int LIMITE_COLECTIVO = 10;
	private static final double EXTRA_NO_COLECTIVO = 0.5;
	private static final double EXTRA_COLECTIVO = 1.0;

	private int personas;

	public TransportePersonas(double horas, int personas) {
		super(horas);
		// WMC constructor:
		// CC = 1 base + 1 por if = 2
		//
		// CCog constructor:
		// if: +1
		// CCog = 1
		if (personas <= 0) {
			throw new IllegalArgumentException();
		}
		this.personas = personas;
	}

	public int getPersonas() {
		return personas;
	}

	@Override
	protected double extraAdicional() {
		// WMC extraAdicional:
		// CC = 1 base + 1 por if = 2
		//
		// CCog extraAdicional:
		// if: +1
		// CCog = 1
		if (personas < LIMITE_COLECTIVO) {
			return horas() * EXTRA_NO_COLECTIVO;
		}
		return horas() * EXTRA_COLECTIVO;
	}
}

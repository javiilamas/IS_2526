/**
 * Transporte de mercancias.
 *
 * Refactorizaciones aplicadas:
 * - Extract SubClass.
 * - Replace Conditional with Polymorphism.
 * - Replace Magic Number with Symbolic Constant.
 *
 * Metricas finales:
 * Metodos considerados: constructor, ton y extraAdicional.
 * WMC = constructor(2) + ton(1) + extraAdicional(1) = 4
 * WMCn = 4 / 3 = 1.33
 * CCog = constructor(1) + ton(0) + extraAdicional(0) = 1
 * CCogn = 1 / 3 = 0.33
 * CBO = 1: Transporte
 * DIT = 1
 * NOC = 1
 */
public class TransporteMercancias extends Transporte {

	private static final double EXTRA_POR_TONELADA = 2.0;

	private int ton;

	public TransporteMercancias(double horas, int toneladas) {
		super(horas);
		// WMC constructor:
		// CC = 1 base + 1 por if = 2
		//
		// CCog constructor:
		// if: +1
		// CCog = 1
		if (toneladas <= 0) {
			throw new IllegalArgumentException();
		}
		this.ton = toneladas;
	}

	public int ton() {
		return ton;
	}

	@Override
	protected double extraAdicional() {
		return ton * EXTRA_POR_TONELADA;
	}
}

/**
 * Transporte de mercancias peligrosas.
 *
 * Refactorizaciones aplicadas:
 * - Extract SubClass.
 * - Reuse Method: reutiliza el calculo de TransporteMercancias.
 * - Replace Magic Number with Symbolic Constant.
 *
 * Metricas finales:
 * Metodos considerados: constructor y extraAdicional.
 * WMC = constructor(1) + extraAdicional(1) = 2
 * WMCn = 2 / 2 = 1.00
 * CCog = constructor(0) + extraAdicional(0) = 0
 * CCogn = 0 / 2 = 0.00
 * CBO = 1: TransporteMercancias
 * DIT = 2
 * NOC = 0
 */
public class TransporteMercanciasPeligrosas extends TransporteMercancias {

	private static final double EXTRA_MERCANCIAS_PELIGROSAS = 50.0;

	public TransporteMercanciasPeligrosas(double horas, int toneladas) {
		super(horas, toneladas);
	}

	@Override
	protected double extraAdicional() {
		return super.extraAdicional() + EXTRA_MERCANCIAS_PELIGROSAS;
	}
}

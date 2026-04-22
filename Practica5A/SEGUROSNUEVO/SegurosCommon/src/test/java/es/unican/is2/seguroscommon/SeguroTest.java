package es.unican.is2.seguroscommon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SeguroTest {

    private static final double DELTA = 0.001;

    @Test
    public void precioSeguroNoVigente() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS, 15, LocalDate.now().plusDays(10));

        assertEquals(0.0, sut.precio(), DELTA);
    }

    @Test
    public void precioTercerosSinRecargosNiDescuentos() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS, 89, LocalDate.now().minusYears(2));

        assertEquals(400.0, sut.precio(), DELTA);
    }

    @Test
    public void precioTercerosPotenciaLimiteInferiorTramoMedio() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS, 90, LocalDate.now().minusYears(2));

        assertEquals(420.0, sut.precio(), DELTA);
    }

    @Test
    public void precioTercerosLunasPotenciaLimiteSuperiorTramoMedio() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS_LUNAS, 110, LocalDate.now().minusYears(2));

        assertEquals(630.0, sut.precio(), DELTA);
    }

    @Test
    public void precioTercerosLunasPotenciaSuperiorTramoAlto() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS_LUNAS, 111, LocalDate.now().minusYears(2));

        assertEquals(720.0, sut.precio(), DELTA);
    }

    @Test
    public void precioTodoRiesgoDurantePrimerAnio() {
        Seguro sut = crearSeguro(Cobertura.TODO_RIESGO, 20, LocalDate.now().minusMonths(6));

        assertEquals(800.0, sut.precio(), DELTA);
    }

    @Test
    public void precioExactamenteUnAnioDeAntiguedadSinDescuento() {
        Seguro sut = crearSeguro(Cobertura.TERCEROS, 15, LocalDate.now().minusYears(1));

        assertEquals(400.0, sut.precio(), DELTA);
    }

    private Seguro crearSeguro(Cobertura cobertura, int potencia, LocalDate fechaInicio) {
        Seguro seguro = new Seguro();
        seguro.setCobertura(cobertura);
        seguro.setPotencia(potencia);
        seguro.setFechaInicio(fechaInicio);
        return seguro;
    }
}

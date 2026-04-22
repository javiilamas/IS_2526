package es.unican.is2.seguroscommon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteTest {

    private static final double DELTA = 0.001;

    @Test
    public void totalSegurosListaVaciaSinMinusvalia() {
        Cliente sut = new Cliente();
        sut.setMinusvalia(false);

        assertEquals(0.0, sut.totalSeguros(), DELTA);
    }

    @Test
    public void totalSegurosVariosSegurosSinMinusvalia() {
        Cliente sut = new Cliente();
        sut.getSeguros().add(new SeguroStub(100.0));
        sut.getSeguros().add(new SeguroStub(250.0));
        sut.getSeguros().add(new SeguroStub(50.0));
        sut.setMinusvalia(false);

        assertEquals(400.0, sut.totalSeguros(), DELTA);
    }

    @Test
    public void totalSegurosVariosSegurosConMinusvalia() {
        Cliente sut = new Cliente();
        sut.getSeguros().add(new SeguroStub(100.0));
        sut.getSeguros().add(new SeguroStub(250.0));
        sut.getSeguros().add(new SeguroStub(50.0));
        sut.setMinusvalia(true);

        assertEquals(300.0, sut.totalSeguros(), DELTA);
    }

    private static class SeguroStub extends Seguro {

        private final double precio;

        SeguroStub(double precio) {
            this.precio = precio;
        }

        @Override
        public double precio() {
            return precio;
        }
    }
}

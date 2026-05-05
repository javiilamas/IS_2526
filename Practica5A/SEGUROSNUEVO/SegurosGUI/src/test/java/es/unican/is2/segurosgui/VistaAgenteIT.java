package es.unican.is2.segurosgui;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.segurosbusiness.GestionSeguros;
import es.unican.is2.seguroscommon.DataAccessException;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.IInfoSeguros;
import es.unican.is2.seguroscommon.ISegurosDAO;
import es.unican.is2.segurosdaoh2.ClientesDAO;
import es.unican.is2.segurosdaoh2.SegurosDAO;

public class VistaAgenteIT {

    private FrameFixture window;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        IInfoSeguros info = new GestionSeguros(daoClientes, daoSeguros);
        abrirVentana(info);
    }

    @AfterEach
    public void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    public void consultaClienteExistenteConVariosSeguros() {
        GuiActionRunner.execute(() -> {
            window.textBox("txtDNICliente").target().setText("11111111A");
            window.button("btnBuscar").target().doClick();
        });

        window.textBox("txtNombreCliente").requireText("Juan");
        window.textBox("txtTotalCliente").requireText("1820.0");
        window.list("listSeguros").requireItemCount(3);
        assertThat(window.list("listSeguros").contents()).containsExactlyInAnyOrder(
                "1111AAA TERCEROS",
                "1111BBB TODO_RIESGO",
                "1111CCC TERCEROS");
    }

    @Test
    public void consultaClienteExistenteSinSeguros() {
        GuiActionRunner.execute(() -> {
            window.textBox("txtDNICliente").target().setText("33333333A");
            window.button("btnBuscar").target().doClick();
        });

        window.textBox("txtNombreCliente").requireText("Luis");
        window.textBox("txtTotalCliente").requireText("0.0");
        window.list("listSeguros").requireItemCount(0);
    }

    @Test
    public void consultaClienteInexistente() {
        GuiActionRunner.execute(() -> {
            window.textBox("txtDNICliente").target().setText("99999999Z");
            window.button("btnBuscar").target().doClick();
        });

        window.textBox("txtNombreCliente").requireText("DNI No Válido");
        window.textBox("txtTotalCliente").requireText("");
        window.list("listSeguros").requireItemCount(0);
    }

    @Test
    public void consultaClienteConErrorDeAccesoADatos() {
        window.cleanUp();

        abrirVentana(new IInfoSeguros() {
            @Override
            public es.unican.is2.seguroscommon.Cliente cliente(String dni) throws DataAccessException {
                throw new DataAccessException();
            }

            @Override
            public es.unican.is2.seguroscommon.Seguro seguro(String matricula) throws DataAccessException {
                throw new DataAccessException();
            }
        });

        GuiActionRunner.execute(() -> {
            window.textBox("txtDNICliente").target().setText("11111111A");
            window.button("btnBuscar").target().doClick();
        });

        window.textBox("txtNombreCliente").requireText("Error BBDD");
        window.textBox("txtTotalCliente").requireText("");
        window.list("listSeguros").requireItemCount(0);
    }

    private void abrirVentana(IInfoSeguros info) {
        VistaAgente vista = GuiActionRunner.execute(() -> new VistaAgente(info));
        window = new FrameFixture(vista);
        window.show();
        window.requireVisible();
    }
}
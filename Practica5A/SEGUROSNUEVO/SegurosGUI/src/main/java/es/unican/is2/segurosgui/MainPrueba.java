package es.unican.is2.segurosgui;

import javax.swing.SwingUtilities;

import es.unican.is2.segurosbusiness.GestionSeguros;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.IInfoSeguros;
import es.unican.is2.seguroscommon.ISegurosDAO;
import es.unican.is2.segurosdaoh2.ClientesDAO;
import es.unican.is2.segurosdaoh2.SegurosDAO;

public class MainPrueba {

    public static void main(String[] args) {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        IInfoSeguros info = new GestionSeguros(daoClientes, daoSeguros);

        SwingUtilities.invokeLater(() -> {
            VistaAgente vista = new VistaAgente(info);
            vista.setVisible(true);
        });
    }
}
package es.unican.is2.segurosmain;

import es.unican.is2.segurosbusiness.GestionSeguros;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.ISegurosDAO;
import es.unican.is2.segurosdaoh2.ClientesDAO;
import es.unican.is2.segurosdaoh2.SegurosDAO;
import es.unican.is2.segurosgui.VistaAgente;
/*COMPROBADO QUE FUNCIONALIDAD DEL JAR*/
public class Runner {

    public static void main(String[] args) {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
        VistaAgente vista = new VistaAgente(negocio);
        vista.setVisible(true);
    }
}
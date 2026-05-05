package es.unican.is2.segurosbusiness;

import es.unican.is2.seguroscommon.Cliente;
import es.unican.is2.seguroscommon.DataAccessException;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.IGestionClientes;
import es.unican.is2.seguroscommon.IGestionSeguros;
import es.unican.is2.seguroscommon.IInfoSeguros;
import es.unican.is2.seguroscommon.ISegurosDAO;
import es.unican.is2.seguroscommon.OperacionNoValida;
import es.unican.is2.seguroscommon.Seguro;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;

    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
    }

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        Cliente existente = clientesDAO.cliente(c.getDni());
        if (existente != null) {
            return null;
        }
        return clientesDAO.creaCliente(c);
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
        Cliente cliente = clientesDAO.cliente(dni);
        if (cliente == null) {
            return null;
        }
        if (!cliente.getSeguros().isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros a su nombre");
        }
        return clientesDAO.eliminaCliente(dni);
    }

    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        Cliente cliente = clientesDAO.cliente(dni);
        if (cliente == null) {
            return null;
        }

        Seguro existente = segurosDAO.seguroPorMatricula(s.getMatricula());
        if (existente != null) {
            throw new OperacionNoValida("Ya existe un seguro para esa matrícula");
        }

        Seguro creado = segurosDAO.creaSeguro(s);
        cliente.getSeguros().add(creado);
        clientesDAO.actualizaCliente(cliente);

        return segurosDAO.seguroPorMatricula(creado.getMatricula());
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Cliente cliente = clientesDAO.cliente(dni);
        if (cliente == null) {
            return null;
        }

        Seguro seguro = segurosDAO.seguroPorMatricula(matricula);
        if (seguro == null) {
            return null;
        }

        boolean pertenece = false;
        for (Seguro s : cliente.getSeguros()) {
            if (s.getId() == seguro.getId()) {
                pertenece = true;
                break;
            }
        }

        if (!pertenece) {
            throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
        }

        return segurosDAO.eliminaSeguro(seguro.getId());
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro seguro = segurosDAO.seguroPorMatricula(matricula);
        if (seguro == null) {
            return null;
        }
        seguro.setConductorAdicional(conductor);
        return segurosDAO.actualizaSeguro(seguro);
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        return clientesDAO.cliente(dni);
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return segurosDAO.seguroPorMatricula(matricula);
    }
}
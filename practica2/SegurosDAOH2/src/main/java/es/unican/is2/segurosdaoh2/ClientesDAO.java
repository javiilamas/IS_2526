package es.unican.is2.segurosdaoh2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.seguroscommon.Cliente;
import es.unican.is2.seguroscommon.DataAccessException;
import es.unican.is2.seguroscommon.IClientesDAO;
import es.unican.is2.seguroscommon.Seguro;

public class ClientesDAO implements IClientesDAO {

    @Override
    public Cliente creaCliente(Cliente c) throws DataAccessException {
        String insertStatement = String.format(
                "insert into Clientes(dni, nombre, minusvalia) values ('%s', '%s', '%b')",
                c.getDni(),
                c.getNombre(),
                c.getMinusvalia());
        H2ServerConnectionManager.executeSqlStatement(insertStatement);
        return c;
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        Cliente result = null;
        Connection con = H2ServerConnectionManager.getConnection();
        try (Statement statement = con.createStatement()) {

            String statementText = "select * from Clientes where dni = '" + dni + "'";
            ResultSet results = statement.executeQuery(statementText);

            if (results.next()) {
                result = procesaCliente(con, results);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
        return result;
    }

    @Override
    public Cliente actualizaCliente(Cliente nuevo) throws DataAccessException {
        Cliente cliente;
        Cliente old = cliente(nuevo.getDni());
        String statementText;

        statementText = String.format(
                "update Clientes set nombre = '%s', minusvalia = '%b' where dni = '%s'",
                nuevo.getNombre(), nuevo.getMinusvalia(), nuevo.getDni());
        H2ServerConnectionManager.executeSqlStatement(statementText);

        if (old != null) {
            for (Seguro s : old.getSeguros()) {
                if (!nuevo.getSeguros().contains(s)) {
                    statementText = String.format(
                            "update Seguros set cliente_FK = null where id = %d",
                            s.getId());
                    H2ServerConnectionManager.executeSqlStatement(statementText);
                }
            }
        }

        for (Seguro s : nuevo.getSeguros()) {
            if (old == null || !old.getSeguros().contains(s)) {
                statementText = String.format(
                        "update Seguros set cliente_FK = '%s' where id = %d",
                        nuevo.getDni(), s.getId());
                H2ServerConnectionManager.executeSqlStatement(statementText);
            }
        }

        cliente = cliente(nuevo.getDni());
        return cliente;
    }

    @Override
    public Cliente eliminaCliente(String dni) throws DataAccessException {
        Cliente cliente = cliente(dni);
        String statementText = "delete from Clientes where dni = '" + dni + "'";
        H2ServerConnectionManager.executeSqlStatement(statementText);
        return cliente;
    }

    @Override
    public List<Cliente> clientes() throws DataAccessException {
        List<Cliente> clientes = new LinkedList<>();

        Connection con = H2ServerConnectionManager.getConnection();
        try (Statement statement = con.createStatement()) {

            String statementText = "select dni, nombre, minusvalia from Clientes";
            ResultSet results = statement.executeQuery(statementText);

            while (results.next()) {
                clientes.add(procesaCliente(con, results));
            }

        } catch (SQLException e) {
            throw new DataAccessException();
        }
        return clientes;
    }

    private Cliente procesaCliente(Connection con, ResultSet results) throws SQLException, DataAccessException {

        Cliente result = ClienteMapper.toCliente(results);

        try (Statement statement = con.createStatement()) {

            String statementText = String.format(
                "select * from Seguros where cliente_FK = '%s'", 
                result.getDni()
            );

            ResultSet rs = statement.executeQuery(statementText);

            while (rs.next()) {
                result.getSeguros().add(SeguroMapper.toSeguro(rs));
            }
        }

        return result;
    }
}

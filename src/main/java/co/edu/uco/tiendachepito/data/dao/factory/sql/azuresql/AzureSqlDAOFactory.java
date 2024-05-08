package co.edu.uco.tiendachepito.data.dao.factory.sql.azuresql;

import java.sql.Connection;

import co.edu.uco.tiendachepito.data.dao.CiudadDAO;
import co.edu.uco.tiendachepito.data.dao.DepartamentoDAO;
import co.edu.uco.tiendachepito.data.dao.PaisDAO;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.data.dao.sql.azuresql.CiudadAzureSqlDAO;
import co.edu.uco.tiendachepito.data.dao.sql.azuresql.DepartamentoAzureSqlDAO;
import co.edu.uco.tiendachepito.data.dao.sql.azuresql.PaisAzureSqlDAO;

public final class AzureSqlDAOFactory extends DAOFactory {

	private Connection connection;

	public AzureSqlDAOFactory() {
		obtenerConexion();
	}

	@Override
	protected void obtenerConexion() {
		// TODO Auto-generated method stub
		connection = null;

	}

	@Override
	public void iniciarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void confirmarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelarTransaccion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrarConexion() {
		// TODO Auto-generated method stub

	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisAzureSqlDAO(connection);
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoAzureSqlDAO(connection);
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadAzureSqlDAO(connection);
	}

}

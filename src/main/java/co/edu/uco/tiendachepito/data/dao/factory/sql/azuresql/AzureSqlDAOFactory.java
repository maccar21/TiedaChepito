package co.edu.uco.tiendachepito.data.dao.factory.sql.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.tiendachepito.crosscutting.helpers.SQLHelper;
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
		final String connectionUrl = "jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>";
		try {
			connection = DriverManager.getConnection(connectionUrl);
		}catch (final SQLException exception){
			// TODO Auto-generated catch block
		} catch (final Exception exception){
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(connection);
	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(connection);
	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(connection);
	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(connection);
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

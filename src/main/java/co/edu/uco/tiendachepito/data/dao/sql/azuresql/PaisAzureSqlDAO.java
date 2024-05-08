package co.edu.uco.tiendachepito.data.dao.sql.azuresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.tiendachepito.data.dao.PaisDAO;
import co.edu.uco.tiendachepito.data.dao.sql.SqlConnection;
import co.edu.uco.tiendachepito.entity.PaisEntity;

public final class PaisAzureSqlDAO extends SqlConnection implements PaisDAO {

	public PaisAzureSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void crear(final PaisEntity entidad) {
		final var sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("INSERT INTO Pais(Nombre)");
		sentenciaSql.append("VALUES('Colombia')");

	}

	@Override
	public final void actualizar(final PaisEntity entidad) {
		final var sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("UPDATE Pais");
		sentenciaSql.append("SET Nombre = 'Venezuela'");
		sentenciaSql.append("WHERE id = 1");

	}

	@Override
	public final void eliminar(final int id) {
		final var sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("DELETE FROM Pais");
		sentenciaSql.append("WHERE id = 1");
	}

	@Override
	public final List<PaisEntity> consultar(PaisEntity entidad) {

		final var sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT id, nombre");
		sentenciaSql.append("FROM Pais");
		sentenciaSql.append("ORDER BY nombre ASC");
		
		return null;
	}

}

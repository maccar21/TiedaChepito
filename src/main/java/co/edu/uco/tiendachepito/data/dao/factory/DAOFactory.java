package co.edu.uco.tiendachepito.data.dao.factory;

import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.DataTiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.messagecatalog.MessageCatalog;
import co.edu.uco.tiendachepito.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tiendachepito.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tiendachepito.data.dao.CiudadDAO;
import co.edu.uco.tiendachepito.data.dao.DepartamentoDAO;
import co.edu.uco.tiendachepito.data.dao.PaisDAO;
import co.edu.uco.tiendachepito.data.dao.factory.enums.Factory;
import co.edu.uco.tiendachepito.data.dao.factory.sql.azuresql.AzureSqlDAOFactory;
import co.edu.uco.tiendachepito.entity.PaisEntity;

import java.util.List;

public abstract class DAOFactory {

	public static final DAOFactory getFactory(final Factory factory){
		switch(factory){
			case AZURESQL -> {
				return new AzureSqlDAOFactory();
			}
			case SQLSERVER -> {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos SQL Server";

				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
			}
			case POSTGRESQL -> {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos postgre SQL";

				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
			}
			case MYSQL -> {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos MySQL";

				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
			}
			case ORACLE -> {
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
				var mensajeTecnico = "No existe configurada una factoria de datos para una base de datos Oracle";

				throw new DataTiendaChepitoException(mensajeTecnico, mensajeUsuario);
			}
			default ->{
				return new AzureSqlDAOFactory();
			}
		}

	}

	protected abstract void obtenerConexion();

	public abstract void iniciarTransaccion();

	public abstract void confirmarTransaccion();

	public abstract void cancelarTransaccion();

	public abstract void cerrarConexion();

	public abstract PaisDAO getPaisDAO();

	public abstract DepartamentoDAO getDepartamentoDAO();

	public abstract CiudadDAO getCiudadDAO();

	public static void main(String[] args) {
		DAOFactory.getFactory(Factory.AZURESQL).getPaisDAO().crear(null);
		System.out.println("CREE");
		DAOFactory.getFactory(Factory.AZURESQL).getPaisDAO().actualizar(null);
		System.out.println("ACTUALICE");
		DAOFactory.getFactory(Factory.AZURESQL).getPaisDAO().eliminar(0);
		System.out.println("ELIMINE");
		List<PaisEntity> resultados = DAOFactory.getFactory(Factory.AZURESQL).getPaisDAO().consultar(null);
		System.out.println("CONSULTE");
	}

}

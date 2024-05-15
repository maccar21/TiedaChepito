package co.edu.uco.tiendachepito.crosscutting.helpers;

import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.CrosscuttingTiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tiendachepito.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

import java.sql.Connection;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public final class SQLHelper {

    private SQLHelper(){
        super();
    }

    public static final boolean IsNull(final Connection connection){
        return ObjectHelper.getObjectHelper().isNull(connection);
    }

    public static final boolean isOpen(final Connection connection){
        try {
            return !isNull(connection) && !connection.isClosed();
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00007);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00008);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void close(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00009);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            connection.close();
        }catch (final CrosscuttingTiendaChepitoException exception){
            throw exception;
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00010);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00011);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }
    }


    public static final void commit(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            if(connection.getAutoCommit()){
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00013);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            connection.commit();
        }catch (final CrosscuttingTiendaChepitoException exception){
            throw exception;
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00014);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00015);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }
    }


    public static final void rollback(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            if(connection.getAutoCommit()){
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00017);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            connection.rollback();
        }catch (final CrosscuttingTiendaChepitoException exception){
            throw exception;
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00018);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00019);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static final void initTransaction(final Connection connection){
        try {
            if (!isOpen(connection)) {
                var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
                var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00020);

                throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario);
            }

            connection.setAutoCommit(false);
        }catch (final CrosscuttingTiendaChepitoException exception){
            throw exception;
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);

            throw new CrosscuttingTiendaChepitoException(mensajeTecnico, mensajeUsuario, exception);
        }
    }


}

package co.edu.uco.tiendachepito.business.facade.concrete;

import co.edu.uco.tiendachepito.business.assembler.dto.concrete.PaisDTODomainAssembler;
import co.edu.uco.tiendachepito.business.facade.RegistrarPaisFachada;
import co.edu.uco.tiendachepito.business.usecase.RegistrarPais;
import co.edu.uco.tiendachepito.business.usecase.concrete.RegistrarPaisImpl;
import co.edu.uco.tiendachepito.crosscutting.exceptions.TiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.BusinessTiendaChepitoException;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.data.dao.factory.enums.Factory;
import co.edu.uco.tiendachepito.dto.PaisDTO;

public final class RegistrarPaisFachadaImpl implements RegistrarPaisFachada {

    private DAOFactory factory;

    public RegistrarPaisFachadaImpl() {
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }

    @Override
    public final void ejecutar(PaisDTO pais) {
        try {
            factory.iniciarTransaccion();
            var paisDomain = PaisDTODomainAssembler.obtenerInstancia().ensamblarDominio(pais);
            RegistrarPais useCase = new RegistrarPaisImpl(factory);
            useCase.ejecutar(paisDomain);
            factory.confirmarTransaccion();
        } catch (TiendaChepitoException exception) {
            factory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            factory.cancelarTransaccion();
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información del nuevo país";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de registrar el nuevo país en el método ejecutar de la clase RegistrarPaisFachadaImpl";

            throw new BusinessTiendaChepitoException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
package co.edu.uco.tiendachepito.business.facade.concret;

import co.edu.uco.tiendachepito.business.assembler.dto.concrete.CiudadDTODomainAssembler;
import co.edu.uco.tiendachepito.business.facade.RegistrarCiudadFachada;
import co.edu.uco.tiendachepito.business.usecase.RegistrarCiudad;
import co.edu.uco.tiendachepito.business.usecase.concrete.RegistrarCiudadImpl;
import co.edu.uco.tiendachepito.crosscutting.exceptions.TiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.BusinessTiendaChepitoException;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.data.dao.factory.enums.Factory;
import co.edu.uco.tiendachepito.dto.CiudadDTO;

public class ResgistarCiudadFachadaImpl implements RegistrarCiudadFachada {

    private DAOFactory factory;

    public ResgistarCiudadFachadaImpl(){
        factory = DAOFactory.getFactory(Factory.AZURESQL);
    }

    @Override
    public final void ejecutar(final CiudadDTO ciudad) {
        try{
            factory.iniciarTransaccion();

            var ciudadDomain = CiudadDTODomainAssembler.obtenerInstancia().ensamblarDominio(ciudad);

            final RegistrarCiudad UseCase = new RegistrarCiudadImpl(factory);
            UseCase.ejecutar(ciudadDomain);

            factory.confirmarTransaccion();
        }catch (final TiendaChepitoException exception){
            factory.cancelarTransaccion();
            throw exception;
        }catch (final Exception exception){
            factory.cancelarTransaccion();

            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la informacion de la nueva ciudad";
            var mensajeTecnico = "Se ha presentado un problema inesperado tratando de registrar la informacion de la nueva ciudad en el metodo ejecutar de la clase RegistrarCiudadFachadaImpl. Por favor revise la traza completa del problema";

            throw new BusinessTiendaChepitoException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }

    }
}

package co.edu.uco.tiendachepito.business.facade.concret;

import co.edu.uco.tiendachepito.business.assembler.dto.concrete.CiudadDTODomainAssembler;
import co.edu.uco.tiendachepito.business.assembler.dto.concrete.PaisDTODomainAssembler;
import co.edu.uco.tiendachepito.business.facade.ConsultarPaisesFachada;
import co.edu.uco.tiendachepito.business.usecase.ConsultarPaises;
import co.edu.uco.tiendachepito.business.usecase.RegistrarCiudad;
import co.edu.uco.tiendachepito.business.usecase.concrete.ConsultarPaisesImpl;
import co.edu.uco.tiendachepito.business.usecase.concrete.RegistrarCiudadImpl;
import co.edu.uco.tiendachepito.crosscutting.exceptions.TiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.BusinessTiendaChepitoException;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.data.dao.factory.enums.Factory;
import co.edu.uco.tiendachepito.dto.PaisDTO;

import java.util.List;

public final class ConsultarPaisesFachadaImpl implements ConsultarPaisesFachada {

    private DAOFactory factory;

    public ConsultarPaisesFachadaImpl(){
        factory = DAOFactory.getFactory(Factory.AZURESQL);
    }

    @Override
    public final List<PaisDTO> execute(final PaisDTO pais) {
        try{
            var paisDomain = PaisDTODomainAssembler.obtenerInstancia().ensamblarDominio(pais);

            final ConsultarPaises UseCase = new ConsultarPaisesImpl(factory);
            var resultados = UseCase.ejecutar(paisDomain);
            return PaisDTODomainAssembler.obtenerInstancia()
                            .ensamblarListaDTO(resultados);

        }catch (final TiendaChepitoException exception){
            throw exception;
        }catch (final Exception exception){

            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de los paises";
            var mensajeTecnico = "Se ha presentado un problema inesperado tratando de conusltar la informacion de los paises. Por favor revise la traza completa del problema";

            throw new BusinessTiendaChepitoException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}

package co.edu.uco.tiendachepito.business.facade.concrete;


import co.edu.uco.tiendachepito.business.assembler.dto.concrete.PaisDTODomainAssembler;
import co.edu.uco.tiendachepito.business.facade.ConsultarPaisesFachada;
import co.edu.uco.tiendachepito.business.usecase.ConsultarPaises;
import co.edu.uco.tiendachepito.business.usecase.concrete.ConsultarPaisesImpl;
import co.edu.uco.tiendachepito.crosscutting.exceptions.TiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.BusinessTiendaChepitoException;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.data.dao.factory.enums.Factory;
import co.edu.uco.tiendachepito.dto.PaisDTO;

import java.util.List;

public final class ConsultarPaisesFachadaImpl implements ConsultarPaisesFachada {

    private DAOFactory factory;

    public ConsultarPaisesFachadaImpl(){
        factory = DAOFactory.getFactory(Factory.AZURE_SQL);
    }
    @Override
    public final List<PaisDTO> execute(final PaisDTO pais) {
        try {
            var paisDomain = PaisDTODomainAssembler.obtenerInstancia().ensamblarDominio(pais);

            final ConsultarPaises useCase = new ConsultarPaisesImpl(factory);
            var resultados = useCase.ejecutar(paisDomain);
            return PaisDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (TiendaChepitoException exception){
            throw exception;
        }catch (Exception exception){
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de los paises";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la informacion de los paises";

            throw new BusinessTiendaChepitoException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
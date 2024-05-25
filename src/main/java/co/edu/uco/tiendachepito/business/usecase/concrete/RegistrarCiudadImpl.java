package co.edu.uco.tiendachepito.business.usecase.concrete;

import co.edu.uco.tiendachepito.business.assembler.entity.concrete.CiudadEntityDomainAssembler;
import co.edu.uco.tiendachepito.business.domain.CiudadDomain;
import co.edu.uco.tiendachepito.business.usecase.RegistrarCiudad;
import co.edu.uco.tiendachepito.crosscutting.exceptions.custom.BusinessTiendaChepitoException;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;
import co.edu.uco.tiendachepito.entity.CiudadEntity;
import co.edu.uco.tiendachepito.entity.DepartamentoEntity;


import java.util.List;

public class RegistrarCiudadImpl implements RegistrarCiudad {

    private final DAOFactory factory;

    public RegistrarCiudadImpl(final DAOFactory factory) {
        this.factory = factory;
    }


    @Override
    public void ejecutar(final CiudadDomain ciudad) {
        // 1. validar que los datos sean validos en las obligatoriedades
        // 2. No debe existir una ciudad con el mismo nombre para el mismo departamento
        validarNoExistenciaCiudad(ciudad.getNombre(), ciudad.getDepartamento().getId());
        //3. guardar la informacion de la nueva ciudad
        var ciudadEntity = CiudadEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(ciudad);
        factory.getCiudadDAO().crear(ciudadEntity);
    }
    private void validarNoExistenciaCiudad(String nombreCiudad, int idDepartamento){
        var departamentoEntity = DepartamentoEntity.build(idDepartamento);
        var ciudadEntity = CiudadEntity.build(0, nombreCiudad, departamentoEntity);

        final List<CiudadEntity> resultados = factory.getCiudadDAO().consultar(ciudadEntity);

        if(!resultados.isEmpty()){
            var mensajeUsuario = "Ya existe una ciudad en con nombre {1} asociada al departamento deseado";
            throw new BusinessTiendaChepitoException(mensajeUsuario);
        }
    }
}

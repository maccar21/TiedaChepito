package co.edu.uco.tiendachepito.business.assembler.entity.concrete;

import co.edu.uco.tiendachepito.business.assembler.dto.DTODomainAssembler;
import co.edu.uco.tiendachepito.business.assembler.dto.concrete.CiudadDTODomainAssembler;
import co.edu.uco.tiendachepito.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.tiendachepito.business.domain.CiudadDomain;
import co.edu.uco.tiendachepito.business.domain.DepartamentoDomain;
import co.edu.uco.tiendachepito.business.domain.PaisDomain;
import co.edu.uco.tiendachepito.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tiendachepito.dto.CiudadDTO;
import co.edu.uco.tiendachepito.entity.CiudadEntity;
import co.edu.uco.tiendachepito.entity.DepartamentoEntity;
import co.edu.uco.tiendachepito.entity.PaisEntity;

public final class CiudadEntityDomainAssembler implements EntityDomainAssembler<CiudadDomain, CiudadEntity> {

    private static final EntityDomainAssembler<CiudadDomain, CiudadEntity> instancia = new CiudadEntityDomainAssembler();

    private static final EntityDomainAssembler<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoEntityDomainAssembler
            .obtenerInstancia();

    private CiudadEntityDomainAssembler() {
        super();
    }

    public static final  EntityDomainAssembler<CiudadDomain, CiudadEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public final CiudadDomain ensamblarDominio(final CiudadEntity entidad) {
        var ciudadEntityTmp = ObjectHelper.getObjectHelper().getDefault(entidad, CiudadEntity.build(0));
        var departamentoDominio = departamentoAssembler.ensamblarDominio(ciudadEntityTmp.getDepartamento());

        return CiudadDomain.crear(ciudadEntityTmp.getId(),
                ciudadEntityTmp.getNombre(),
                departamentoDominio);    }

    @Override
    public final CiudadEntity ensamblarEntidad(final CiudadDomain dominio) {
        var ciudadDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio,CiudadDomain.crear());
        var departamentoEntity = departamentoAssembler.ensamblarEntidad(ciudadDomainTmp.getDepartamento());

        return CiudadEntity.build(ciudadDomainTmp.getId(), ciudadDomainTmp.getNombre(), departamentoEntity);
    }
}

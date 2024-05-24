package co.edu.uco.tiendachepito.business.assembler.entity.concrete;

import co.edu.uco.tiendachepito.business.assembler.entity.EntityDomainAssembler;
import co.edu.uco.tiendachepito.business.domain.CiudadDomain;
import co.edu.uco.tiendachepito.business.domain.DepartamentoDomain;
import co.edu.uco.tiendachepito.business.domain.PaisDomain;
import co.edu.uco.tiendachepito.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tiendachepito.entity.CiudadEntity;
import co.edu.uco.tiendachepito.entity.DepartamentoEntity;
import co.edu.uco.tiendachepito.entity.PaisEntity;

public final class DepartamentoEntityDomainAssembler implements EntityDomainAssembler<DepartamentoDomain, DepartamentoEntity> {

    private static final EntityDomainAssembler<DepartamentoDomain, DepartamentoEntity> instancia = new DepartamentoEntityDomainAssembler();

    private static final EntityDomainAssembler<PaisDomain, PaisEntity> paisAssembler = PaisEntityDomainAssembler
            .obtenerInstancia();

    private DepartamentoEntityDomainAssembler() {
        super();
    }

    public static final  EntityDomainAssembler<DepartamentoDomain, DepartamentoEntity> obtenerInstancia(){
        return instancia;
    }

    @Override
    public final DepartamentoDomain ensamblarDominio(final DepartamentoEntity entidad) {
        var departamentoEntityTmp = ObjectHelper.getObjectHelper().getDefault(entidad,DepartamentoEntity.build(0));
        var paisDominio = paisAssembler.ensamblarDominio(departamentoEntityTmp.getPais());

        return DepartamentoDomain.crear(departamentoEntityTmp.getId(),
                departamentoEntityTmp.getNombre(),
                paisDominio);
    }

    @Override
    public final DepartamentoEntity ensamblarEntidad(final DepartamentoDomain dominio) {
        var departamentoDomainTmp = ObjectHelper.getObjectHelper().getDefault(dominio,DepartamentoDomain.crear());
        var paisEntity = paisAssembler.ensamblarEntidad(departamentoDomainTmp.getPais());

        return DepartamentoEntity.build(departamentoDomainTmp.getId(), departamentoDomainTmp.getNombre(), paisEntity);
    }
}

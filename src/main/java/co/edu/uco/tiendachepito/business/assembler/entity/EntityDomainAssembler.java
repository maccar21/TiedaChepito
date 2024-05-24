package co.edu.uco.tiendachepito.business.assembler.entity;

public interface EntityDomainAssembler <D, E>{

    D ensamblarDominio(E entidad);

    E ensamblarEntidad(D dominio);

}

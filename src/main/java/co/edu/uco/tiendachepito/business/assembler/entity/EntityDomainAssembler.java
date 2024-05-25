package co.edu.uco.tiendachepito.business.assembler.entity;

import java.util.List;

public interface EntityDomainAssembler <D, E>{

    D ensamblarDominio(E entidad);

    E ensamblarEntidad(D dominio);

    List<D> ensamblarListaDominios(List<E> listaEntidades);

}

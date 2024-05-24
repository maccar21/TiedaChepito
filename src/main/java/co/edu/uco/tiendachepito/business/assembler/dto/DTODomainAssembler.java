package co.edu.uco.tiendachepito.business.assembler.dto;

public interface DTODomainAssembler<D, T> {

    D ensamblarDominio(T dto);

    T ensamblarDTO(D dominio);
}

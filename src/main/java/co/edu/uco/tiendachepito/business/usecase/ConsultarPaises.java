package co.edu.uco.tiendachepito.business.usecase;

import co.edu.uco.tiendachepito.business.domain.PaisDomain;

import java.util.List;

public interface ConsultarPaises {

    List<PaisDomain> ejecutar(PaisDomain pais);
}

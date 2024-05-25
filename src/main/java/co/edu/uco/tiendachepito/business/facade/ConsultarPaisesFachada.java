package co.edu.uco.tiendachepito.business.facade;

import co.edu.uco.tiendachepito.dto.PaisDTO;

import java.util.List;

public interface ConsultarPaisesFachada {

    List<PaisDTO> execute (PaisDTO pais);
}

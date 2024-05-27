package co.edu.uco.tiendachepito.business.usecase.concrete;

import co.edu.uco.tiendachepito.business.domain.PaisDomain;
import co.edu.uco.tiendachepito.business.usecase.RegistrarPais;
import co.edu.uco.tiendachepito.data.dao.factory.DAOFactory;

public class RegistrarPaisImpl implements RegistrarPais {

    private final DAOFactory factory;

    public RegistrarPaisImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(PaisDomain pais) {
        // POL.1 Validar que los datos sean validados a nivel de tipo de dato, longitud, obligatoriedad, formato, rango.
        // POL.2 No debe existir una ciudad con el mismo nombre para el mismo departamento.
        // POL.3 Guardar la informacion de la nueva ciudad.
    }
}
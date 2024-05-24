package co.edu.uco.tiendachepito.business.domain;

import co.edu.uco.tiendachepito.crosscutting.helpers.TextHelper;

public class PaisDomain {

    private int id;
    private String nombre;

    private PaisDomain(final int id,final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    private PaisDomain(){
        setNombre(TextHelper.EMPTY);
    }

    public static final PaisDomain crear(final int id, final String nombre){
        return new PaisDomain(id, nombre);
    }

    public static final PaisDomain crear(){
        return new PaisDomain();
    }

    private final void setId(int id) {
        this.id = id;
    }

    private final void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final int getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }
}

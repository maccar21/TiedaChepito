package co.edu.uco.tiendachepito.api.controller;

import co.edu.uco.tiendachepito.api.response.pais.PaisResponse;
import co.edu.uco.tiendachepito.business.facade.concrete.ConsultarPaisesFachadaImpl;
import co.edu.uco.tiendachepito.crosscutting.exceptions.TiendaChepitoException;
import co.edu.uco.tiendachepito.crosscutting.helpers.NumericHelper;
import co.edu.uco.tiendachepito.dto.PaisDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    @GetMapping("/dummy")
    public PaisDTO getDummy(){
        return PaisDTO.build();
    }

    @GetMapping
    public ResponseEntity<PaisResponse> consultar(
            @RequestParam(required = false,defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "")String nombre){
        PaisResponse paisResponse = PaisResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            final var paisDtoFilter = PaisDTO.build().setId(NumericHelper.convertToInt(id)).setNombre(nombre);
            final ConsultarPaisesFachadaImpl fachada = new ConsultarPaisesFachadaImpl();
            paisResponse.setDatos(fachada.execute(paisDtoFilter));
            paisResponse.getMensajes().add("Paises consultados exitosamente");
        } catch (final TiendaChepitoException exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add("se ha presentado un problema inesperado");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(paisResponse, httpStatusResponse);
    }

    @PostMapping
    public ResponseEntity<PaisResponse> crear(@RequestBody PaisDTO pais){
        PaisResponse paisResponse = PaisResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            paisResponse.getDatos().add(pais);
            paisResponse.getMensajes().add("Pais registrado correctamente");
        } catch (final TiendaChepitoException exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add("se ha presentado un problema inesperado tratando de crear el pais");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(paisResponse,httpStatusResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaisResponse> eliminar(@PathVariable int id){
        PaisResponse paisResponse = PaisResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            paisResponse.getDatos().add(PaisDTO.build().setId(id));
            paisResponse.getMensajes().add("Pais ha sido eliminado correctamente");
        } catch (final TiendaChepitoException exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add("se ha presentado un problema inesperado tratando de eliminar el pais");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(paisResponse, httpStatusResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisResponse> modificar(@PathVariable int id, @RequestBody PaisDTO pais){
        PaisResponse paisResponse = PaisResponse.build();
        HttpStatus httpStatusResponse = HttpStatus.OK;

        try {
            pais.setId(id);
            paisResponse.getDatos().add(pais);
            paisResponse.getMensajes().add("Pais modificado correctamente");

        } catch (final TiendaChepitoException exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add(exception.getMensajeUsuario());
            httpStatusResponse = HttpStatus.BAD_REQUEST;
        } catch (final Exception exception){
            exception.printStackTrace();
            paisResponse.getMensajes().add("se ha presentado un problema inesperado tratando de modificar el pais");
            httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(paisResponse, httpStatusResponse);
    }
}
package co.edu.uco.tiendachepito.api.controller;


import co.edu.uco.tiendachepito.business.facade.ConsultarPaisesFachada;
import co.edu.uco.tiendachepito.business.facade.concret.ConsultarPaisesFachadaImpl;
import co.edu.uco.tiendachepito.dto.PaisDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    @GetMapping
    public List<PaisDTO> listar () {
        ConsultarPaisesFachada fachada = new ConsultarPaisesFachadaImpl();
        return fachada.execute(null);

    }

}

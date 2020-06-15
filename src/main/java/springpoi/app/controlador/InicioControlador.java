package springpoi.app.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {
    
    @GetMapping(value={"/", "/index", "/home"})
    public String inicio(){
        
        
        return "trabajadores/inicio";
        
    }
}

package springpoi.app.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;

@Slf4j
@Controller
@RequestMapping("/vistasPdf")
@SessionAttributes("trabajador")
public class VistasPdfControlador {
    
    @Autowired
    private ITrabajadorServicio trabajadorServicio;
    
    @ModelAttribute("trabajador")
    public Trabajador trabajador(){
    
        return new Trabajador();
    }
    
    
    @GetMapping(value="/inicioPdf")
    public String inicioPdf (@ModelAttribute("trabajador") Trabajador trabajador ,Model model) {
        
        trabajador = trabajadorServicio.encontrarTrabajador(trabajador);
        
        model.addAttribute("trabajador" , trabajador);
        
        return "pdf/inicioPdf";
    }
    
    
    
    @GetMapping(value="/vistaGenerarLiquidacion")
    public String inicioPdf (Model model) {
        return "pdf/generarLiquidacion";
    }
    
    @GetMapping(value="/salirPdf")
    public String salirPdf(Model model , SessionStatus status){
    
        status.setComplete();
        
        return "redirect:/";
    }
}

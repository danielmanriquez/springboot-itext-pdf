package springpoi.app.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springpoi.app.editores.MayusculasEditor;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;

@Slf4j
@Controller
@RequestMapping("/trabajadores")
public class TrabajadorControlador {
    
    @Autowired
    private ITrabajadorServicio trabajadorServicio;
    
    @Autowired
    private MayusculasEditor mayusculasEditor;
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
        
        
        binder.registerCustomEditor(String.class,"primerNombre",mayusculasEditor);
        binder.registerCustomEditor(String.class,"segundoNombre",mayusculasEditor);
        binder.registerCustomEditor(String.class,"primerApellido",mayusculasEditor);
        binder.registerCustomEditor(String.class,"segundoApellido",mayusculasEditor);
        
    }
    
    
    @GetMapping(value="/listarTrabajadores")
    public String mostrarTodosLosTrabajadores(Model model){
        
        List<Trabajador> trabajadores = trabajadorServicio.listarTrabajadores();
        model.addAttribute("trabajadores", trabajadores);
        return "trabajadores/listadoTrabajadores";
        
    }
    
    @GetMapping(value="agregarTrabajador")
    public String agregarTrabajador(Trabajador trabajador){
        return "trabajadores/editarTrabajador";
    }
    
    @PostMapping("/guardarTrabajador")
    public String guardarTrabajador(@Valid Trabajador trabajador, BindingResult result){
        
        log.info("entrando al metodo Agregar a la BD");
        if(result.hasErrors()){
            if(trabajador.getIdTrabajador() != null){
                
                return "trabajadores/editarTrabajador";
            }
            
            log.info("Hubo un error , redirigiendo hacia la vista agregarTrabajador");
            log.info(result.toString());
            return "trabajadores/editarTrabajador";
        }
        
        
        trabajadorServicio.guardar(trabajador);
        return "redirect:/";
    }
    
    @GetMapping("/eliminarTrabajador")
    public String eliminarTrabajador(Trabajador trabajador){
        
        trabajadorServicio.eliminar(trabajador);
        
        return "redirect:/trabajadores/listarTrabajadores";
    }
    
    @GetMapping("/editarTrabajador/{idTrabajador}")
    public String editar(Trabajador trabajador, Model model){
        trabajador = trabajadorServicio.encontrarTrabajador(trabajador);
        model.addAttribute("trabajador", trabajador);
        return "trabajadores/editarTrabajador";
    }
    
    
    
    
}

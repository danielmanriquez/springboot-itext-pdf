/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springpoi.app.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;

@Slf4j
@Controller
@RequestMapping("/trabajadores")
public class TrabajadorControlador {
    
    @Autowired
    private ITrabajadorServicio trabajadorServicio;
    
    @Autowired
    private ServletContext context;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
    }
    
    
    @GetMapping(value="/listarTrabajadores")
    public String mostrarTodosLosTrabajadores(Model model){
        
        List<Trabajador> trabajadores = trabajadorServicio.listarTrabajadores();
        model.addAttribute("trabajadores", trabajadores);
        return "trabajadores/listadoTrabajadores";
        
    }
    
    @GetMapping(value="agregarTrabajador")
    public String agregarTrabajador(Trabajador trabajador){
        return "trabajadores/agregarTrabajador";
    }
    
    @PostMapping("/guardarTrabajador")
    public String guardarTrabajador(@Valid Trabajador trabajador, BindingResult result){
        
        log.info("entrando al metodo Agregar a la BD");
        if(result.hasErrors()){
            log.info("Hubo un error , redirigiendo hacia la vista agregarTrabajador");
            log.info(result.toString());
            return "trabajadores/agregarTrabajador";
        }
        
        trabajadorServicio.guardar(trabajador);
        return "redirect:/";
    }
    
    @GetMapping(value="/crearPdf")
    public String crearPdf(HttpServletRequest request , HttpServletResponse response){
        
        List<Trabajador> trabajadores = trabajadorServicio.listarTrabajadores();
        boolean bandera = trabajadorServicio.crearPdf(trabajadores , context , request , response);
        
        if(bandera){
            String rutaCompleta = request.getServletContext().getRealPath("/resources/reportes/"+"trabajadores"+".pdf");
            filedownload(rutaCompleta , response , "trabajadores.pdf");
        }
                
        return null;
    }

    private void filedownload(String rutaCompleta, HttpServletResponse response, String nombreArchivo) {
        
        File file = new File(rutaCompleta);
        final int BUFFER_SIZE= 4096;
        if(file.exists()){
            try{
                
                FileInputStream inputStream = new FileInputStream(file);
                String tipoMime = context.getMimeType(rutaCompleta);
                response.setContentType(tipoMime);
                response.setHeader("content-disposition", "attachment; filename="+nombreArchivo);
                OutputStream outputStream = response.getOutputStream();
                
                byte[] buffer = new byte[BUFFER_SIZE];
                
                int bytesRead = -1 ;
                
                while((bytesRead = inputStream.read(buffer))!= -1){
                    
                    outputStream.write(buffer , 0 , bytesRead);
                }
                
                inputStream.close();
                outputStream.close();
                file.delete();
                
            }catch(Exception e){
                
                e.printStackTrace();
            }
        
        }
    }
    
    
    
}

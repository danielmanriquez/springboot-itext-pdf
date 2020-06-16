package springpoi.app.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;
import springpoi.app.util.GenerarLiquidacion;

@Controller
@RequestMapping("/pdf")
public class PDFControlador {
    
    @Autowired
    private ITrabajadorServicio trabajadorServicio;
    
    @Autowired
    private ServletContext context;
    
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
    
    @GetMapping(value="/crearPdfLiquidacion")
    public String crearPdfLiquidacion(Trabajador trabajador, HttpServletRequest request , HttpServletResponse response){
        
        trabajador = trabajadorServicio.encontrarTrabajador(trabajador);
        boolean bandera = GenerarLiquidacion.crearLiquidacionTrabajador(trabajador, context, request, response);
        
        if(bandera){
            String rutaCompleta = request.getServletContext().getRealPath("/resources/reportes/"+"trabajador_"+trabajador.getIdTrabajador()+".pdf");
            filedownload(rutaCompleta , response , "trabajador_"+trabajador.getIdTrabajador()+".pdf");
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

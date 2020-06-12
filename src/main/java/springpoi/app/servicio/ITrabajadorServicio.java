
package springpoi.app.servicio;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import springpoi.app.model.Trabajador;

public interface ITrabajadorServicio {
    
    public List<Trabajador> listarTrabajadores();
    
    public void guardar(Trabajador trabajador);
    
    public void eliminar(Trabajador Trabajador);
    
    public Trabajador encontrarTrabajador(Trabajador trabajador);

    public boolean crearPdf(List<Trabajador> trabajadores, ServletContext context, HttpServletRequest request, HttpServletResponse response);
    
}

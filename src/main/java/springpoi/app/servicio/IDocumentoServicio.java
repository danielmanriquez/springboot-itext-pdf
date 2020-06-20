package springpoi.app.servicio;

import java.util.List;
import javax.servlet.ServletContext;
import springpoi.app.model.Trabajador;

public interface IDocumentoServicio {
    
    public boolean listaTrabajadores (List<Trabajador> trabajadores, ServletContext context);
    public boolean crearLiquidacionTrabajador(Trabajador trabajador, ServletContext context);
    
    
}

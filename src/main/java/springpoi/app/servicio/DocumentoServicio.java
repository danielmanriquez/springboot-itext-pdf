/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springpoi.app.servicio;

import java.util.List;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springpoi.app.model.Trabajador;
import springpoi.app.util.GenerarDocumento;


@Slf4j
@Service
public class DocumentoServicio implements IDocumentoServicio {

    @Autowired
    private GenerarDocumento generarDocumento;
    
    
    @Override
    public boolean listaTrabajadores(List<Trabajador> trabajadores, ServletContext context) {
        
        
        return generarDocumento.crearListaTrabajadores(trabajadores, context);
    }

    @Override
    public boolean crearLiquidacionTrabajador(Trabajador trabajador, ServletContext context) {
        
        return generarDocumento.crearLiquidacionTrabajador(trabajador, context);
    }

    @Override
    public boolean crearContratoTrabajador(Trabajador trabajador, ServletContext context) {
        
        return generarDocumento.crearContratoTrabajador(trabajador, context);
    }
    
}

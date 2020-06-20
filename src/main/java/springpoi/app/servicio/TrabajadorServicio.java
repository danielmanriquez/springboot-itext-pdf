
package springpoi.app.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springpoi.app.dao.ITrabajadorDao;
import springpoi.app.model.Trabajador;

@Service
@Transactional
public class TrabajadorServicio implements ITrabajadorServicio {

    @Autowired
    ITrabajadorDao trabajadorDao;
    
    
    @Override
    public List<Trabajador> listarTrabajadores() {
        return trabajadorDao.findAll();
    }

    @Override
    public void guardar(Trabajador trabajador) {
        trabajadorDao.save(trabajador);
    }

    @Override
    public void eliminar(Trabajador Trabajador) {
        trabajadorDao.delete(Trabajador);
    }

    @Override
    public Trabajador encontrarTrabajador(Trabajador trabajador) {
        return trabajadorDao.findById(trabajador.getIdTrabajador()).orElse(null);
    }

}

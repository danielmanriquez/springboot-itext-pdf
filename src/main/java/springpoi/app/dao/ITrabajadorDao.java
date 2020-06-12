package springpoi.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springpoi.app.model.Trabajador;


public interface ITrabajadorDao extends JpaRepository<Trabajador, Integer> {
    
}

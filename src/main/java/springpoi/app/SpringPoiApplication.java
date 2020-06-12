package springpoi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;
import springpoi.app.servicio.TrabajadorServicio;


@SpringBootApplication
public class SpringPoiApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringPoiApplication.class, args);
        
    }

}

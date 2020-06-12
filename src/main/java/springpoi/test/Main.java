/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springpoi.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import springpoi.app.JPAConfiguration;
import springpoi.app.model.Trabajador;
import springpoi.app.servicio.ITrabajadorServicio;
import springpoi.app.servicio.TrabajadorServicio;

public class Main {
    public static void main(String[] args) {
        
       AbstractApplicationContext context = new AnnotationConfigApplicationContext(JPAConfiguration.class);
       ITrabajadorServicio trabajadorServicio = context.getBean(TrabajadorServicio.class);
       for (Trabajador trabajador : trabajadorServicio.listarTrabajadores()){
       
            System.out.println("Nombre: "+ trabajador.getNombre());
            System.out.println("Apellido: "+ trabajador.getApellido());
       }   
}
    
}
    

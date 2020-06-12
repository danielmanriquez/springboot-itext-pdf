package springpoi.app.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "trabajador")
public class Trabajador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer idTrabajador;
    
    @NotBlank
    private String nombre;
    
    @NotBlank
    private String apellido;
    
    @NotBlank
    private String rut;
    
    @NotNull
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaNacimiento;
    
    @NotBlank
    private String afp;
    
    @NotBlank
    private String salud;
    
}

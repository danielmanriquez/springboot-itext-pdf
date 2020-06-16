package springpoi.app.model;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiquidacionDeSueldo {

    public static final Double SUELDO_BASE = 300000d ;

    private Calendar fecha;
    private Integer diasTrabajados;
    private Double otrosIngresosImponibles;
    private Double asignacionFamiliar;
    private Double totalRemuneracionImponible;
    private Double descuentoCotizacionPrevisional;
    private Double descuentoCotizacionSalud;
    private Double descuentoCotizacionVoluntaria;
    private Double alcanceLiquido;
    private Double anticipo;

    public LiquidacionDeSueldo(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
        this.calcularLiquidacion();
        this.fecha= Calendar.getInstance();
       
    }

    public LiquidacionDeSueldo() {

        this.diasTrabajados = 31;
        this.calcularLiquidacion();
        this.fecha= Calendar.getInstance();
    }

    public void calcularLiquidacion() {

        otrosIngresosImponibles = 00d;
        asignacionFamiliar = 00d;
        totalRemuneracionImponible = (SUELDO_BASE / diasTrabajados) * diasTrabajados;
        descuentoCotizacionPrevisional = totalRemuneracionImponible * 10 / 100;
        descuentoCotizacionSalud = totalRemuneracionImponible * 7 / 100;
        descuentoCotizacionVoluntaria = 00d;
        anticipo = 00d;
        alcanceLiquido = totalRemuneracionImponible - descuentoCotizacionPrevisional - descuentoCotizacionSalud;
    }

    

    public Integer getDiasTrabajados() {
        return diasTrabajados;
    }

    public Double getOtrosIngresosImponibles() {
        return otrosIngresosImponibles;
    }

    public Double getAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public Double getTotalRemuneracionImponible() {
        return totalRemuneracionImponible;
    }

    public Double getDescuentoCotizacionPrevisional() {
        return descuentoCotizacionPrevisional;
    }

    public Double getDescuentoCotizacionSalud() {
        return descuentoCotizacionSalud;
    }

    public Double getDescuentoCotizacionVoluntaria() {
        return descuentoCotizacionVoluntaria;
    }

    public Double getAlcanceLiquido() {
        return alcanceLiquido;
    }

    public Double getAnticipo() {
        return anticipo;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        
        this.diasTrabajados = diasTrabajados;
        this.calcularLiquidacion();
    }


    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    
    
    
    
}
    


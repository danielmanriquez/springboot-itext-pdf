package springpoi.app.model;
import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiquidacionDeSueldo extends Documento {

    private Integer diasTrabajados;
    private Double otrosIngresosImponibles;
    private Double anticipo;

    public LiquidacionDeSueldo() {
        super();
        this.diasTrabajados = 30;
        otrosIngresosImponibles = 0.0;
        anticipo = 0.0;
    }

    public LiquidacionDeSueldo(int diasTrabajados, Calendar fechaDocumento) {
        super(fechaDocumento);
        this.diasTrabajados = diasTrabajados;
        otrosIngresosImponibles = 0.0;
        anticipo = 0.0;
    }

    public Integer getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public Double getOtrosIngresosImponibles() {
        return otrosIngresosImponibles;
    }

    public void setOtrosIngresosImponibles(Double otrosIngresosImponibles) {
        this.otrosIngresosImponibles = otrosIngresosImponibles;
    }

    public Double getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(Double anticipo) {
        this.anticipo = anticipo;
    }

    public Double getTotalRemuneracionImponible() {

        return (SUELDO_BASE / 30) * diasTrabajados;
    }

    public Double getDescuentoCotizacionPrevisional() {

        return getTotalRemuneracionImponible() * 10 / 100;
    }

    public Double getDescuentoCotizacionSalud() {

        return getTotalRemuneracionImponible() * 7 / 100;
    }

    public Double getAlcanceLiquido() {

        return getTotalRemuneracionImponible() - getDescuentoCotizacionPrevisional() - getDescuentoCotizacionSalud();

    }

    @Override
    public Calendar getFechaDocumento() {
        return super.getFechaDocumento(); //To change body of generated methods, choose Tools | Templates.
    }
    /**Variables finales de la interface**/

    public static String getNOMBRE_EMPRESA() {
        return NOMBRE_EMPRESA;
    }

    public static String getRUT_EMPRESA() {
        return RUT_EMPRESA;
    }

    public static Double getSUELDO_BASE() {
        return SUELDO_BASE;
    }

    public static int getTOTAL_DIAS_MES() {
        return TOTAL_DIAS_MES;
    }
    
    
    
    
    
    
    @Override
    public String toString() {
        return "Total Remuneracion Imponible : " + getTotalRemuneracionImponible()
                + " Descuento AFP : " + getDescuentoCotizacionPrevisional()
                + " Descuento Salud :" + getDescuentoCotizacionSalud();
    }

}
package springpoi.app.model;

import java.util.Calendar;

public abstract class Documento implements Empresa {
    
    private Calendar fechaDocumento;
    
    public Documento(){
    
        fechaDocumento = Calendar.getInstance();
    }
    
    public Documento(Calendar fechaDocumento){
        
        this.fechaDocumento = fechaDocumento;
    }

    public Calendar getFechaDocumento() {
        return fechaDocumento;
    }
    
    
}

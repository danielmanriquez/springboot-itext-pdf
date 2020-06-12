
package springpoi.app.servicio;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public boolean crearPdf(List<Trabajador> trabajadores, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        
        //le damos el formato al documento
        Document document = new Document(PageSize.A4 ,15, 15 , 45 , 30);
        try{
            
            String filePath = context.getRealPath("/resources/reportes");
            File file = new File(filePath);
            boolean archivoYaExiste = new File(filePath).exists();
            
            if(!archivoYaExiste){
                new File(filePath).mkdirs();
            }
            
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"trabajadores.pdf"));
            document.open();
            
            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            
            Paragraph paragraph = new Paragraph("Lista de Trabajadores",mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            
            document.add(paragraph);
            
            
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);
           
            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            
            float[] anchoColumnas = { 2f , 2f , 2f , 2f , 2f , 2f , 2f};
            
            table.setWidths(anchoColumnas);
            
            PdfPCell idTrabajador = new PdfPCell(new Paragraph("Nombre", tableHeader));
            idTrabajador.setBorderColor(BaseColor.BLACK);
            idTrabajador.setPaddingLeft(10);
            idTrabajador.setHorizontalAlignment(Element.ALIGN_CENTER);
            idTrabajador.setVerticalAlignment(Element.ALIGN_CENTER);
            idTrabajador.setBackgroundColor(BaseColor.GRAY);
            idTrabajador.setExtraParagraphSpace(5f);
            
            table.addCell(idTrabajador);
            
            PdfPCell nombre = new PdfPCell(new Paragraph("Nombre", tableHeader));
            nombre.setBorderColor(BaseColor.BLACK);
            nombre.setPaddingLeft(10);
            nombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            nombre.setVerticalAlignment(Element.ALIGN_CENTER);
            nombre.setBackgroundColor(BaseColor.GRAY);
            nombre.setExtraParagraphSpace(5f);
            
            table.addCell(nombre);
            
            PdfPCell apellido = new PdfPCell(new Paragraph("Apellido", tableHeader));
            apellido.setBorderColor(BaseColor.BLACK);
            apellido.setPaddingLeft(10);
            apellido.setHorizontalAlignment(Element.ALIGN_CENTER);
            apellido.setVerticalAlignment(Element.ALIGN_CENTER);
            apellido.setBackgroundColor(BaseColor.GRAY);
            apellido.setExtraParagraphSpace(5f);
          
            table.addCell(apellido);
            
            PdfPCell rut = new PdfPCell(new Paragraph("Rut", tableHeader));
            rut.setBorderColor(BaseColor.BLACK);
            rut.setPaddingLeft(10);
            rut.setHorizontalAlignment(Element.ALIGN_CENTER);
            rut.setVerticalAlignment(Element.ALIGN_CENTER);
            rut.setBackgroundColor(BaseColor.GRAY);
            rut.setExtraParagraphSpace(5f);
            
            table.addCell(rut);
            
            PdfPCell fechaNacimiento = new PdfPCell(new Paragraph("Fecha Nacimiento", tableHeader));
            fechaNacimiento.setBorderColor(BaseColor.BLACK);
            fechaNacimiento.setPaddingLeft(10);
            fechaNacimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
            fechaNacimiento.setVerticalAlignment(Element.ALIGN_CENTER);
            fechaNacimiento.setBackgroundColor(BaseColor.GRAY);
            fechaNacimiento.setExtraParagraphSpace(5f);
            
            table.addCell(fechaNacimiento);
            
            PdfPCell afp = new PdfPCell(new Paragraph("AFP", tableHeader));
            afp.setBorderColor(BaseColor.BLACK);
            afp.setPaddingLeft(10);
            afp.setHorizontalAlignment(Element.ALIGN_CENTER);
            afp.setVerticalAlignment(Element.ALIGN_CENTER);
            afp.setBackgroundColor(BaseColor.GRAY);
            afp.setExtraParagraphSpace(5f);
            
            table.addCell(afp);
            
            PdfPCell salud = new PdfPCell(new Paragraph("Salud", tableHeader));
            salud.setBorderColor(BaseColor.BLACK);
            salud.setPaddingLeft(10);
            salud.setHorizontalAlignment(Element.ALIGN_CENTER);
            salud.setVerticalAlignment(Element.ALIGN_CENTER);
            salud.setBackgroundColor(BaseColor.GRAY);
            salud.setExtraParagraphSpace(5f);
            
            table.addCell(salud);
            
            for (Trabajador trabajador : trabajadores){
                
            PdfPCell valorIdTrabajador = new PdfPCell(new Paragraph(trabajador.getIdTrabajador().toString(), tableBody));
            valorIdTrabajador.setBorderColor(BaseColor.BLACK);
            valorIdTrabajador.setPaddingLeft(10);
            valorIdTrabajador.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorIdTrabajador.setVerticalAlignment(Element.ALIGN_CENTER);
            valorIdTrabajador.setBackgroundColor(BaseColor.WHITE);
            valorIdTrabajador.setExtraParagraphSpace(5f);
            
            table.addCell(valorIdTrabajador);    
                
            PdfPCell valorNombre = new PdfPCell(new Paragraph(trabajador.getNombre(), tableBody));
            valorNombre.setBorderColor(BaseColor.BLACK);
            valorNombre.setPaddingLeft(10);
            valorNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorNombre.setVerticalAlignment(Element.ALIGN_CENTER);
            valorNombre.setBackgroundColor(BaseColor.WHITE);
            valorNombre.setExtraParagraphSpace(5f);
            
            table.addCell(valorNombre);
            
            PdfPCell valorApellido = new PdfPCell(new Paragraph(trabajador.getApellido(), tableBody));
            valorApellido.setBorderColor(BaseColor.BLACK);
            valorApellido.setPaddingLeft(10);
            valorApellido.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorApellido.setVerticalAlignment(Element.ALIGN_CENTER);
            valorApellido.setBackgroundColor(BaseColor.WHITE);
            valorApellido.setExtraParagraphSpace(5f);
            
            table.addCell(valorApellido);
            
            PdfPCell valorRut = new PdfPCell(new Paragraph(trabajador.getRut(), tableBody));
            valorRut.setBorderColor(BaseColor.BLACK);
            valorRut.setPaddingLeft(10);
            valorRut.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorRut.setVerticalAlignment(Element.ALIGN_CENTER);
            valorRut.setBackgroundColor(BaseColor.WHITE);
            valorRut.setExtraParagraphSpace(5f);
            
            table.addCell(valorRut);
            
            PdfPCell valorFechaNacimiento = new PdfPCell(new Paragraph(trabajador.getFechaNacimiento().toString(), tableBody));
            valorFechaNacimiento.setBorderColor(BaseColor.BLACK);
            valorFechaNacimiento.setPaddingLeft(10);
            valorFechaNacimiento.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorFechaNacimiento.setVerticalAlignment(Element.ALIGN_CENTER);
            valorFechaNacimiento.setBackgroundColor(BaseColor.WHITE);
            valorFechaNacimiento.setExtraParagraphSpace(5f);
            
            table.addCell(valorFechaNacimiento);
            
            PdfPCell valorAfp = new PdfPCell(new Paragraph(trabajador.getRut(), tableBody));
            valorAfp.setBorderColor(BaseColor.BLACK);
            valorAfp.setPaddingLeft(10);
            valorAfp.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorAfp.setVerticalAlignment(Element.ALIGN_CENTER);
            valorAfp.setBackgroundColor(BaseColor.WHITE);
            valorAfp.setExtraParagraphSpace(5f);
            
            table.addCell(valorAfp);
            
            PdfPCell valorSalud = new PdfPCell(new Paragraph(trabajador.getRut(), tableBody));
            valorSalud.setBorderColor(BaseColor.BLACK);
            valorSalud.setPaddingLeft(10);
            valorSalud.setHorizontalAlignment(Element.ALIGN_CENTER);
            valorSalud.setVerticalAlignment(Element.ALIGN_CENTER);
            valorSalud.setBackgroundColor(BaseColor.WHITE);
            valorSalud.setExtraParagraphSpace(5f);
            
            table.addCell(valorSalud);
            
            }
            
            document.add(table);
            document.close();
            writer.close();
            
            return true;
            
            
        }catch(Exception e){
                
            return false;
        }
        
    }
    
}

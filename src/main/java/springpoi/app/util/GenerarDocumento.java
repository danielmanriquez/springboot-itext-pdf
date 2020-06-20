package springpoi.app.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springpoi.app.model.LiquidacionDeSueldo;
import springpoi.app.model.Trabajador;

@Slf4j
@Component
public class GenerarDocumento {
    
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    
    public boolean crearListaTrabajadores(List<Trabajador> trabajadores, ServletContext context) {
        
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
            log.info(e.toString());
            return false;
        }
        
    }
    
    public boolean crearLiquidacionTrabajador(Trabajador trabajador, ServletContext context) {
        
        LiquidacionDeSueldo liquidacion = new LiquidacionDeSueldo();
        
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);

        try {

            String filePath = context.getRealPath("/resources/reportes");
            File file = new File(filePath);
            boolean archivoYaExiste = new File(filePath).exists();

            if (!archivoYaExiste) {
                new File(filePath).mkdirs();
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "trabajador_" + trabajador.getIdTrabajador() + ".pdf"));

            //Abrimos archivo pdf para ingresar los datos.
            document.open();

            /**
             * Inicio Titulo *
             */
            Paragraph saltoDeLinea = new Paragraph();

            Paragraph titulo = new Paragraph("Liquidacion de sueldo mensual ", paragraphFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setIndentationLeft(50);
            titulo.setIndentationRight(50);
            titulo.setSpacingAfter(10);

            document.add(titulo);

            /**
             * Fin titulo*
             */
            document.add(saltoDeLinea);

            /**
             * Inicio Cabecero*
             */
            Paragraph cabecero = new Paragraph("Datos del trabajador", paragraphFont);

            document.add(cabecero);

            PdfPTable tablaDatos = new PdfPTable(2);

            //Configurar la tabla.
            float[] anchoColumnas = {2f, 2f, 2f, 2f, 2f, 2f, 2f};

            tablaDatos.setWidthPercentage(100f);
            tablaDatos.getDefaultCell().setBorder(0);
            tablaDatos.setSpacingBefore(10f);
            tablaDatos.setSpacingAfter(10);

            tablaDatos.addCell("Nombre : " + trabajador.getNombre() + " " + trabajador.getApellido());

            tablaDatos.addCell("Rut : " + trabajador.getRut());
            tablaDatos.addCell("AFP : " + trabajador.getAfp());
            tablaDatos.addCell("Salud : " + trabajador.getSalud());
            
            
            
            
            
            //String fechaPeriodo = new SimpleDateFormat("MMMM").format(fechaConcreta);
            
            
            
            
            tablaDatos.addCell("Periodo de remuneracion : "+ new SimpleDateFormat("MMMM").format(liquidacion.getFechaDocumento().getTime()));
            tablaDatos.addCell("Año : " + new SimpleDateFormat("yyyy").format(liquidacion.getFechaDocumento().getTime()));
            
            document.add(tablaDatos);

            /**
             * Fin Cabecero*
             */
            
            document.add(saltoDeLinea);
            document.add(saltoDeLinea);
            Paragraph tituloCuerpo = new Paragraph("Detalle Remuneracion", paragraphFont);
            
            document.add(tituloCuerpo);
            
            /**Inicio Cuerpo del documento**/    
     
            PdfPTable tablaCuerpo = new PdfPTable(2);
            
            tablaCuerpo.setWidthPercentage(100f);
            tablaCuerpo.getDefaultCell().setBorder(0);
            tablaCuerpo.setSpacingBefore(10f);
            tablaCuerpo.setSpacingAfter(10);
            
            tablaCuerpo.addCell("Sueldo Base");
            tablaCuerpo.addCell(LiquidacionDeSueldo.SUELDO_BASE.toString());
            
            tablaCuerpo.addCell("Dias Trabajados");
            tablaCuerpo.addCell(liquidacion.getDiasTrabajados().toString());
            
            
            tablaCuerpo.addCell("Otros Ingresos Imponibles");
            tablaCuerpo.addCell(liquidacion.getOtrosIngresosImponibles().toString());
            
            tablaCuerpo.addCell("Total Remuneracion Imponible");
            tablaCuerpo.addCell(liquidacion.getTotalRemuneracionImponible().toString());
            
            document.add(tablaCuerpo);
            
            document.add(saltoDeLinea);
            
            Paragraph titulo2Cuerpo = new Paragraph("Descuentos :", paragraphFont);
            
            document.add(titulo2Cuerpo);
            
            document.add(saltoDeLinea);
            
            PdfPTable tablaCuerpoDescuentos = new PdfPTable(2);
            
            tablaCuerpoDescuentos.setWidthPercentage(100f);
            tablaCuerpoDescuentos.getDefaultCell().setBorder(0);
            tablaCuerpoDescuentos.setSpacingBefore(10f);
            tablaCuerpoDescuentos.setSpacingAfter(10);
            
            tablaCuerpoDescuentos.addCell("Cotizacion Previsional AFP");
            tablaCuerpoDescuentos.addCell(liquidacion.getDescuentoCotizacionPrevisional().toString());
            
            tablaCuerpoDescuentos.addCell("Cotizacion Salud");
            tablaCuerpoDescuentos.addCell(liquidacion.getDescuentoCotizacionSalud().toString());
           
            
            tablaCuerpoDescuentos.addCell("Total Descuentos Previsionales");
            
            String descuentos = liquidacion.getDescuentoCotizacionPrevisional() + liquidacion.getDescuentoCotizacionSalud().toString();
            
            tablaCuerpoDescuentos.addCell(descuentos);
            
            document.add(tablaCuerpoDescuentos);
            
            document.add(saltoDeLinea);
            
            Paragraph titulo3Cuerpo = new Paragraph("Total :", paragraphFont);
            
            document.add(titulo3Cuerpo);
            
            document.add(saltoDeLinea);
            
            PdfPTable tablaCuerpoTotal = new PdfPTable(2);
            
            tablaCuerpoTotal.setWidthPercentage(100f);
            tablaCuerpoTotal.getDefaultCell().setBorder(0);
            tablaCuerpoTotal.setSpacingBefore(10f);
            tablaCuerpoTotal.setSpacingAfter(10);
            
            tablaCuerpoTotal.addCell("Alcance Liquido:");
            tablaCuerpoTotal.addCell(liquidacion.getAlcanceLiquido().toString());
            
            document.add(tablaCuerpoTotal);
           
            /**
             * Termino Cuerpo del documento*
             */
            
            document.add(saltoDeLinea);
            
            /**InicioFooter**/
            
            Paragraph parrafoConforme = new Paragraph("", paragraphFont);
            parrafoConforme.add(new Chunk
        ("Yo "+trabajador.getNombre() + " " + trabajador.getApellido() + " " + "certifico que he recibido de mi empleador " + liquidacion.getNOMBRE_EMPRESA() ));
            parrafoConforme.add(new Chunk
        (" a mi total y entera satisfacción el saldo líquido indicado en la presente liquidación, sin tener cargo ni"));
            parrafoConforme.add(new Chunk
        (" cobro posterior alguno que hacer, por los conceptos de esta liquidación."));
            
            
            document.add(parrafoConforme);
            
            /**Termino Footer**/
            
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            document.add(new Chunk(Chunk.NEWLINE));
            
            
            
            
            
            /**Firmas**/
           
//            Paragraph firmaTrabajador = new Paragraph(new Chunk("  Firma Trabajador  ").setUnderline(1.5f, 11));
//            firmaTrabajador.setAlignment(Element.ALIGN_LEFT);
//            
//            Paragraph firmaEmpleador = new Paragraph(new Chunk("  Firma Empleador  ").setUnderline(1.5f, 11));
//            firmaEmpleador.setAlignment(Element.ALIGN_RIGHT);
//            
//            Paragraph firmas = new Paragraph();
//            firmas.add(firmaTrabajador);
//            firmas.add(firmaEmpleador);
//            
//            
//            
//            
//            document.add(firmas);
            //document.add(firmaEmpleador);
            
            PdfPTable tablaFirmas = new PdfPTable(2);
            
            tablaFirmas.setWidthPercentage(100f);
            tablaFirmas.getDefaultCell().setBorder(0);
            tablaFirmas.setSpacingBefore(10f);
            tablaFirmas.setSpacingAfter(10);
            
            Paragraph firmaTrabajador = new Paragraph(new Chunk("  Firma Trabajador  ").setUnderline(1.5f, 11));
            firmaTrabajador.setAlignment(Element.ALIGN_JUSTIFIED);
            
            
            Paragraph firmaEmpleador = new Paragraph(new Chunk("  Firma Empleador  ").setUnderline(1.5f, 11));
            firmaEmpleador.setAlignment(Element.ALIGN_JUSTIFIED);
            
            tablaFirmas.addCell(firmaTrabajador);
            tablaFirmas.addCell(firmaEmpleador);
            
            
            
            
            
            document.add(tablaFirmas);
            
            /**Termino Firmas**/
            
            
            
            document.close();
            writer.close();

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}

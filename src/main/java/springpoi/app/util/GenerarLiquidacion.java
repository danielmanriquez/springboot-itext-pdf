package springpoi.app.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import springpoi.app.model.LiquidacionDeSueldo;
import springpoi.app.model.Trabajador;

@Slf4j
public class GenerarLiquidacion {
     

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static boolean crearLiquidacionTrabajador(Trabajador trabajador, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        
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
            
            
            
            
            tablaDatos.addCell("Periodo de remuneracion : "+ new SimpleDateFormat("MMMM").format(liquidacion.getFecha().getTime()));
            tablaDatos.addCell("Año : " + new SimpleDateFormat("yyyy").format(liquidacion.getFecha().getTime()));
            
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
            
            tablaCuerpo.addCell("Asignacion Familiar");
            tablaCuerpo.addCell(liquidacion.getAsignacionFamiliar().toString());
            
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
           
            tablaCuerpoDescuentos.addCell("Descuento Cotizacion Voluntaria");
            tablaCuerpoDescuentos.addCell(liquidacion.getDescuentoCotizacionVoluntaria().toString());
            
            tablaCuerpoDescuentos.addCell("Total Descuentos Previsionales");
            
            String descuentos = liquidacion.getDescuentoCotizacionPrevisional() + liquidacion.getDescuentoCotizacionSalud() + liquidacion.getDescuentoCotizacionVoluntaria().toString();
            
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
        ("Yo "+trabajador.getNombre() + " " + trabajador.getApellido() + " " + "certifico que he recibido de mi empleador " + "Nombre Empleador" ));
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

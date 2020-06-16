package springpoi.app.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import springpoi.app.model.Trabajador;

public class GenerarLiquidacion {

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static boolean crearLiquidacionTrabajador(Trabajador trabajador, ServletContext context, HttpServletRequest request, HttpServletResponse response) {

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
            
            tablaDatos.addCell("Periodo de remuneracion : " + "Junio");
            tablaDatos.addCell("Año : " + "2018"); 
            
            
            
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
            
            tablaCuerpo.addCell("");
            

            /**
             * Termino Cuerpo del documento*
             */
            document.close();
            writer.close();

            return true;

        } catch (Exception e) {

            return false;

        }

    }

}

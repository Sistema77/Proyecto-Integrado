package Proyecto.Java.Final.Controladores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IHistorialServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import Proyecto.Java.Final.Util.FechaHoraComparable;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HistoriaControlador {

    @Autowired
    private IUsuarioServicio usuarioServicio;
    
    @Autowired
    private ICuentaServicio cuentaServicio;
    
    @Autowired
    private IHistorialServicio historialServicio;
    
    private static final Logger logger = LoggerFactory.getLogger(HistoriaControlador.class);
    
    @GetMapping("/privada/historial/{id}")
    public String Simulador(@PathVariable long id, Model model, Authentication authentication) {
        try {
            // Busca la cuenta por ID
            CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
            
            List<FechaHoraComparable> lista = historialServicio.historial(cuentaDao);
            
            if (lista == null || lista.isEmpty()) {
                model.addAttribute("historial", null);
            } else {
                model.addAttribute("historial", lista);
            }
            
            //model.addAttribute("historial", lista);
            
            // Agrega el nombre de usuario al modelo
            model.addAttribute("nombreUsuario", authentication.getName());
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            // Añadir la lista al modelo
            //model.addAttribute("historial", lista);  

            return "historial"; 
        } catch (Exception e) {
            // Manejo de errores
            logger.error("Error en Simulador: " + e.getMessage(), e);
            return "home";
        }
    }
    
    @GetMapping("/privada/historial/{id}/pdf")
    @ResponseBody
    public void descargarHistorialPdf(@PathVariable long id, HttpServletResponse response) {
        try {
            CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
            List<FechaHoraComparable> lista = historialServicio.historial(cuentaDao);

            // Decirle al navegador que es un PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=historial.pdf");

            // Crear el Documento
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Historial de Transacciones"));

            // Columnas con los datos
            float[] columnWidths = {1, 5};
            PdfPTable table = new PdfPTable(columnWidths);
            table.addCell(new PdfPCell(new Paragraph("Fecha y Hora")));
            table.addCell(new PdfPCell(new Paragraph("Cantidad de Dinero")));

            // Para el formato de fecha/hora
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (FechaHoraComparable transacion : lista) {
            	String formattedDate = dateFormat.format(transacion.getFecha_Hora().getTime());
                table.addCell(new PdfPCell(new Paragraph(formattedDate)));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(transacion.getCantidadDinero()))));
            }

            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}

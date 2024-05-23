package Proyecto.Java.Final.Controladores;

import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.DTO.FacturaDTO;
import Proyecto.Java.Final.DTO.UsuarioDTO;
import Proyecto.Java.Final.Servicios.CuentaToDaoImple;
import Proyecto.Java.Final.Servicios.CuentaToDtoImple;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IFactruraServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FacturaControlador {
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private IFactruraServicio facturaServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(FacturaControlador.class);
	
	@GetMapping("/privada/factura/{id}")
	public String homefactura(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
            CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
            CuentaDTO cuenta = new CuentaDTO();
            CuentaToDtoImple toDto = new CuentaToDtoImple();
            
            cuenta = toDto.cuentaToDto(cuentaDao);
			
			model.addAttribute("name", authentication.getName());
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            model.addAttribute("cuenta", cuenta);
            
            return "factura";
         
        } catch (Exception e) {
            // Manejo de errores
            logger.error("Error en homefactura: " + e.getMessage(), e);
            model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "home"; 
        }
	}
	
	@PostMapping("/privada/factura/pago/{id}")
	public String pagoFactura(@PathVariable long id, FacturaDTO factura, Model model, Authentication authentication){
		try {
			CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
			CuentaDTO cuentaDto;
			CuentaToDtoImple cuentaToDto = new CuentaToDtoImple();
			
			cuentaDto = cuentaToDto.cuentaToDto(cuentaDao);
			
			facturaServicio.registrar(cuentaDto, factura);
			
			model.addAttribute("FacturaPagada", "Factura Pagada");
			
			return "home";
			
		}catch(Exception e) {
			 logger.error("Error en pagoFactura: " + e.getMessage(), e);
			 return null;
		}
	}
}

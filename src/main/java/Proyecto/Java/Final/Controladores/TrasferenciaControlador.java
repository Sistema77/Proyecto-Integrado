package Proyecto.Java.Final.Controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Proyecto.Java.Final.DTO.UsuarioDTO;
import Proyecto.Java.Final.Servicios.IMovimientoCuentaServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TrasferenciaControlador {
	@Autowired
	private IMovimientoCuentaServicio movimientoCuentaServicio;
	
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(TrasferenciaControlador.class);
	
	// Metodo para home de Trasferecia
	@GetMapping("/privada/administracion")
	public String homeTrasfetencia(Model model, HttpServletRequest request, Authentication authentication) {
		try {
			// Verifica si el usuario tiene el rol de administrador
            if(request.isUserInRole("ROLE_USER")) {
            	
                // foto para mostrar en la vista
                model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
                return "administracion";    
            } 
            return "home";
		}catch(Exception e) {
			logger.error("Error en listadoUsuarios: " + e.getMessage(), e);
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "home";
		}
	}
}

/*package Proyecto.Java.Final.Controladores;

import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.Servicios.CuentaToDtoImple;
import Proyecto.Java.Final.Servicios.ICreditoServicio;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.ICuentaToDto;
import Proyecto.Java.Final.Servicios.IHistorialServicio;
import Proyecto.Java.Final.Servicios.IMovimientoCuentaServicio;
import Proyecto.Java.Final.Servicios.ITransacionServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;

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
	public String Simulador(@PathVariable long id,Model model, Authentication authentication) {
		try {
			 // Busca la cuenta por ID
            CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
            
            List<Object> lista = new ArrayList<>();
            
            lista = historialServicio.historial(cuentaDao);
            
			// Agrega el nombre de usuario al modelo
            model.addAttribute("nombreUsuario", authentication.getName());
            model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));

            return "simulador"; 
		}catch(Exception e) {
			// Manejo de errores
            logger.error("Error en Simulador: " + e.getMessage(), e);
            return "home";
		}
	}
}*/

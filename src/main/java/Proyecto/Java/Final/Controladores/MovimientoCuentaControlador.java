package Proyecto.Java.Final.Controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.TransacionDTO;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import Proyecto.Java.Final.Servicios.TransacionToDaoImple;
import Proyecto.Java.Final.Servicios.TransacionToDtoImple;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MovimientoCuentaControlador {

	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(MovimientoCuentaControlador.class);
	
	@GetMapping("/privada/movimientocuenta/cajero/{id}")
	public String homemovimientocuenta(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			
			// Verifica si es usuario
            if(request.isUserInRole("ROLE_USER")) {
            	// Comprobar si el Usuario es el dueño de esa Cuenta
            	CuentaDAO Cuenta = new CuentaDAO();
            	Cuenta = cuentaServicio.buscarCuentaId(id);
            	UsuarioDAO UsuarioDAO = new UsuarioDAO();
            	UsuarioDAO = Cuenta.getUsuario();
            	
            	if(UsuarioDAO != null) {
            		model.addAttribute("cuenta", Cuenta);
            		model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            		return "cajero";
            	}
            }
            // foto para mostrar en la vista
        	model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
        	model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
            return "home";
            
		}catch(Exception e) {
			logger.error("Error en homemovimientocuenta: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
		
	}
	
	@GetMapping("/privada/movimientocuenta/ingreso/{id}")
	public String ingresar(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			// Verifica si es usuario
            if(request.isUserInRole("ROLE_USER")) {
            	// Comprobar si el Usuario es el dueño de esa Cuenta
            	CuentaDAO Cuenta = new CuentaDAO();
            	Cuenta = cuentaServicio.buscarCuentaId(id);
            	UsuarioDAO UsuarioDAO = new UsuarioDAO();
            	UsuarioDAO = Cuenta.getUsuario();
            	
            	if(UsuarioDAO != null) {
            		model.addAttribute("cuenta", Cuenta);
            		model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            		return "ingreso";
            	}
            }
            // foto para mostrar en la vista
        	model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
        	model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
            return "home";
            
		}catch(Exception e) {
			logger.error("Error en homemovimientocuenta: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}
	
	@GetMapping("/privada/movimientocuenta/pago/{id}")
	public String sacar(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			// Verifica si es usuario
            if(request.isUserInRole("ROLE_USER")) {
            	// Comprobar si el Usuario es el dueño de esa Cuenta
            	CuentaDAO Cuenta = new CuentaDAO();
            	Cuenta = cuentaServicio.buscarCuentaId(id);
            	UsuarioDAO UsuarioDAO = new UsuarioDAO();
            	UsuarioDAO = Cuenta.getUsuario();
            	
            	if(UsuarioDAO != null) {
            		model.addAttribute("cuenta", Cuenta);
            		model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            		return "pago";
            	}
            }
            // foto para mostrar en la vista
        	model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
        	model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
            return "home";
            
		}catch(Exception e) {
			logger.error("Error en homemovimientocuenta: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}
	
	@GetMapping("/privada/movimientocuenta/pago/realizar/{id}")
	public String realizarPago(@PathVariable long id, TransacionDTO trasacion, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			CuentaDAO cuenta = new CuentaDAO();
			
			cuenta = cuentaServicio.buscarCuentaId(id);
			
			cuenta.setSaldo(cuenta.getSaldo()-trasacion.getCantidadDinero());
			
			// Pasar de DTO a DAO
			TransacionToDaoImple e = new TransacionToDaoImple();
			TransacionDAO trasacionDao = e.trasacionToDao(trasacion);
			
			// Guardar Cuenta y el Trasaccion
			
			//////////////////)
			
		}catch(Exception e) {
			logger.error("Error en realizarPago: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}
	
	@GetMapping("/privada/movimientocuenta/ingreso/realizar/{id}")
	public String realizarIngreso(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			
		}catch(Exception e) {
			logger.error("Error en realizarIngreso: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}
	
}

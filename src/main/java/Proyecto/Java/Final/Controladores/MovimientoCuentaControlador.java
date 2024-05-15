package Proyecto.Java.Final.Controladores;

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
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.DTO.TransacionDTO;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IMovimientoCuentaServicio;
import Proyecto.Java.Final.Servicios.ITransacionServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import Proyecto.Java.Final.Servicios.MovimientoCuentaToDaoImple;
import Proyecto.Java.Final.Servicios.TransacionToDaoImple;
import Proyecto.Java.Final.Servicios.TransacionToDtoImple;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MovimientoCuentaControlador {

	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private IMovimientoCuentaServicio movimientoCuentaServicio;
	
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
	
	@GetMapping("/privada/movimientocuenta/retirar/{id}")
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
            		return "retirar";
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
	
	///////////////////////////////////
	@PostMapping("/privada/movimientocuenta/retirar/realizar/{id}")
	public String realizarPago(@PathVariable long id, MovimientoCuentaDTO trasacion, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			if(trasacion.getCantidad_dinero() < 0 ) {
				
				CuentaDAO cuenta = new CuentaDAO();
				
				cuenta = cuentaServicio.buscarCuentaId(id);
				//Comprobar si la cuenta tiene dinero suficiente
				if(cuentaServicio.comprobacionDineroCuenta(cuenta.getNumeroCuenta(), trasacion.getCantidad_dinero())) {
					double dinero = cuenta.getSaldo()-trasacion.getCantidad_dinero();
					cuentaServicio.sacarDineroCuenta(cuenta.getNumeroCuenta(), dinero);

					//Datos de la MovimientoCuenta
				
					
					// Guardar Cuenta y el Trasaccion
					cuentaServicio.guardarCuenta(cuenta);
					movimientoCuentaServicio.registrar(trasacion);
		
					model.addAttribute("info", "Pago Realizado");
					return "home";
				}else {
					model.addAttribute("error", "Pago No Realizado");
					return "home";
				}
				
				
			}
			
			model.addAttribute("error", "No puede retirara esa cantidad");
			return "cajero";
			
		}catch(Exception e) {
			logger.error("Error en realizarPago: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}
	
	/*@GetMapping("/privada/movimientocuenta/ingreso/realizar/{id}")
	public String realizarIngreso(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			
		}catch(Exception e) {
			logger.error("Error en realizarIngreso: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}*/
	
}

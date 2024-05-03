package Proyecto.Java.Final.Controladores;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.DTO.UsuarioDTO;
import Proyecto.Java.Final.Repositorio.MovimoentoCuentaRepositorio;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.IMovimientoCuentaServicio;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TrasferenciaControlador {
	//@Autowired
	//private IMovimientoCuentaServicio movimientoCuentaServicio;
	
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private MovimoentoCuentaRepositorio movimoentoRepositorio;
	
	
	private static final Logger logger = LoggerFactory.getLogger(TrasferenciaControlador.class);
	
	// Metodo para home de Trasferecia
	@GetMapping("/privada/trasferencia/{id}")
	public String homeTrasferencia(@PathVariable long id, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			// Verifica si el usuario tiene el rol de administrador
            if(request.isUserInRole("ROLE_USER")) {
            	// Comprobar si el Usuario es el dueño de esa Cuenta
            	CuentaDAO Cuenta = new CuentaDAO();
            	Cuenta = cuentaServicio.buscarCuentaId(id);
            	UsuarioDAO UsuarioDAO = new UsuarioDAO();
            	UsuarioDAO = Cuenta.getUsuario();
            	
            	if(UsuarioDAO != null) {
            		model.addAttribute("cuenta", Cuenta);
            		model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            		return "trasferencia";
            	}
                // foto para mostrar en la vista
            	model.addAttribute("foto", usuarioServicio.verFoto(authentication.getName()));
            	model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
                return "home";    
            } 
            return "home";
		}catch(Exception e) {
			logger.error("Error en homeTrasferencia: " + e.getMessage(), e);
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
            return "home";
		}
	}
	
	@PostMapping("/privada/trasferencia/cuenta/{id}")
	public String hacerTrasferencia(@PathVariable long id, @ModelAttribute MovimientoCuentaDTO cuentaDto, Model model, HttpServletRequest request, Authentication authentication) {
		try {
			
			// Comprobar si la Cuenta Existe
			String cuent = cuentaDto.getCuenta_envia();
			boolean t = cuentaServicio.numeroCuentaExiste(cuent);
			System.out.println(cuentaDto.toString());
			if(t && !cuentaDto.getCuenta_envia().equals(cuentaServicio.buscarCuentaId(id).getNumeroCuenta())) { /////////////
				// Comprobar si la Cuenta tiene suficiente Saldo
				if(cuentaServicio.comprobacionDineroCuenta(cuentaServicio.buscarCuentaId(id).getNumeroCuenta(), cuentaDto.getCantidad_dinero())) {
					// Realizar la trasferencia
					MovimientoCuentaDAO trasferencia = new MovimientoCuentaDAO();
					
					trasferencia.setCantidad_dinero(cuentaDto.getCantidad_dinero());
					trasferencia.setCuenta(cuentaServicio.buscarCuentaId(id));
					trasferencia.setDescripcion(cuentaDto.getDescripcion());
					trasferencia.setFecha_Hora(Calendar.getInstance());
					trasferencia.setTipo_movimiento(cuentaDto.getTipo_movimiento());
					trasferencia.setCuenta_envia(cuentaDto.getCuenta_envia());
					
					movimoentoRepositorio.save(trasferencia);
				}
				
			}
			
			
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "home";
		}catch(Exception e) {
			logger.error("Error en homeTrasferencia: " + e.getMessage(), e);
			model.addAttribute("error", "Error al procesar la solicitud. Por favor, inténtelo de nuevo.");
			return "home";
		}
	}
	
}

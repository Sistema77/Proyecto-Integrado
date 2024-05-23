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

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.Servicios.CreditoToDaoImple;
import Proyecto.Java.Final.Servicios.CuentaToDtoImple;
import Proyecto.Java.Final.Servicios.ICreditoServicio;
import Proyecto.Java.Final.Servicios.ICuentaServicio;
import Proyecto.Java.Final.Servicios.ICuentaToDto;
import Proyecto.Java.Final.Servicios.IUsuarioServicio;

@Controller
public class SimuladorControlador {
	@Autowired
    private IUsuarioServicio usuarioServicio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private ICreditoServicio creditoServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(SimuladorControlador.class);
	
	@GetMapping("/privada/simulador/{id}")
	public String Simulador(@PathVariable long id,Model model, Authentication authentication) {
		try {
			 // Busca la cuenta por ID y lo convierte en un DTO para mostrar en el formulario
            CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
            ICuentaToDto cuentaToDto = new CuentaToDtoImple();
            CuentaDTO cuentaDto = cuentaToDto.cuentaToDto(cuentaDao);
            
            model.addAttribute("cuenta", cuentaDto);
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
	
	@PostMapping("/privada/simulador/generar/{id}")
	public String generar(@PathVariable long id,Model model, Authentication authentication, CreditoDTO credito, int meses) {
		try {
			//Pasar de DTO a DAO
			CuentaDAO cuentaDao = cuentaServicio.buscarCuentaId(id);
			CreditoDAO dao = new CreditoDAO();
			CreditoToDaoImple creditoToDao = new CreditoToDaoImple();
			
			//Guardar Simulación Credito
			creditoServicio.registrar(credito, id, meses, cuentaDao);
			
			model.addAttribute("info", "Simulacion Aceptada");
			return "home";
		}catch(Exception e) {
			logger.error("Error en Simulador: " + e.getMessage(), e);
            return "home";
		}
	}
}

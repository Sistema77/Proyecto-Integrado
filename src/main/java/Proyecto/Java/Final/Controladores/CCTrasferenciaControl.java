package Proyecto.Java.Final.Controladores;

import java.util.Calendar;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.DTO.TransacionDTO;
import Proyecto.Java.Final.Servicios.TransacionToDaoImple;
import jakarta.servlet.http.HttpServletRequest;

public class CCTrasferenciaControl {
///////////////////////////////////
	/*@PostMapping("/privada/movimientocuenta/retirar/realizar/{id}")
	public String realizarPago(@PathVariable long id, TransacionDTO trasacion, Model model, HttpServletRequest request,
			Authentication authentication) {
		try {
			if (trasacion.getCantidadDinero() > 0) {
				CuentaDAO cuenta = new CuentaDAO();

				cuenta = cuentaServicio.buscarCuentaId(id);

				cuenta.setSaldo(cuenta.getSaldo() - trasacion.getCantidadDinero());

// Pasar de DTO a DAO
				TransacionToDaoImple e = new TransacionToDaoImple();
				TransacionDAO trasacionDao = e.trasacionToDao(trasacion);

// Guardar Cuenta y el Trasaccion
				cuentaServicio.guardarCuenta(cuenta);
				transacionServicio.guardar(trasacionDao);

				model.addAttribute("info", "Pago Realizado");
				return "home";
			}

			model.addAttribute("error", "No puede retirara esa cantidad");
			return "cajero";

		} catch (Exception e) {
			logger.error("Error en realizarPago: " + e.getMessage(), e);
			model.addAttribute("error", "Erro no tiene permisos para acceder a esta cuenta");
			return "home";
		}
	}

	/*
	 * @GetMapping("/privada/movimientocuenta/ingreso/realizar/{id}") public String
	 * realizarIngreso(@PathVariable long id, Model model, HttpServletRequest
	 * request, Authentication authentication) { try {
	 * 
	 * }catch(Exception e) { logger.error("Error en realizarIngreso: " +
	 * e.getMessage(), e); model.addAttribute("error",
	 * "Erro no tiene permisos para acceder a esta cuenta"); return "home"; } }
	 */

}

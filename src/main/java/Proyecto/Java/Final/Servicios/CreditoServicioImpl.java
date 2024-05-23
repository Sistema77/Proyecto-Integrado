package Proyecto.Java.Final.Servicios;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.Repositorio.CreditoRepositorio;
@Service
public class CreditoServicioImpl implements ICreditoServicio {

	@Autowired
	private CreditoRepositorio creditoRepositorio;
	
	private static final Logger logger = LoggerFactory.getLogger(CreditoServicioImpl.class);
	
	@Override
	public CreditoDTO registrar(CreditoDTO credito, long id, int meses, CuentaDAO cuenta) {
		try {
			// Pasar de DTO a DAO
			CreditoDAO creditoDao = new CreditoDAO();
			
			ICreditoToDao toDao = new CreditoToDaoImple();
			ICuentaToDao cuentaToDao = new CuentaToDaoImple();
			
			ICuentaServicio servicioCuenta = new CuentaServicioImpl();
			
			// Rellenar Campos
			credito.setEstadoPrestamo("Simulado");
			credito.setCuenta(cuenta);
			credito.setFch_inicio(Calendar.getInstance());
			credito.setFch_final(calcularFecha(credito, meses));
			
			if(cuenta.getConNomina()) {
				credito.setTasaInteres(2.5);
			}else {
				credito.setTasaInteres(3.5);
			}
			
			double cuotaMensual = calcularCuotaMensual(credito.getTasaInteres(), credito.getCantidadPrestamo(), meses);
			credito.setCuotaMensual(cuotaMensual);
			
			creditoDao = toDao.creditoToDao(credito);
			
			// Guardar en BD
			creditoRepositorio.save(creditoDao);
			
			logger.info("Credito de la cuneta:  " + credito.getCuenta() + " [REGISTRADO]");
			
			return credito;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
		
	}

	@Override
	public Calendar calcularFecha(CreditoDTO credito, int meses) {
		try {
			
			Calendar nuevaFecha = (Calendar) credito.getFch_inicio().clone();
            
            // Sumar los meses
            nuevaFecha.add(Calendar.MONTH, meses);
			
			return nuevaFecha;
			
		}catch(Exception e) {
			logger.error("Error en calcularFecha: " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public double calcularCuotaMensual(double tasaInteres, double cantidad, int meses) {
		try {
			double resul;
			
			resul = (cantidad * tasaInteres) / meses;
			
			return resul;
		}catch(Exception e) {
			logger.error("Error en calcularCuotaMensual: " + e.getMessage(), e);
			return 0;
		}
	}

	
}

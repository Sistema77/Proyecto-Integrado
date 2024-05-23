package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DTO.CuentaDTO;
import Proyecto.Java.Final.DTO.FacturaDTO;
import Proyecto.Java.Final.Repositorio.FacturaRepositorio;

@Service
public class FacturaServicioImpl implements IFactruraServicio{

	@Autowired
	private FacturaRepositorio facturaRepositorio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	private static final Logger logger = LoggerFactory.getLogger(FacturaServicioImpl.class);
	
	@Override
	public void registrar(CuentaDTO cuenta, FacturaDTO factura) {
		try {
			
			if(cuentaServicio.comprobacionDineroCuenta(cuenta.getNumeroCuenta(), factura.getCantidadDinero())) {
				// Guardar Factura
				IFacturaToDao facturaToDao = new FacturaToDaoImpl();
				FacturaDAO facturaDao = new FacturaDAO();
				
				factura.setCuenta(cuenta);
				factura.setFecha_Hora(Calendar.getInstance());
				factura.setTipoFactura("Pago");
				
				facturaDao =  facturaToDao.facturaToDao(factura);
				
				facturaRepositorio.save(facturaDao);
				
				// Retirar dinero cuenta
				cuentaServicio.sacarDineroCuenta(cuenta.getNumeroCuenta(), factura.getCantidadDinero());
				
				logger.info("Factura Guardad");
			}
		}catch(Exception e) {
			logger.error("[Error] registrar FacturaServicioImpl: " + e);
		}
		
	}

	@Override
	public List<FacturaDAO> listaFactura(CuentaDAO cuenta) {
		try {
			List<FacturaDAO> listaFactura = new ArrayList<>();
			
			listaFactura = facturaRepositorio.findByCuenta(cuenta);
			
			return listaFactura;
		}catch(Exception e) {
			logger.error("[Error] listaFactura FacturaServicioImpl: " + e);
			return null;
		}
	}

}

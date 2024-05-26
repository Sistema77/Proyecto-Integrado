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
import Proyecto.Java.Final.Repositorio.CuentaRepositorio;
import Proyecto.Java.Final.Repositorio.FacturaRepositorio;

@Service
public class FacturaServicioImpl implements IFactruraServicio{

	@Autowired
	private FacturaRepositorio facturaRepositorio;
	
	@Autowired
	private ICuentaServicio cuentaServicio;
	
	@Autowired
	private CuentaRepositorio cuentaRepositorio;
	
	private static final Logger logger = LoggerFactory.getLogger(FacturaServicioImpl.class);
	
	@Override
	public void registrar(CuentaDTO cuenta, FacturaDTO factura) {
		try {
			 if (cuentaServicio.comprobacionDineroCuenta(cuenta.getNumeroCuenta(), factura.getCantidadDinero())) {
	                // Convertir CuentaDTO a CuentaDAO
	                CuentaDAO cuentaDao = new CuentaDAO();
	                cuentaDao.setNumeroCuenta(cuenta.getNumeroCuenta());
	                cuentaDao.setSaldo(cuenta.getSaldo());
	                cuentaDao.setFch_apertura(cuenta.getFch_apertura());
	                cuentaDao.setConNomina(cuenta.getConNomina());
	                // Asumiendo que el usuario ya está configurado en CuentaDTO
	                cuentaDao.setUsuario(cuenta.getUsuario());

	                // Guardar CuentaDAO si no está ya persistida
	                if (cuenta.getId_cuenta() == null || !cuentaRepositorio.existsById(cuenta.getId_cuenta())) {
	                    cuentaDao = cuentaRepositorio.save(cuentaDao);
	                } else {
	                    cuentaDao.setId_cuenta(cuenta.getId_cuenta());
	                }

	                // Convertir FacturaDTO a FacturaDAO
	                IFacturaToDao facturaToDao = new FacturaToDaoImpl();
	                FacturaDAO facturaDao = new FacturaDAO();
	                factura.setCuenta(cuenta);
	                //factura.setFecha_Hora(Calendar.getInstance());
	                factura.setTipoFactura("Pago");

	                facturaDao = facturaToDao.facturaToDao(factura);

	                // Configurar la CuentaDAO persistida en FacturaDAO
	                facturaDao.setCuenta(cuentaDao);

	                // Guardar FacturaDAO
	                facturaRepositorio.save(facturaDao);

	                // Retirar dinero de la cuenta
	                cuentaServicio.sacarDineroCuenta(cuenta.getNumeroCuenta(), factura.getCantidadDinero());

	                logger.info("Factura Guardada");
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

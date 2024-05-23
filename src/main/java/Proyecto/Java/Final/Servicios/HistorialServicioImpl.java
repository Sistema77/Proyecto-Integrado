/*package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;

@Service
public class HistorialServicioImpl implements IHistorialServicio{
	
	@Autowired
	private IMovimientoCuentaServicio movimientoCuentaServicio;
	
	@Autowired
	private ITransacionServicio transacionServicio;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HistorialServicioImpl.class);

	@Override
	public List<Object> historial(CuentaDAO cuenta) {
		try {
			// Listas
			List<Object> listaHistorial = new ArrayList<>();
			
			List<FacturaDAO> listaFactura = new ArrayList<>();
			
			List<MovimientoCuentaDAO> listaMovimientoCuenta = new ArrayList<>();
			
			List<TransacionDAO> listaTrasancion = new ArrayList<>();
			
			// Rellenar las listas
			listaMovimientoCuenta = movimientoCuentaServicio.listaMovimiento(cuenta);
			listaTrasancion = transacionServicio.listaTransaciones(cuenta);
			
			
			// Añadir todo a una lista 
			listaHistorial.add(listaMovimientoCuenta);
			listaHistorial.add(listaTrasancion);
			
			// Ordenar lista
			listaHistorial.
		}catch(Exception e) {
			logger.error("Error en historial: " + e.getMessage(), e);
			return null;
		}
	}
	
	
	
	
}*/

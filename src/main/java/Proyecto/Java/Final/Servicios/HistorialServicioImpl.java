package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.FacturaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.FacturaDTO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.DTO.TransacionDTO;
import Proyecto.Java.Final.Util.FechaHoraComparable;

@Service
public class HistorialServicioImpl implements IHistorialServicio{
	
	@Autowired
	private IMovimientoCuentaServicio movimientoCuentaServicio;
	
	@Autowired
	private ITransacionServicio transacionServicio;
	
	@Autowired
	private IFactruraServicio facturaServicio;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HistorialServicioImpl.class);

	@Override
	public List<FechaHoraComparable> historial(CuentaDAO cuenta) {
		try {
			// Listas
			List<FechaHoraComparable> listaHistorial = new ArrayList<>();
			
			List<FacturaDAO> listaFactura = new ArrayList<>();
			List<FacturaDTO> listaFacturaDto = new ArrayList<>();
			
			List<MovimientoCuentaDAO> listaMovimientoCuenta = new ArrayList<>();
			List<MovimientoCuentaDTO> listaMovimientoCuentaDto = new ArrayList<>();
			
			List<TransacionDAO> listaTrasancion = new ArrayList<>();
			List<TransacionDTO> listaTrasancionDto = new ArrayList<>();
			
			// Rellenar las listas
			listaMovimientoCuenta = movimientoCuentaServicio.listaMovimiento(cuenta);
			listaTrasancion = transacionServicio.listaTransaciones(cuenta);
			listaFactura = facturaServicio.listaFactura(cuenta);
			
			// Pasar DAO a DTO
			
			IFacturaToDto facturaToDto = new FacturaToDtoImpl();
			IMovimientoCuentaToDto movimientoToDto = new MovimientoCuentaToDtoImple();
			ITransacionToDto transacionToDto = new TransacionToDtoImple();
			
			listaFacturaDto = facturaToDto.listaFacturaToDto(listaFactura);
			listaMovimientoCuentaDto = movimientoToDto.listaMovimientoCuentaToDto(listaMovimientoCuenta);
			listaTrasancionDto = transacionToDto.listaTrasacionDao(listaTrasancion);
			
			
			// Añadir todo a una lista 
			listaHistorial.addAll(listaFacturaDto);
			listaHistorial.addAll(listaMovimientoCuentaDto);
			listaHistorial.addAll(listaTrasancionDto);
			// Ordenar lista
			
			 Collections.sort(listaHistorial);
			
			//listaHistorial.
			return listaHistorial;
		}catch(Exception e) {
			logger.error("Error en historial: " + e.getMessage(), e);
			return null;
		}
	}
	
	
	
	
}

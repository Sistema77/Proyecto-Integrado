package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;

public class MovimientoCuentaToDaoImple implements IMovimientoCuentaToDao{

	private static final Logger logger = LoggerFactory.getLogger(MovimientoCuentaToDaoImple.class);
	
	@Override
	public MovimientoCuentaDAO movimientoCuentaToDao(MovimientoCuentaDTO movimientoCuentaDto) {
		try {
			MovimientoCuentaDAO movimientoCuentaDao = new MovimientoCuentaDAO();
			
			movimientoCuentaDao.setCantidad_dinero(movimientoCuentaDto.getCantidad_dinero());
			movimientoCuentaDao.setCuenta(movimientoCuentaDto.getCuenta());
			movimientoCuentaDao.setFecha_Hora(movimientoCuentaDto.getFecha_Hora());
			movimientoCuentaDao.setId_movimiento(movimientoCuentaDto.getId_movimiento());
			movimientoCuentaDao.setTipo_movimiento(movimientoCuentaDto.getTipo_movimiento());
			
			return movimientoCuentaDao;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<MovimientoCuentaDAO> listMovimientoCuentaToDao(List<MovimientoCuentaDTO> listaMovimientoCuentaDTO) {
		try {
			List<MovimientoCuentaDAO> listaMovimientoDAO = new ArrayList<>();
			
			for(MovimientoCuentaDTO movimientoDTO : listaMovimientoCuentaDTO) {
				listaMovimientoDAO.add(movimientoCuentaToDao(movimientoDTO));
			}
			
			return listaMovimientoDAO;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}
	
}

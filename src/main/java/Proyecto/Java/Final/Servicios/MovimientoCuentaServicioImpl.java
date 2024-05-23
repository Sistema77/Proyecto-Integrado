package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DAO.UsuarioDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;
import Proyecto.Java.Final.Repositorio.MovimoentoCuentaRepositorio;

@Service
public class MovimientoCuentaServicioImpl implements IMovimientoCuentaServicio{

	//Repositorios
	
	@Autowired
	private MovimoentoCuentaRepositorio movimientoCRepositorio;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MovimientoCuentaServicioImpl.class);
	//Metodos
	
	@Override
	public MovimientoCuentaDTO registrar(MovimientoCuentaDTO movimientoCuentaDto) {
		try {
			// Pasar DTO a DAO
			
			MovimientoCuentaDAO movimientoDAO = new MovimientoCuentaDAO();  

			IMovimientoCuentaToDao movimientoCuentaToDAO = new MovimientoCuentaToDaoImple();
			
			movimientoDAO = movimientoCuentaToDAO.movimientoCuentaToDao(movimientoCuentaDto);

			movimientoCRepositorio.save(movimientoDAO);
			logger.info("Movimiento " + movimientoDAO.getId_movimiento() + " REGISTRADO");
			
			return movimientoCuentaDto;
			
		} catch (Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<MovimientoCuentaDAO> listaMovimiento(CuentaDAO cuenta) {
		try {
			List<MovimientoCuentaDAO> listaDao = new ArrayList<>();
			
			listaDao = movimientoCRepositorio.findByCuenta(cuenta);
			
			return listaDao;
		}catch(Exception e) {
			logger.error("Error en listaMovimiento: " + e.getMessage(), e);
			return null;
		}
	}
	
	
}

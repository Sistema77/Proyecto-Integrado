package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.MovimientoCuentaDAO;
import Proyecto.Java.Final.DTO.MovimientoCuentaDTO;

public class MovimientoCuentaToDtoImple implements IMovimientoCuentaToDto{

	private static final Logger logger = LoggerFactory.getLogger(MovimientoCuentaToDtoImple.class);
	
	@Override
	public MovimientoCuentaDTO movimientoCuentaDto(MovimientoCuentaDAO u) {
		try {
            // Crear un nuevo objeto DTO y copiar los atributos al DAO
			MovimientoCuentaDTO dto = new MovimientoCuentaDTO();
			
			dto.setCantidad_dinero(u.getCantidad_dinero());
			dto.setCuenta(u.getCuenta());
			dto.setDescripcion(u.getDescripcion());
			dto.setFecha_Hora(u.getFecha_Hora());
			dto.setId_movimiento(u.getId_movimiento());
			dto.setTipo_movimiento(u.getTipo_movimiento());
            
            return dto;
        } catch (Exception e) {
        	logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
        }
	}

	@Override
	public List<MovimientoCuentaDTO> listaMovimientoCuentaToDto(List<MovimientoCuentaDAO> listaMovimientoCuenta) {
		try {
			List<MovimientoCuentaDTO> listaDto = new ArrayList<>();
			
			for(MovimientoCuentaDAO u : listaMovimientoCuenta) {
				listaDto.add(movimientoCuentaDto(u));
			}
			
			return listaDto;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
		}
	}

}

package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.TransacionDTO;

public class TransacionToDtoImple implements ITransacionToDto{

	private static final Logger logger = LoggerFactory.getLogger(TransacionToDtoImple.class);
	
	@Override
	public TransacionDTO trasacionToDao(TransacionDAO traDao) {
		try {
			
			TransacionDTO dto = new TransacionDTO();
			
			dto.setCantidadDinero(traDao.getCantidadDinero());
			dto.setCuenta(traDao.getCuenta());
			dto.setFecha_Hora(traDao.getFecha_Hora());
			dto.setTipoTrasa(traDao.getTipoTrasa());
			
			return dto;
			
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
		}
	}

	@Override
	public List<TransacionDTO> listaTrasacionDao(List<TransacionDAO> lista) {
		try {
			List<TransacionDTO> listaDto = new ArrayList<>();
			
			for(TransacionDAO dao : lista) {
				listaDto.add(trasacionToDao(dao));
			}
			
			return listaDto;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
		}
	}

}

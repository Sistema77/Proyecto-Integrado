package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.DTO.TransacionDTO;

public class TransacionToDaoImple implements ITransacionToDao{

	private static final Logger logger = LoggerFactory.getLogger(TransacionToDaoImple.class);

	@Override
	public TransacionDAO trasacionToDao(TransacionDTO traDto) {
		try {
			TransacionDAO dao = new TransacionDAO();
			
			dao.setCantidadDinero(traDto.getCantidadDinero());
			dao.setCuenta(traDto.getCuenta());
			dao.setFecha_Hora(traDto.getFecha_Hora());
			dao.setDescripcion(traDto.getDescripcion());
			dao.setCuenta_enviada(traDto.getCuenta_enviada());
			
			return dao;
			
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<TransacionDAO> listaTrasacionDao(List<TransacionDTO> lista) {
		try {
			List<TransacionDAO> listaDao = new ArrayList<>();
			
			for(TransacionDTO dto : lista) {
				listaDao.add(trasacionToDao(dto));
			}
			
			return listaDao;
			
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}
}

package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;

public class CreditoToDaoImple implements ICreditoToDao{

	private static final Logger logger = LoggerFactory.getLogger(CreditoToDaoImple.class);
	
	@Override
	public CreditoDAO creditoToDao(CreditoDTO dto) {
		try {
			CreditoDAO dao = new CreditoDAO();
			
			dao.setCantidadPrestamo(dto.getCantidadPrestamo());
			dao.setCuenta(dto.getCuenta());
			dao.setCuotaMensual(dto.getCuotaMensual());
			dao.setEstadoPrestamo(dto.getEstadoPrestamo());
			dao.setFch_final(dto.getFch_final());
			dao.setFch_inicio(dto.getFch_inicio());
			dao.setTasaInteres(dto.getTasaInteres());
			dao.setTipoPrestamo(dto.getTipoPrestamo());
			
			return dao;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public List<CreditoDAO> listCreditoDao(List<CreditoDTO> listaDto) {
		try {
			List<CreditoDAO> listDao = new ArrayList<>();
			
			for(CreditoDTO u : listaDto) {
				listDao.add(creditoToDao(u));
			}
			
			return listDao;
			
		}catch(Exception e) {
			logger.error("Error en registrar lista: " + e.getMessage(), e);
			return null;
		}
	}

}

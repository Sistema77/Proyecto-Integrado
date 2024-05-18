package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;

public class CreditoToDtoImple implements ICreditoToDto{

	private static final Logger logger = LoggerFactory.getLogger(CreditoToDtoImple.class);
	@Override
	public CreditoDTO creditoToDto(CreditoDAO dao) {
		try{
			CreditoDTO dto = new CreditoDTO();
			
			dto.setCantidadPrestamo(dao.getCantidadPrestamo());
			dto.setCuenta(dao.getCuenta());
			dto.setCuotaMensual(dao.getCuotaMensual());
			dto.setEstadoPrestamo(dao.getEstadoPrestamo());
			dto.setFch_final(dao.getFch_final());
			dto.setFch_inicio(dao.getFch_inicio());
			dto.setTasaInteres(dao.getTasaInteres());
			dto.setTipoPrestamo(dao.getTipoPrestamo());
			
			return dto;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
		}
	}

	@Override
	public List<CreditoDTO> listCreditoDto(List<CreditoDAO> listaDao) {
		try {
			List<CreditoDTO> listDto = new ArrayList<>();
			
			for(CreditoDAO u : listaDao) {
				listDto.add(creditoToDto(u));
			}
			
			return listDto;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
            return null;
		}
	}

}

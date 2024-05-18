package Proyecto.Java.Final.Servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Proyecto.Java.Final.DAO.CreditoDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;
import Proyecto.Java.Final.Repositorio.CreditoRepositorio;

public class CreditoServicioImpl implements ICreditoServicio {

	@Autowired
	private CreditoRepositorio creditoRepositorio;
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);
	@Override
	public CreditoDTO registrar(CreditoDTO credito) {
		try {
			//Pasar de DTO a DAO
			CreditoDAO creditoDao = new CreditoDAO();
			
			ICreditoToDao toDao = new CreditoToDaoImple();
			
			creditoDao = toDao.creditoToDao(credito);
			
			creditoRepositorio.save(creditoDao);
			
			logger.info("Credito de la cuneta:  " + credito.getCuenta() + " [REGISTRADO]");
			
			return credito;
		}catch(Exception e) {
			logger.error("Error en registrar: " + e.getMessage(), e);
			return null;
		}
		
	}

}

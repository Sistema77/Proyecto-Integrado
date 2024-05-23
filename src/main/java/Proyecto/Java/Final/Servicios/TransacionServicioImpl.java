package Proyecto.Java.Final.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.Repositorio.TransacionRepositorio;

@Service
public class TransacionServicioImpl implements ITransacionServicio{

	@Autowired
	private TransacionRepositorio transacionRepositorio;
	
	private static final Logger logger = LoggerFactory.getLogger(TransacionServicioImpl.class);
	
	@Override
	public TransacionDAO guardar(TransacionDAO dao) {
		try {
			transacionRepositorio.save(dao);
			return dao;
		}catch(Exception e) {
			logger.error("Error en guardar: " + e.getMessage(), e);
			return null;
		}
		
	}

	@Override
	public List<TransacionDAO> listaTransaciones(CuentaDAO cuenta) {
		try {
			List<TransacionDAO> listdao = new ArrayList<>();
			
			listdao = transacionRepositorio.findByCuenta(cuenta);
			
			return listdao;
		}catch(Exception e) {
			logger.error("Error en listaTransaciones: " + e.getMessage(), e);
			return null;
		}
	}

	
}

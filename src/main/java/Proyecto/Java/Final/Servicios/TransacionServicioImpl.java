package Proyecto.Java.Final.Servicios;

import org.springframework.beans.factory.annotation.Autowired;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.Repositorio.TransacionRepositorio;

public class TransacionServicioImpl implements ITransacionServicio{

	@Autowired
	private TransacionRepositorio transacionRepositorio;
	
	@Override
	public TransacionDAO guardar(TransacionDAO dao) {
		try {
			transacionRepositorio.save(dao);
			return dao;
		}catch(Exception e) {
			return null;
		}
		
	}

}

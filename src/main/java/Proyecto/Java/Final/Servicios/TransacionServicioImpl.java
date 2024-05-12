package Proyecto.Java.Final.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto.Java.Final.DAO.TransacionDAO;
import Proyecto.Java.Final.Repositorio.TransacionRepositorio;

@Service
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

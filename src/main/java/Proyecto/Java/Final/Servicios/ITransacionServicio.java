package Proyecto.Java.Final.Servicios;

import Proyecto.Java.Final.DAO.TransacionDAO;

public interface ITransacionServicio {
	// Guarda en la base de datos
	public TransacionDAO guardar(TransacionDAO dao);
}

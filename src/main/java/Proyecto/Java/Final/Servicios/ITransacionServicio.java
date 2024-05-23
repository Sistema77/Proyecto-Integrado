package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DAO.TransacionDAO;

public interface ITransacionServicio {
	// Guarda en la base de datos
	public TransacionDAO guardar(TransacionDAO dao);
	
	public List<TransacionDAO> listaTransaciones(CuentaDAO cuenta);
}

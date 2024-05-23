package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;

public interface IHistorialServicio {
	public List<Object> historial(CuentaDAO cuenta);
}

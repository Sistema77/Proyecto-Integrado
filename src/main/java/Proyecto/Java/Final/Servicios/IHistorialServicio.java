package Proyecto.Java.Final.Servicios;

import java.util.List;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.Util.FechaHoraComparable;

public interface IHistorialServicio {
	public List<FechaHoraComparable> historial(CuentaDAO cuenta);
}

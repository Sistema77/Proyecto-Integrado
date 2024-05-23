package Proyecto.Java.Final.Servicios;

import java.util.Calendar;

import Proyecto.Java.Final.DAO.CuentaDAO;
import Proyecto.Java.Final.DTO.CreditoDTO;

public interface ICreditoServicio {
	public CreditoDTO registrar(CreditoDTO credito, long id, int meses, CuentaDAO cuenta);
	public Calendar calcularFecha(CreditoDTO credito, int meses);
	public double calcularCuotaMensual(double tasaInteres, double cantidad, int meses);
}
